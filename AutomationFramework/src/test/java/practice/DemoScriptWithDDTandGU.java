package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGU {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//Read data from propertyfile
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//Read data from Excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		wutil.toMaximize(driver);
		wutil.toImplicitWait(driver);
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(name.contains(LASTNAME)) {
			System.out.println(name + "----Passed");
		}else {
			System.out.println(name + "----Failed");
		}
		
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logout);
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
	}
}
