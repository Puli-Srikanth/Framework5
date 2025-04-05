package GenericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ElementRepository.HomePage;
import ElementRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver;
	
	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuiteConfiguration() {
		Reporter.log("---DataBase connection establised---", true);
	}
	
	//@BeforeTest // Cross Browser Testing
	//@Parameters("browser") //Cross Browser Testing
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClassConfiguration(/*String BROWSER*/) throws IOException {
	String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver=driver;
		Reporter.log("Browser got Launched", true);
		wutil.toMaximize(driver);
		wutil.toImplicitWait(driver);
		driver.get(URL);
	}
	
	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getpasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
	}
	
	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethodConfiguration() {
		HomePage hp = new HomePage(driver);
		wutil.toMouseHover(driver, hp.getLogout());
		hp.getSignoutButton().click();
		Reporter.log("Logged out Successfully", true);
	}
	
	@AfterClass(groups = {"smoke","regression"})
	public void afterClassConfiguration() {
		Reporter.log("Browser got closed Successfully", true);
		driver.quit();
	}
	
	@AfterSuite(groups = {"smoke","regression"})
	public void afterSuiteConfiguration() {
		Reporter.log("---DataBase connection Disconnected---", true);
	}
	
}
