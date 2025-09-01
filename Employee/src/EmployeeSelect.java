import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeSelect {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String insert = """
							SELECT
					   			   EMP_ID
					             , EMP_NAME
					             , EMP_NO
					 			 , EMAIL
					 			 , PHONE
					 			 , DEPT_CODE
					 			 , JOB_CODE
					 			 , SAL_LEVEL
					 			 , SALARY
					 			 , BONUS
					 			 , MANAGER_ID
					 			 , HIRE_DATE
					 			 , ENT_DATE
					 			 , ENT_YN
				  			  FROM
				       			   EMPLOYEE
				  			 ORDER 
					 			BY 
							   	   HIRE_DATE
						""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE"
					  , "YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset =  stmt.executeQuery(insert);

			while(rset.next()){
				int empId = rset.getInt("EMP_ID");
				String empName = rset.getString("EMP_NAME");
				String empNo = rset.getString("EMP_NO");
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String deptCode = rset.getString("DEPT_CODE");
				String jobCode = rset.getString("JOB_CODE");
				String salLevel = rset.getString("SAL_LEVEL");
				String endYn = rset.getString("ENT_YN");
				
				int salary = rset.getInt("SALARY");
				int managerId = rset.getInt("MANAGER_ID");
				double bonus = rset.getDouble("BONUS");
				
				Date hireDate = rset.getDate("HIRE_DATE");
				Date endDate = rset.getDate("ENT_DATE");
				
				
				System.out.println("사번 : " + empId + ", 직원명 : " + empName + ", 주민번호 : " + empNo 
						+ ", 이메일 : " + email + ", 전화번호 : " + phone + ", 부서코드 : " + deptCode 
						+ ", 직급코드 : " + jobCode + ", 급여등급 : " + salLevel + ", 급여 : " + salary 
						+ ", 보너스율 : " + bonus + ", 관리자사번 : " + managerId + ", 입사일 : " + hireDate 
						+ ", 톼사일 : " + endDate + ", 재직여부 : " + endYn);
				} 
		} catch(ClassNotFoundException e) {
		} catch(SQLException e) {
		} finally {
				try {
					if(rset != null && !rset.isClosed()) {
						rset.close();
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				try {
					if(stmt != null && !stmt.isClosed()) 
						stmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				try {
					if(conn != null && !conn.isClosed()) 
						conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		
		
	}

}
