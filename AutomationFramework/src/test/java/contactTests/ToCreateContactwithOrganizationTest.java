package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import ElementRepository.ContactInfoPage;
import ElementRepository.ContactPage;
import ElementRepository.CreateContactPage;
import ElementRepository.HomePage;
import GenericUtility.BaseClass;
import GenericUtility.ExcelFileUtility;
import GenericUtility.WebDriverUtility;

public class ToCreateContactwithOrganizationTest extends BaseClass {

	@Test(groups = "smoke")
	public void toCreateContactwithOrganization_005() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactButton().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();

		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastname().sendKeys(LASTNAME);

		ccp.getAddElement().click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toSwitchWindow(driver, "Accounts");

		driver.findElement(By.xpath("//a[text()='teddy']")).click();
		wutil.toSwitchWindow(driver, "Contacts");

		ccp.getSaveContact().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String name = cip.getContactInfo().getText();
		if (name.contains(LASTNAME)) {
			System.out.println(name + "---Passed");
		} else {
			System.out.println(name + "----Failed");
			System.out.println("----****----");
		}
	}
}
