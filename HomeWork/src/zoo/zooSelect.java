package zoo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class zooSelect {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String insert = """
							SELECT
					   			   ANIMAL_NO
					             , ANIMAL_NAME
					             , ANIMAL_TYPE
					 			 , ZONE
					 			 , PREY
					 			 , GENDER
					 			 , ADOPTION_DAY
				  			  FROM
				       			   ZOO
				  			 ORDER 
					 			BY 
							   	   ANIMAL_NO
						""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE"
					  , "YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset =  stmt.executeQuery(insert);

			while(rset.next()){
				int animalNo = rset.getInt("ANIMAL_NO");
				String animalName = rset.getString("ANIMAL_NAME");
				String animalType = rset.getString("ANIMAL_TYPE");
				String zone = rset.getString("ZONE");
				String prey = rset.getString("PREY");
				String gender = rset.getString("GENDER");
				Date adoptionDay = rset.getDate("ADOPTION_DAY");
				
				
				System.out.println("동물번호 : " + animalNo + ", 동물명 : " + animalName + ", 동물종류 : " + animalType 
						+ ", 구역 : " + zone + ", 먹이 : " + prey + ", 성별 : " + gender 
						+ ", 입양날짜 : " + adoptionDay);
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
