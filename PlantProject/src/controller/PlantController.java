package controller;

import java.util.List;

import model.dao.PlantDao;
import model.vo.Plant;

public class PlantController {
	
	public int save(String plName, String plType, String plColor) {
		
		Plant plant	= new Plant(plName, plType, plColor);
	
		int result = new PlantDao().save(plant);
		return result;	
	}
	
	public List<Plant> plantAll(){
		
		List<Plant> plants = new PlantDao().plantAll();
		return plants;
	}
	
	public Plant findByName(String plName) {
		
		Plant plant = new PlantDao().findByName(plName);
		
		return plant;
	}
	
	public List<Plant> findByKeyword(String keyword) {
		
		List<Plant> plants = new PlantDao().findByKeyword(keyword);
		
		return plants;
	}
	
	
}
