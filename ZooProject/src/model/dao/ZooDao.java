package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Zoo;

public class ZooDao {
	
	public int save(Zoo zoo) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		String insert = "INSERT "
				        + "INTO "
				        	 + "Zoo "
					  + "VALUES "
						     + "("
						  	 + "SEQ_ZOONO.NEXTVAL"
						   + ", '" + zoo.getAnimalName()+ "'"
						   + ", '" + zoo.getAnimalType()+ "'"
						   + ", '" + zoo.getZone()+ "'"
						   + ", '" + zoo.getPrey()+ "'"
						   + ", '" + zoo.getGender()+ "'"
						   + ", SYSDATE"
						  	 + ")";
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
				if(stmt != null) {
					stmt.close();
				}
		
			}catch(SQLException e) {
			}	
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Zoo> all(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Zoo> zoos = new ArrayList();
		
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
							   	   ADOPTION_DAY DESC
						""";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
					"YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(insert);
			
			while(rset.next()) {
				Zoo zoo = new Zoo();
				zoo.setAnimalNo(rset.getInt("ANIMAL_NO"));
				zoo.setAnimalName(rset.getString("ANIMAL_NAME"));
				zoo.setAnimalType(rset.getString("ANIMAL_TYPE"));
				zoo.setZone(rset.getString("ZONE"));
				zoo.setPrey(rset.getString("PREY"));
				zoo.setGender(rset.getString("GENDER"));
				zoo.setAdoptionDay(rset.getDate("ADOPTION_DAY"));
				
				zoos.add(zoo);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) {
					rset.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return zoos;
	}
	
	public Zoo findByName(String animalName) {
		
		Zoo zoo = null;
		
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
				  			 WHERE 
							   	   ANIMAL_NAME = 
						""";
		insert += "'" + animalName + "'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
															"YSH16", "YSH161234");
					Statement stmt = conn.createStatement();
					ResultSet rset = stmt.executeQuery(insert)){
				if(rset.next()) {
					zoo = new Zoo(rset.getInt("ANIMAL_NO")
							  , rset.getString("ANIMAL_NAME")
							  , rset.getString("ANIMAL_TYPE")
							  , rset.getString("ZONE")
							  , rset.getString("PREY")
							  , rset.getString("GENDER")
							  , rset.getDate("ADOPTION_DAY"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return zoo;
		}	
	
	public List<Zoo> findByKeyword(String keyword) {
		
		List<Zoo> zoos = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String insert = "SELECT "
					      	 + "ANIMAL_NO"
					       + ", ANIMAL_NAME"
					       + ", ANIMAL_TYPE"
					       + ", ZONE"
					       + ", PREY"
					       + ", GENDER"
					       + ", ADOPTION_DAY "
	                    + "FROM "
	                         + "ZOO "
			           + "WHERE "
			        	  	 + "ANIMAL_NAME LIKE '%" + keyword + "%'"
			           + "ORDER "
			   	       	  + "BY "
			   	   	  	  	 + "ADOPTION_DAY DESC";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
					"YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(insert);
			
			while(rset.next()) {
				zoos.add(new Zoo(rset.getInt("ANIMAL_NO")
						  , rset.getString("ANIMAL_NAME")
						  , rset.getString("ANIMAL_TYPE")
						  , rset.getString("ZONE")
						  , rset.getString("PREY")
						  , rset.getString("GENDER")
						  , rset.getDate("ADOPTION_DAY")));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return zoos;
	}
	
	
	
}
