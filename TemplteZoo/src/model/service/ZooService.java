package model.service;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import model.dao.ZooDao;
import model.dto.ZooDTO;
import model.vo.Zoo;


public class ZooService {
	private Connection conn = null;
	public ZooService() {
		conn = JDBCTemplate.getConnection();
	}
	
	public int save(Zoo zoo) {
		int result = 0;
		result = new ZooDao().save(conn, zoo);
		JDBCTemplate.commit(conn);
		return result;
	}
	
	public List<Zoo> all(){
		List<Zoo> Zoos = new ZooDao().all(conn);
		return Zoos;
	}
	
	public Zoo findByName(String animalName) {
		Zoo zoo = new ZooDao().findByName(conn, animalName);
		return zoo;
	}
	
	public List<Zoo> findByKeyword(String keyword) {
		List<Zoo> zoos = new ZooDao().findByKeyword(conn, keyword);
		return zoos;
	}
	
	public int update(ZooDTO zd) {
		int result = 0;
		result = new ZooDao().update(conn, zd);
		JDBCTemplate.commit(conn);
		return result;
	}
	
	public int delete(ZooDTO zd) {
		int result = 0;
		result = new ZooDao().delete(conn, zd);
		JDBCTemplate.commit(conn);
		return result;
		
	}
}
