package GenericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * This class consist of methods related to WebDriver like to handle dropdowns,
 * to handle frames, to wait for elements, to handle mouse and keyboard actions,
 * to switch windows
 */
public class WebDriverUtility {
/**
 * This method is used to maximize the browser provided driver
 * @param driver
 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to minimize
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait until the element loaded in WebPage (Implicit wait)
	 * @param driver
	 */
	public void toImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	/**
	 * This method will wait until element is clickable provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait until element is visible provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilVisiblityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to handle dropdown using value
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element,int index) {
	Select select = new Select(element);
	select.selectByIndex(index);
	}
	
	/**
	 * This method is used to handle dropdown using value
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method is used to handle dropdown using visible text
	 * @param value
	 * @param element
	 */
	public void toHandleDropdown(String value,WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	/**
	 * This method is used to perform mouse hover provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver,WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to perform right click provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver,WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	/**
	 * This method is used to perform double click provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to perform drag and drop provided driver and webelements
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}

	/**
	 * This method is used to handle frame using index
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to handle frame using Id or name
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}
	
	/**
	 * This method is used to handle from using webelement
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch back from frame
	 * @param driver
	 */
	public void toHandleBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to handle alert popup by accepting it
	 * @param driver
	 */
	public void toSwitchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to handle alert popup by dismissing it
	 * @param driver
	 */
	public void toSwitchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is used to capture text in alert popup and accept it
	 * @param driver
	 * @return
	 */
	public String toSwitchToAlertAndCaptureMessage(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String message = alertPopup.getText();
		alertPopup.accept();
		return message;
	}
	
	/**
	 * This method is used to take screenshot provided driver and screenshotname
	 * @param driver
	 * @param scrrenshotname
	 * @throws IOException 
	 */
	public String toTakeScreenshot(WebDriver driver, String scrrenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + scrrenshotname + ".png");
		FileHandler.copy(temp,src);
		String path = src.getAbsolutePath();
		return path;
	}
	
	/**
	 * This method is used to switch the driver control to window
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchWindow(WebDriver driver, String partialTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			String title = driver.switchTo().window(id).getTitle();
			if(title.contains(partialTitle)) {
				break;
			}
		}
	}
	
}
