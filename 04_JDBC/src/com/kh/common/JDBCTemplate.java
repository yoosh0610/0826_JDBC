package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * 코드(하드코딩)로 컴파일 하지않고 파일로 빼서 설정
 * 
 */

public class JDBCTemplate {
	// 중복을 제거하는 법 : 값의 중복은 변수, 코드의 중복은 메소드
	/*
	 * JDBC과정 중 반복적으로 쓰이는 구문들을 각각의 메소드로 정의해 둘 클래스
	 * 중복된 코드들을 메소드로 분리하여 '재사용'
	 * 이 클래스의 모든 클래스는 전부 static으로 선언
	 */
	// JDBC Driver를 등록하는 메소드
	// 프로그램 실행 중 단 한번만 실행되면 됨
	public static void registDriver() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// DB의 연결정보를 가지고 있는 Connection객체를 생성해서 반환해주는 메소드
	public static Connection getConnection() {
		
		try {
			
			Properties prop = new Properties();
			
			prop.load(new FileInputStream("resources/driver.properties"));
		
			//String keyA = prop.getProperty("A");
			//System.out.println("A 키값의 Value : " + keyA);
			//System.out.println(prop.getProperty("URL"));

			Connection conn = DriverManager.getConnection(prop.getProperty("URL"),
														  prop.getProperty("USERNAME"), 
														  prop.getProperty("PASSWORD"));
			// 값을 읽어오는 시점-> MemberService의 생성자를 호출할 때마다
			// 원래라면 프로그램 시작하면 클래스 시점에서 실행 => 동적으로(유연하게)
			
			conn.setAutoCommit(false);
		
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 트랜잭션 처리;
	
	// Connection객체를 이용해서 commit 시켜주는 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Connection객체를 이용해서 rollback 시켜주는 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC용 객체를 반납해주는 메소드(각 객체별로)
	// Connection 객체를 전달받아서 반납해주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Statement객체를 전달받아서 반납해주는 메소드
	// => 다형성을 적용하여 PreparedStatement객체도 Statement타입으로 받을 수 있음
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ResultSet 객체를 전달받아서 반납해주는 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
