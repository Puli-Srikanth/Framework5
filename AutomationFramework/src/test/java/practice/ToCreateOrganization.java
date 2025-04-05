package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateOrganization {
	public static void main(String[] args) {
		
		//Step 1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step 2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3 :- Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4 :- Click on create Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 5 :- Create Organization with mandatory fields
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("teddy"+random);
		
		//Step 6 :- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String accountname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(accountname.contains("teddy"+random)) {
			System.out.println(accountname + "-----Passed");
		} else {
			System.out.println(accountname + "-----Failed");
		}
		
		//Step 7 :- Logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 8 :- Close Browser
		driver.quit();
		
	}
}
