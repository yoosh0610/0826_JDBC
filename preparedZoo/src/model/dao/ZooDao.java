package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.ZooDTO;
import model.vo.Zoo;

public class ZooDao {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE"; 
	private final String USERNAME = "YSH16";
	private final String PASSWORD = "YSH161234";
	
	
	
	public int save(Zoo zoo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String insert = """
							INSERT
							  INTO
								   Zoo
							VALUES
								   (
								   SEQ_ZOONO.NEXTVAL
								 , ?
								 , ?
								 , ?
								 , ?
								 , ?
								 , SYSDATE
								   )
						""";	
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insert);
			
			pstmt.setString(1, zoo.getAnimalName());
			pstmt.setString(2, zoo.getAnimalType());
			pstmt.setString(3, zoo.getZone());
			pstmt.setString(4, zoo.getPrey());
			pstmt.setString(5, zoo.getGender());
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			}
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
	
	public List<Zoo> all(){
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(
											"""
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
									        """
										);
			rset = pstmt.executeQuery(insert);
			
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
		return zoos;
	}
	
	public Zoo findByName(String animalName) {
		Zoo zoo = null;
		Connection conn = null;
		PreparedStatement pstmt = null; 
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
				  			 WHERE 
							   	   ANIMAL_NAME = ?
						 """;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, animalName);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				zoo = new Zoo(rset.getInt("ANIMAL_NO")
							, rset.getString("ANIMAL_NAME")
							, rset.getString("ANIMAL_TYPE")
							, rset.getString("ZONE")
							, rset.getString("PREY")
							, rset.getString("GENDER")
							, rset.getDate("ADOPTION_DAY"));
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
		return zoo;
	}	
	
	public List<Zoo> findByKeyword(String keyword) {
		
		List<Zoo> zoos = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null; 
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
			                WHERE 
			        	  	      ANIMAL_NAME LIKE '%'||?||'%'
			           		ORDER 
			   	       	  	   BY 
			   	   	  	  	 	  ADOPTION_DAY DESC
						 """;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(insert);
			rset = pstmt.executeQuery();
			
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
		return zoos;
	}
	
	public int update(ZooDTO zd) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insert = """
							UPDATE
					 			   ZOO
					 		   SET    
							   	   ANIMAL_TYPE = ?
					 		 WHERE 
						   	   	   ANIMAL_NAME = ?
						   	   AND  
						   	   	   ANIMAL_TYPE = ? 
						""";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
						
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, zd.getNewType());
			pstmt.setString(2, zd.getAnimalName());
			pstmt.setString(3, zd.getAnimalType());
						
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
		
	public int delete(Zoo zoo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String insert = """
							DELETE
							  FROM
					 			   ZOO
					 		 WHERE    
						   	   	   ANIMAL_NAME = ?
						   	   AND  
						   	   	   ANIMAL_TYPE = ? 
						""";
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, zoo.getAnimalName());
			pstmt.setString(2, zoo.getAnimalType());
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
