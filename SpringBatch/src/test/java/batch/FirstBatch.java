package batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.FileSystemResource;

public class FirstBatch implements Tasklet {
	static Logger logger = Logger.getLogger("FirstBatch");

	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {
		logger.info("** First Batch Job is Executing! **");
		// Fixed length File Read
		FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<Employee>();
		itemReader.setResource(new FileSystemResource("employees.txt"));
		// FixedLengthTokenizer reads each field of length specified
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<Employee>();
		FixedLengthTokenizer lineTokenizer = new FixedLengthTokenizer();
		lineTokenizer.setNames(new String[] { "ID", "lastName", "firstName",
				"designation", "department", "yearOfJoining" });

		lineTokenizer.setColumns(new Range[] { new Range(1, 2),
				new Range(3, 12), new Range(13, 22), new Range(23, 32),
				new Range(33, 47), new Range(48, 51) });
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(new EmployeeFieldSetMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.open(new ExecutionContext());
		Employee employee = new Employee();

		while (employee != null) {
			employee = itemReader.read();
			if (employee == null) {
				break;
			}
			logger.info(employee.toString());
		}
		
		// Delimited File Read
		FlatFileItemReader<Employee> itemReader1 = new FlatFileItemReader<Employee>();
		itemReader1.setResource(new FileSystemResource("employees.csv"));
		// DelimitedLineTokenizer defaults to comma as its delimiter
		DefaultLineMapper<Employee> lineMapper1 = new DefaultLineMapper<Employee>();
		DelimitedLineTokenizer lineTokenizer1 = new DelimitedLineTokenizer();
		lineTokenizer1.setNames(new String[] { "ID", "lastName", "firstName", "designation", "department", "yearOfJoining" });
		lineMapper1.setLineTokenizer(lineTokenizer1);
		lineMapper1.setFieldSetMapper(new EmployeeFieldSetMapper());
		itemReader1.setLineMapper(lineMapper1);
		itemReader1.open(new ExecutionContext());
		Employee employee1 = new Employee();
		while (employee1 != null) {
			employee1 = itemReader1.read();
			if (employee1 == null) {
				return RepeatStatus.FINISHED;
			}
			System.out.println(employee1.toString());
		}
		
		/*// PatternMatchingCompositeLineMapper
		FlatFileItemReader<Employee> itemReader2 = new FlatFileItemReader<Employee>();
		itemReader2.setResource(new FileSystemResource("employees.csv"));
		DefaultLineMapper<Employee> lineMapper2 = new DefaultLineMapper<Employee>();
		PatternMatchingCompositeLineTokenizer  lineTokenizer2 = new PatternMatchingCompositeLineTokenizer ();
		lineTokenizer2.setTokenizers(Map<"Line",LineTokenizer>,tokenizers);
//		lineTokenizer2.setNames(new String[] { "ID", "lastName", "firstName", "designation", "department", "yearOfJoining" });
//		lineMapper2.setLineTokenizer(lineTokenizer2);
		lineMapper2.setFieldSetMapper(new EmployeeFieldSetMapper());
		itemReader2.setLineMapper(lineMapper2);
		itemReader2.open(new ExecutionContext());
		Employee employee2 = new Employee();
		while (employee2 != null) {
			employee2 = itemReader2.read();
			if (employee2 == null) {
				return RepeatStatus.FINISHED;
			}
			System.out.println(employee2.toString());
		}*/
		return RepeatStatus.FINISHED;
	}
}