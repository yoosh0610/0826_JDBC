package Employee.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.JDBCTemplate;

import Employee.model.vo.Employee;

public class EmployeeDAO {
	
	private Properties prop = new Properties();
	
	public EmployeeDAO() {
		try {
			prop.loadFromXML(new FileInputStream("resources/practice-mapper.xml"));
		}catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public List<Employee> findAll(Connection conn) {
		
		List<Employee> emps = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String select = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(select);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Employee emp = new Employee(rset.getInt("EMP_ID"),
											rset.getString("EMP_NAME"),
											rset.getString("DEPT_TITLE"),
											rset.getString("JOB_NAME"),
											rset.getInt("SALARY"));
				emps.add(emp);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return emps;
	}
	
	public List<Employee> findDept(Connection conn, String title) {
		
		List<Employee> emps = new ArrayList();
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String select = prop.getProperty("findDept");
		
		try {
			pstmt = conn.prepareStatement(select);
			pstmt.setString(1, title);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				emps.add(new Employee(rset.getInt("EMP_ID"),
									  rset.getString("EMP_NAME"),
									  rset.getString("DEPT_TITLE"),
									  rset.getString("JOB_NAME"),
									  rset.getInt("SALARY")));
									
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return emps;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
