package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE",
													"YSH16", "YSH161234");
		conn.setAutoCommit(false);
		
		return conn;
		} catch (SQLException e) {
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
