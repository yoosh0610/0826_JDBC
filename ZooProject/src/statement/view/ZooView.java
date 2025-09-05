package statement.view;

import java.util.List;
import java.util.Scanner;

import statement.controller.ZooController;
import statement.model.vo.Zoo;


public class ZooView {
	
	private Scanner sc = new  Scanner(System.in);
	private ZooController zc = new ZooController();
	
	public void mainMenu() {
		while(true) {
			System.out.println(" ---- 동물 관리 프로그램 ---- ");
			System.out.println("1. 동물 추가");
			System.out.println("2. 동물 전체 조회");
			System.out.println("3. 동물 이름 조회");
			System.out.println("4. 동물 이름 키워드로 조회");
			System.out.println("5. 동물 정보 변경");
			System.out.println("6. 동물 삭제");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴를 선택해 주세요. > ");
			
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : save(); break; 
			case 2 : all(); break;
			case 3 : findByName(); break; 
			case 4 : findByKeyword(); break; 
			case 5 : break; 
			case 6 : break; 
			case 9 : System.out.println("프로그램을 종료합니다."); return; 
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	private void save() {
		System.out.println("--- 동물 추가 ---");
		System.out.print("동물명를 입력해주세요. > ");
		String animalName = sc.nextLine();
		System.out.print("동물종류를 입력해주세요. > ");
		String animalType = sc.nextLine();
		System.out.print("구역를 입력해주세요. > ");
		String zone = sc.nextLine();
		System.out.print("먹이를 입력해주세요. > ");
		String prey = sc.nextLine();
		System.out.print("성별를 입력해주세요. > ");
		String gender = sc.nextLine();
	
		int result = zc.save(animalName, animalType, zone, prey, gender);
		
		if(result > 0) {
			System.out.println("동물 가입에 성공했습니다.");
		} else {
			System.out.println("동물 가입에 실패했습니다.");
		}
	}
	
	private void all() {
		System.out.println("\n동물 전체 조회");
		List<Zoo> zoos = zc.all();
		
		System.out.println("\n조회된 총 동물수는 " + zoos.size() + "마리 입니다.");
		if(zoos.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			
			for(Zoo zoo : zoos) {
				System.out.println("==============================");
				System.out.println(zoo.getAnimalNo() + "번 동물의 정보");
				System.out.print("동물명 : " + zoo.getAnimalName() + ", ");
				System.out.print("동물종류 : " + zoo.getAnimalType() + ", ");
				System.out.print("구역 : " + zoo.getZone() + ", ");
				System.out.print("먹이 : " + zoo.getPrey() + ", ");
				System.out.print("성별 : " + zoo.getGender() + ", ");
				System.out.println("입양일 : " + zoo.getAdoptionDay());
				System.out.println();
			}
		}
	}
	
	private void findByName(){
		System.out.println("\n동물명으로 검색 서비스입니다. ");
		System.out.print("동물명을 입력해주세요. > ");
		String animalName = sc.nextLine();
		
		Zoo zoo = zc.findByName(animalName);
		
		if(zoo != null) {
			System.out.println(animalName + "의 검색 결과입니다.");
			System.out.println("====================================");
			System.out.print("동물종류 : " + zoo.getAnimalType() + ", ");
			System.out.print("구역 : " + zoo.getZone() + ", ");
			System.out.print("먹이 : " + zoo.getPrey() + ", ");
			System.out.print("성별 : " + zoo.getGender() + ", ");
			System.out.print("가입일 : " + zoo.getAdoptionDay());
			System.out.println();
		} else {
			System.out.println("존재하지 않는 동물 입니다.");
		}
	}
	
	private void findByKeyword() {
		
		System.out.println("\n동물 이름 키워드로 검색");
		System.out.println("검색하고자 하는 키워드를 입력해 주세요. > ");
		String keyword = sc.nextLine();
		
		List<Zoo> zoos = zc.findByKeyword(keyword);
		if(zoos.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			for(int i = 0; i < zoos.size(); i++) {
				System.out.println((i+1) + "번 째 조회 결과!");
				System.out.println(zoos.get(i));
			}
		}
	}
	 
	
}
