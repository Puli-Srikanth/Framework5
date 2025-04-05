package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v114.browser.Browser;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ElementRepository.ContactInfoPage;
import ElementRepository.ContactPage;
import ElementRepository.CreateContactPage;
import ElementRepository.HomePage;
import ElementRepository.LoginPage;
import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGUandPOM {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//Read data from property file
		
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//Read data from excel file
		
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		wutil.toMaximize(driver);
		wutil.toImplicitWait(driver);
		
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getpasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		HomePage hp = new HomePage(driver);
		hp.getContactButton().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();
		
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastname().sendKeys(LASTNAME);
		
		ccp.getSaveContact().click();
		
		ContactInfoPage cip = new ContactInfoPage(driver);
		String name = cip.getContactInfo().getText();
		if(name.contains(LASTNAME)) {
			System.out.println(name + "----Passed");
		} else {
			System.out.println(name + "----Failed");
		}
		
		wutil.toMouseHover(driver, hp.getLogout());
		hp.getSignoutButton().click();
		
		driver.quit();
		
	}
}
