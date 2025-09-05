package com.kh;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRun {
	// 외부파일로부터 입력받을때 사용
	public static void main(String[] args) {

		Properties prop = new Properties();
		prop.setProperty("A", "B");
		
		try {
		//prop.store(new FileOutputStream("driver.properties"), "setting for DBMS");
			prop.storeToXML(new FileOutputStream("member-mammper.xml"), "MEMBER SQL");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
