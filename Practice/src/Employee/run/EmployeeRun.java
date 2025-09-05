package Employee.run;

import Employee.model.view.EmployeeView;
import common.JDBCTemplate;

public class EmployeeRun {

	public static void main(String[] args) {
		JDBCTemplate.registDriver();
		EmployeeView ev = new EmployeeView();
		ev.mainMenu();
		
	}
}
