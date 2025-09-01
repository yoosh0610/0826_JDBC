package Plant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String insert = """
							SELECT
					   			   PL_NAME
					             , PL_TYPE
					             , PL_COLOR
				  			  FROM
				       			   PLANT
						""";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE"
					  , "YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset =  stmt.executeQuery(insert);

			while(rset.next()){
				String pName = rset.getString("PL_NAME");
				String pType = rset.getString("PL_TYPE");
				String pColor = rset.getString("PL_COLOR");
				
				System.out.println("식물이름 : " + pName + ", 식물타입 : " + pType + ", 색상 : " + pColor);
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
