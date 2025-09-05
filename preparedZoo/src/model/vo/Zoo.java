package model.vo;

import java.sql.Date;
import java.util.Objects;

public class Zoo {
	
	private int animalNo;    
    private String animalName;
    private String animalType;
    private String zone; 
    private String prey;
    private String gender;
    private Date adoptionDay;
	
    public Zoo() {
		super();
	}

	public Zoo(String animalName, String animalType, String zone, String prey, String gender) {
		super();
		this.animalName = animalName;
		this.animalType = animalType;
		this.zone = zone;
		this.prey = prey;
		this.gender = gender;
	}

	public Zoo(int animalNo, String animalName, String animalType, String zone, String prey, String gender,
			Date adoptionDay) {
		super();
		this.animalNo = animalNo;
		this.animalName = animalName;
		this.animalType = animalType;
		this.zone = zone;
		this.prey = prey;
		this.gender = gender;
		this.adoptionDay = adoptionDay;
	}

	public int getAnimalNo() {
		return animalNo;
	}

	public void setAnimalNo(int animalNo) {
		this.animalNo = animalNo;
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

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getPrey() {
		return prey;
	}

	public void setPrey(String prey) {
		this.prey = prey;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getAdoptionDay() {
		return adoptionDay;
	}

	public void setAdoptionDay(Date adoptionDay) {
		this.adoptionDay = adoptionDay;
	}

	@Override
	public String toString() {
		return "Zoo [animalNo=" + animalNo + ", animalName=" + animalName + ", animalType=" + animalType + ", zone="
				+ zone + ", prey=" + prey + ", gender=" + gender + ", adoptionDay=" + adoptionDay + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adoptionDay, animalName, animalNo, animalType, gender, prey, zone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zoo other = (Zoo) obj;
		return Objects.equals(adoptionDay, other.adoptionDay) && Objects.equals(animalName, other.animalName)
				&& animalNo == other.animalNo && Objects.equals(animalType, other.animalType)
				&& Objects.equals(gender, other.gender) && Objects.equals(prey, other.prey)
				&& Objects.equals(zone, other.zone);
	}
	
    
	
	
	
}
