package view;

import java.util.List;
import java.util.Scanner;

import controller.PlantController;
import model.vo.Plant;

public class PlantView {
	
	private Scanner sc = new  Scanner(System.in);
	private PlantController pc = new PlantController();
	
	public void mainMenu() {
		while(true) {
			System.out.println(" ---- 식물 관리 프로그램 ---- ");
			System.out.println("1. 식물 추가");
			System.out.println("2. 식물 전체 조회");
			System.out.println("3. 식물 이름 조회");
			System.out.println("4. 식물 이름 키워드로 조회");
			System.out.println("5. 식물 정보 변경");
			System.out.println("6. 식물 삭제");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴를 선택해 주세요. > ");
			
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : save(); break; 
			case 2 : plantAll(); break;
			case 3 : findByName(); break; 
			case 4 : indByKeyword(); break; 
			case 5 : break; 
			case 6 : break; 
			case 9 : System.out.println("프로그램을 종료합니다."); return; 
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	private void save() {
		System.out.println("--- 동물 추가 ---");
		System.out.print("식물명를 입력해주세요. > ");
		String plName = sc.nextLine();
		System.out.print("식물종류를 입력해주세요. > ");
		String plType = sc.nextLine();
		System.out.print("식물색깔를 입력해주세요. > ");
		String plColor = sc.nextLine();
	
		int result = pc.save(plName, plType, plColor);
		
		if(result > 0) {
			System.out.println("식물 가입에 성공했습니다.");
		} else {
			System.out.println("식물 가입에 실패했습니다.");
		}
	}
	
	private void plantAll() {
		System.out.println("\n식물 전체 조회");
		List<Plant> plant = pc.plantAll();
		
		System.out.println("\n조회된 총 식물수는 " + plants.size() + "개 입니다.");
		if(plants.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			
			for(Plant plant : plants) {
				System.out.println("==============================");
				System.out.println("식물의 정보");
				System.out.print("식물명 : " + plant.getPlName() + ", ");
				System.out.print("식물종류 : " + plant.getPlType() + ", ");
				System.out.print("식물색깔 : " + plant.getPlColor() + ", ");
				System.out.println();
			}
		}
	}
	
	private void findByName(){
		System.out.println("\n식물명으로 검색 서비스입니다. ");
		System.out.print("식물명을 입력해주세요. > ");
		String plName = sc.nextLine();
		
		Plant plant = pc.findByName(plName);
		
		if(plant != null) {
			System.out.println(plName + "의 검색 결과입니다.");
			System.out.println("====================================");
			System.out.print("식물종류 : " + plant.getPlType() + ", ");
			System.out.print("식물색깔 : " + plant.getPlColor());
		
		
			System.out.println();
		} else {
			System.out.println("존재하지 않는 식물 입니다.");
		}
	}
	
	private void findByKeyword() {
		
		System.out.println("\n식물 이름 키워드로 검색");
		System.out.println("검색하고자 하는 키워드를 입력해 주세요. > ");
		String keyword = sc.nextLine();
		
		List<Plant> plants = pc.findByKeyword(keyword);
		if(plants.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			for(int i = 0; i < plants.size(); i++) {
				System.out.println((i+1) + "번 째 조회 결과!");
				System.out.println(plants.get(i));
			}
		}
	}
	
	
	
}
