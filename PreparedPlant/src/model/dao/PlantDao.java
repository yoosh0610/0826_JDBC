package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.PlantDTO;
import model.vo.Plant;

public class PlantDao {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE"; 
	private final String USERNAME = "YSH16";
	private final String PASSWORD = "YSH161234";
	
	
	
	public int save(Plant plant) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String insert = """
							INSERT
							  INTO
							       PLANT
							VALUES 
							  	   (
							  	   ?
							  	 , ?  
								 , ?
						""";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, plant.getPlName());
			pstmt.setString(2, plant.getPlType());
			pstmt.setString(3, plant.getPlColor());
			result = pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
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
		PreparedStatement pstmt = null;
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
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement("""
											 SELECT
									   			    PL_NAME
									              , PL_TYPE
									              , PL_COLOR
								  			   FROM
								       			    PLANT 
								  			  ORDER 
									 			 BY 
											   	    PL_NAME
										  """
										  );
			rset = pstmt.executeQuery();
			
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
				if(pstmt != null) {
					pstmt.close();
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
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		Plant plant = null;
		
		String insert = """
							SELECT
					   			   PL_NAME
					             , PL_TYPE
					             , PL_COLOR
				  			  FROM
				       			   PLANT 
				  			 WHERE 
							   	   PL_NAME = ?
						""";
		
		try {
			Class.forName(DRIVER);
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, plName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				plant = new Plant(rset.getString("PL_NAME")
							  	, rset.getString("PL_TYPE")
							  	, rset.getString("PL_COLOR"));
				}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}  catch(SQLException e) {
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
				if(pstmt != null) {
					pstmt.close();
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
		return plant;
	}	
	
	public List<Plant> findByKeyword(String keyword) {
		
		List<Plant> plants = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String insert = """
							SELECT
				                   PL_NAME
								 , PL_TYPE
				  				 , PL_COLOR
				  			  FROM
				  				   PLANT
				  			 WHERE 
				  			 	   PL_NAME LIKE '%'||?||'%'
				  			 ORDER
				  			    BY
						 		   PL_NAME
				        """;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(insert);
			rset = pstmt.executeQuery();
			
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
				if(rset != null) {
				rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) {
				pstmt.close();
				}
			} catch (SQLException e) {
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
	
	public int update(PlantDTO pd) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = """
						UPDATE
				 			   PLANT
				 		   SET    
						   	   PL_TYPE = ?
				 		 WHERE 
					   	   	   PL_NAME = ?
					   	   AND  
					   	   	   PL_TYPE = ? 
					""";
		try {
			
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
						
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pd.getNewType());
			pstmt.setString(2, pd.getPlName());
			pstmt.setString(3, pd.getPlType());
						
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
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
		return result;
	}
	
	public int delete(Plant plant) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
						DELETE
						  FROM
				 			   PLANT
				 		 WHERE    
					   	   	   PL_NAME = ?
					   	   AND  
					   	   	   PL_TYPE = ? 
					""";
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, plant.getPlName());
			pstmt.setString(2, plant.getPlType());
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
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
		return result;
	}
}
