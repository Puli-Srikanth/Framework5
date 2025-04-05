package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;

public class DemoScriptWithGU {

	public static void main(String[] args) throws IOException {
	
		// To read Data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String url = putil.toReadDataFromPropertyFile("url");
		String browser = putil.toReadDataFromPropertyFile("browser");
		String username = putil.toReadDataFromPropertyFile("username");
		String password = putil.toReadDataFromPropertyFile("password");
		
		// To read Data from Excel
		ExcelFileUtility eutil = new ExcelFileUtility();
		String lastname = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
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
				
				driver.get(url);
				driver.findElement(By.name("user_name")).sendKeys(username);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				
				driver.findElement(By.linkText("Contacts")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				driver.findElement(By.name("lastname")).sendKeys(lastname);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(name.contains(lastname)) {
					System.out.println(name + "----Passed");
				} else {
					System.out.println(name + "----Failed");
				}
				
				WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions action = new Actions(driver);
				action.moveToElement(logout).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
				
				driver.quit();

	}

}
