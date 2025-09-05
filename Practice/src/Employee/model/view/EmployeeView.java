package Employee.model.view;

import java.util.List;
import java.util.Scanner;

import Employee.controller.EmployeeController;
import Employee.model.vo.Employee;

public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println(" ---- 회원 관리 프로그램 ---- ");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 부서가 동일한 사원 조회");
			System.out.println("3. 직급이 동일한 사원 조회");
			System.out.println("4. 사원 상세 조회");
			System.out.println("5. 급여가 높은 상위 다섯명 조회");
			System.out.println("6. 급여가 낮은 하위 다섯명 조회");
			System.out.println("7. 사원 추가");
			System.out.println("8. 사원 정보 수정");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴를 선택해 주세요. > ");
			
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : findAll(); break;
			case 2 : findDept(); break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 7 : break;
			case 8 : break;
			case 9 : System.out.println("프로그램을 종료합니다.");return;
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}	
	
	private void findAll() {
			
		System.out.println("\n회원 전체 조회 서비스입니다.");
		List<Employee> emps = ec.findAll();
		System.out.println("\n조회된 총 회원수는 " + emps.size() + "명 입니다.");
		if(emps.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
				
			for(Employee emp : emps) {
				System.out.println("==============================");
				System.out.println(emp.getEmpId() + "번 회원의 정보");
				System.out.print("사원명 : " + emp.getEmpName() + ", ");
				System.out.print("급여 : " + emp.getSalary() + ", ");
				System.out.print("부서명 : " + emp.getDeptCode() + ", ");
				System.out.print("직급명 : " + emp.getJobCode() + ", ");
				System.out.println();
			}
		}
	}

	private void findDept() {
		
		System.out.println("\n부서가 동일한 사원 조회 서비스입니다. ");
		System.out.print("부서를 입력해주세요. > ");
		String title = sc.nextLine();
		
		List<Employee> emps = ec.findDept(title);
		
		if(emps.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			for(int i = 0; i < emps.size(); i++) {
				System.out.println((i+1) + "번 째 조회 결과!");
				System.out.println("사번 : " + emps.get(i).getEmpId() + 
								   ", 사원명 : " + emps.get(i).getEmpName() + 
								   ", 부서명 : " + emps.get(i).getDeptCode() + 
								   ", 직급명 : " + emps.get(i).getJobCode() + 
								   ", 급여 : " + emps.get(i).getSalary());
			}
		}
		
	}
		
		
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

