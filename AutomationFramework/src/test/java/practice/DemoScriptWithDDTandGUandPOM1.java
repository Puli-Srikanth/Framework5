package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ElementRepository.CreateOrganizationPage;
import ElementRepository.HomePage;
import ElementRepository.LoginPage;
import ElementRepository.OrganizationInfoPage;
import ElementRepository.OrganizationPage;
import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGUandPOM1 {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if(BROWSER.equals("firefox")) {
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
		hp.getOrganizationButton().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganization().click();
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		Random r = new Random();
		int random = r.nextInt(1000);
		cop.getAccountname().sendKeys(ORGNAME+random);
		cop.getSaveOrganization().click();
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String name = oip.getOrganizationInfo().getText();
		if(name.contains(ORGNAME)) {
			System.out.println(name + "----Passed");
		}else {
			System.out.println(name + "----Failed");
		}
		
		wutil.toMouseHover(driver, hp.getLogout());
		hp.getSignoutButton();
		driver.quit();
		
	}
}
