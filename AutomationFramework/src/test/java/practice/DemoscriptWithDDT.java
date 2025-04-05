package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoscriptWithDDT {

	public static void main(String[] args) throws IOException {
		
		//To Read Data from propertyFile
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		//To Read Data from excel file
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\DDT.xlsx");	
		Workbook wb = WorkbookFactory.create(efis);
		String lastname = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
	
		//Step 1 :- Launch Browser
		WebDriver driver=null;
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step 2 :- Login with valid credentials
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3 :- Click on Contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4 :- Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5 :- Fill in mandatory details
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		//Step 6 :- Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(name.contains(lastname)) {
			System.out.println(name + "----Passed");
		} else {
			System.out.println(name + "----Failed");
		}
		
		//Step 7 :- Logout
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 8 :- Close the Browser
		driver.quit();
	}
}
