package model.dto;

import java.sql.Date;
import java.util.Objects;

import model.vo.Zoo;

public class ZooDTO {
	
	private String animalName;
    private String animalType;
    private String newType;
	
    public ZooDTO() {
		super();
	}

	public ZooDTO(String animalName, String animalType, String newType) {
		super();
		this.animalName = animalName;
		this.animalType = animalType;
		this.newType = newType;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	@Override
	public String toString() {
		return "ZooDTO [animalName=" + animalName + ", animalType=" + animalType + ", newType=" + newType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(animalName, animalType, newType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZooDTO other = (ZooDTO) obj;
		return Objects.equals(animalName, other.animalName) && Objects.equals(animalType, other.animalType)
				&& Objects.equals(newType, other.newType);
	}
    
    
    
}
