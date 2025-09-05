package controller;

import java.util.List;

import model.dao.PlantDao;
import model.dto.PlantDTO;
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
	
	public int update(String plName, String plType, String newType) {
		
		PlantDTO pd = new PlantDTO(plName, plType, newType);
		
		int result = new PlantDao().update(pd);
		
		return result;
	}
	
	public int delete(String plName, String plType) {
		
		Plant plant = new Plant();
		plant.setPlName(plName);
		plant.setPlType(plType);
		
		int result = new PlantDao().delete(plant);
		
		return result;
	}
	
}
