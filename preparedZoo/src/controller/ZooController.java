package controller;

import java.util.List;

import model.dao.ZooDao;
import model.dto.ZooDTO;
import model.vo.Zoo;

public class ZooController {
	
	public int save(String animalName, String animalType, String zone, 
					String prey, String gender) {
	
		Zoo zoo	= new Zoo(animalName, animalType, zone, prey, gender);
		int result = new ZooDao().save(zoo);
		return result;	
	}
	
	public List<Zoo> all(){
		
		List<Zoo> zoos = new ZooDao().all();
		return zoos;
	}
	
	public Zoo findByName(String animalName) {
		
		Zoo zoo = new ZooDao().findByName(animalName);
		return zoo;
	}
	
	public List<Zoo> findByKeyword(String keyword) {
		
		List<Zoo> zoos = new ZooDao().findByKeyword(keyword);
		return zoos;
	}
	
	public int update(String animalName, String animalType, String newType) {
		
		ZooDTO zd = new ZooDTO(animalName, animalType, newType);
		int result = new ZooDao().update(zd);
		return result;
	}
	
	public int delete(String animalName, String animalType) {
		
		Zoo zoo = new Zoo();
		zoo.setAnimalName(animalName);
		zoo.setAnimalType(animalType);
		
		int result = new ZooDao().delete(zoo);
		return result;
	}
}
