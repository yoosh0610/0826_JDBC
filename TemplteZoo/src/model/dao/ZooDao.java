package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import model.dto.ZooDTO;
import model.vo.Zoo;

public class ZooDao {
	
	public int save(Connection conn, Zoo zoo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String insert = """
							INSERT
							  INTO
								   ZOO
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
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, zoo.getAnimalName());
			pstmt.setString(2, zoo.getAnimalType());
			pstmt.setString(3, zoo.getZone());
			pstmt.setString(4, zoo.getPrey());
			pstmt.setString(5, zoo.getGender());
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public List<Zoo> all(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Zoo> zoos = new ArrayList();
		String select = """
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
			pstmt = conn.prepareStatement(select);
			rset = pstmt.executeQuery();
			
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
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return zoos;
	}
	
	public Zoo findByName(Connection conn, String animalName) {
		Zoo zoo = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		String select = """
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
			pstmt = conn.prepareStatement(select);
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
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return zoo;
	}	
	
	public List<Zoo> findByKeyword(Connection conn,String keyword) {
		List<Zoo> zoos = new ArrayList();
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		String select = """
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
			pstmt = conn.prepareStatement(select);
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
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return zoos;
	}
	
	public int update(Connection conn, ZooDTO zd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String update = """
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
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, zd.getNewType());
			pstmt.setString(2, zd.getAnimalName());
			pstmt.setString(3, zd.getAnimalType());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
		
	public int delete(Connection conn, ZooDTO zd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String delete = """
							DELETE
							  FROM
					 			   ZOO
					 		 WHERE    
						   	   	   ANIMAL_NAME = ?
						   	   AND  
						   	   	   ANIMAL_TYPE = ? 
						""";
		
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, zd.getAnimalName());
			pstmt.setString(2, zd.getAnimalType());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}	
	
	
	
	
}
