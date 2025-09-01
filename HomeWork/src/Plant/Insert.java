package Plant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("식물이름을 입력해주세요 > ");
		String pName = sc.nextLine();
		System.out.print("식물타입를 입력해주세요 > ");
		String pType = sc.nextLine();
		System.out.print("색상를 입력해주세요 > ");
		String pColor = sc.nextLine();
		
		String insert = "INSERT "
				+ "INTO "
				     + "EMPLOYEE"
		     + " VALUES "
		             + "("
		             + "'" + pName + "'"
		           + ", '" + pType + "'"
		           + ", '" + pColor + "'";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
			        							"YSH16", "YSH161234");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(insert);
			if(result > 0) { 
				conn.commit();
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
