package Employee.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.function.Function;

import Employee.model.dao.EmployeeDAO;
import Employee.model.vo.Employee;

public class EmployeeService {
	
	private Connection conn = null;
	public EmployeeService() {
		this.conn = getConnection();
	}
	private <T> T executeQuery(Function<Connection, T> daoFunction) {
		Connection conn = null;
		T result = null;
		conn = getConnection();
		result = daoFunction.apply(conn);
		close(conn);
		return result;
	}
	
	public List<Employee> findAll() {
		return executeQuery(new EmployeeDAO()::findAll);
	}
	
	public List<Employee> findDept(String title) {
		return executeQuery(conn -> new EmployeeDAO().findDept(conn, title));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
