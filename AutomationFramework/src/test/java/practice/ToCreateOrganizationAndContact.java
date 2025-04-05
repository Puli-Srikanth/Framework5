package practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateOrganizationAndContact {
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
		
		//Step 3 :- Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4 :- Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5 :- Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("leoog");
		
		//Step 6 :- Select the Organization from Organization look up image
		String parentId = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='account_id']/../..//img[@alt='Select']")).click();
		Set<String> allwindowsIds = driver.getWindowHandles();
		allwindowsIds.remove(parentId);
		for(String id:allwindowsIds) {
		driver.switchTo().window(id);
		}
		driver.findElement(By.id("1")).click();
		driver.switchTo().window(parentId);
		
		//Step 7 :- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(lastname.contains("leoog")) {
			System.out.println(lastname + "-----Passed");
		} else {
			System.out.println(lastname + "-----Failed");
		}
		
		//Step 8 :- Logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 9 :- Close Browser
		driver.quit();
		
		}
}
