package zoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class zooInsert {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("동물번호를 입력해주세요 > ");
		int animalNo = sc.nextInt();
		sc.nextLine();
		System.out.print("동물명를 입력해주세요 > ");
		String animalName = sc.nextLine();
		System.out.print("동물종류를 입력해주세요 > ");
		String animalType = sc.nextLine();
		System.out.print("구역를 입력해주세요 > ");
		String zone = sc.nextLine();
		System.out.print("먹이를 입력해주세요 > ");
		String prey = sc.nextLine();
		System.out.print("성별를 입력해주세요 > ");
		String gender = sc.nextLine();
		
		String insert = "INSERT "
						+ "INTO "
						     + "EMPLOYEE"
				     + " VALUES "
				             + "("
				              + animalNo
				           + ", '" + animalName + "'"
				           + ", '" + animalType + "'"
				           + ", '" + zone + "'"
				           + ", '" + prey + "'"
				           + ", '" + gender + "'"
				           + ", SYSDATE)";
				         
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
