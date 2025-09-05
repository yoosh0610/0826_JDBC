package Employee.controller;

import java.util.List;

import Employee.model.service.EmployeeService;
import Employee.model.vo.Employee;

public class EmployeeController {
	
	public List<Employee> findAll() {
		List<Employee> emps = new EmployeeService().findAll();
		
		return emps;
	}
	
	public List<Employee> findDept(String title) {
		List<Employee> emps = new EmployeeService().findDept(title);
		
		return emps;
	}
	
	
	
	
}
