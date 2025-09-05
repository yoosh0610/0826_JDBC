package model.controller;

import java.util.List;

import model.dto.ZooDTO;
import model.service.ZooService;
import model.vo.Zoo;

public class ZooController {
	
	public int save(Zoo zoo) {
		
		int result = new ZooService().save(zoo);
		return result;	
	}
	
	public List<Zoo> all(){
		
		List<Zoo> zoos = new ZooService().all();
		return zoos;
	}
	
	public Zoo findByName(String animalName) {
		
		Zoo zoo = new ZooService().findByName(animalName);
		return zoo;
	}
	
	public List<Zoo> findByKeyword(String keyword) {
		
		List<Zoo> zoos = new ZooService().findByKeyword(keyword);
		return zoos;
	}
	
	public int update(String animalName, String animalType, String newType) {
		
		ZooDTO zd = new ZooDTO(animalName, animalType, newType);
		int result = new ZooService().update(zd);
		return result;
	}
	
	public int delete(String animalName, String animalType) {
		
		ZooDTO zd = new ZooDTO();
		zd.setAnimalName(animalName);
		zd.setAnimalType(animalType);
		
		int result = new ZooService().delete(zd);
		return result;
	}
}