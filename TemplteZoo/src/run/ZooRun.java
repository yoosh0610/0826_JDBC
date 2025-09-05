package run;

import common.JDBCTemplate;
import view.ZooView;

public class ZooRun {

	public static void main(String[] args) {
		JDBCTemplate.registDriver();
		ZooView zv = new ZooView();
		zv.mainMenu();
		
	}

}
