package batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExecuteBatchJob {

	
	static Logger logger = Logger.getLogger("ExecuteBatchJob");
	public static void main(String[] args) throws Exception {
		
		String[] springConfig  = {"batch/firstBatch.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("firstBatchJob");
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());			
			logger.info("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (context != null) {
				context = null;
			}
		}
		logger.info("Done");
	}
}
