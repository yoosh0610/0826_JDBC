package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Plant;

public class PlantDao {
	
	public int save(Plant plant) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		String insert = "INSERT "
				        + "INTO "
				        	 + "PLANT "
					  + "VALUES "
						     + "("
						      + plant.getPlName() + "'"
						   + ", '" + plant.getPlType() + "'"
						   + ", '" + plant.getPlColor() + "')";
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
	
	public List<Plant> plantAll(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Plant> plants = new ArrayList();
		
		String insert = """
							SELECT
					   			   PL_NAME
					             , PL_TYPE
					             , PL_COLOR
				  			  FROM
				       			   PLANT 
				  			 ORDER 
					 			BY 
							   	   PL_NAME
						""";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
					"YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(insert);
			
			while(rset.next()) {
				Plant plant = new Plant();
				plant.setPlName(rset.getString("PL_NAME"));
				plant.setPlType(rset.getString("PL_TYPE"));
				plant.setPlColor(rset.getString("PL_COLOR"));
				
				plants.add(plant);
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
		return plants;
	}
	
	public Plant findByName(String plName) {
		
		Plant plant = null;
		
		String insert = """
							SELECT
					   			   PL_NAME
					             , PL_TYPE
					             , PL_COLOR
				  			  FROM
				       			   PLANT 
				  			 WHERE 
							   	   PL_NAME = 
						""";
		insert += "'" + plName + "'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
															"YSH16", "YSH161234");
					Statement stmt = conn.createStatement();
					ResultSet rset = stmt.executeQuery(insert)){
				if(rset.next()) {
					plant = new Plant(rset.getString("PL_NAME")
							  , rset.getString("PL_TYPE")
							  , rset.getString("PL_COLOR"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return plant;
		}	
	
	public List<Plant> findByKeyword(String keyword) {
		
		List<Plant> plants = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String insert = "SELECT "
					      	 + "PL_NAME"
					       + ", PL_TYPE"
					       + ", PL_COLOR"
	                    + "FROM "
	                         + "PLANT "
			           + "WHERE "
			        	  	 + "PL_NAME LIKE '%" + keyword + "%'"
			           + "ORDER "
			   	       	  + "BY "
			   	   	  	  	 + "PL_NAME";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", 
					"YSH16", "YSH161234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(insert);
			
			while(rset.next()) {
				plants.add(new Plant(rset.getString("PL_NAME")
						  , rset.getString("PL_TYPE")
						  , rset.getString("PL_COLOR")));
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
		return plants;
	}
	
	
}
