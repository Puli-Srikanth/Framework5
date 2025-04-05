package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoScript1 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\DDT.xlsx");	
		Workbook wb = WorkbookFactory.create(efis);
		String orgname = wb.getSheet("Organization").getRow(1).getCell(2).toString();
		
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
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys(orgname+random);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("")).getText();
		if(name.contains(orgname+random)) {
			System.out.println(name+"----Passed");
		} else {
			System.out.println(name+"----Failed");
		}
	}
}
