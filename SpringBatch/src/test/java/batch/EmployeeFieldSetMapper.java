package batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

class EmployeeFieldSetMapper implements FieldSetMapper<Employee> {
	public Employee mapFieldSet(FieldSet fieldSet) {
		Employee emp = new Employee();
		emp.setID(fieldSet.readInt("ID")); 
		emp.setLastName(fieldSet.readString("lastName"));
		emp.setFirstName(fieldSet.readString("firstName"));
		emp.setDesignation(fieldSet.readString("designation"));
		emp.setDepartment(fieldSet.readString("department"));
		emp.setYearOfJoining(fieldSet.readInt("yearOfJoining"));
		return emp;
	}
}