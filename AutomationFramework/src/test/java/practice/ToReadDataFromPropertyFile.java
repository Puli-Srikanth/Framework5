package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ToReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		
		//Create object of fis
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
		//Create object of property file
		Properties prop = new Properties();
		
		//Call methods
		prop.load(fis);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String browser = prop.getProperty("browser");
		
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(browser);
	}
}