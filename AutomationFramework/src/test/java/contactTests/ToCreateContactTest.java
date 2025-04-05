package contactTests;

import static org.testng.Assert.fail;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ElementRepository.ContactInfoPage;
import ElementRepository.ContactPage;
import ElementRepository.CreateContactPage;
import ElementRepository.HomePage;
import GenericUtility.BaseClass;
import GenericUtility.ExcelFileUtility;

@Listeners(GenericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {

	@Test //(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactButton().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastname().sendKeys(LASTNAME);
		ccp.getSaveContact().click();
		//Fail
		//Assert.fail();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String name = cip.getContactInfo().getText();
//		if(name.contains(LASTNAME)) {
//			System.out.println(name + "---Passed");
//		} else {
//			System.out.println(name + "----Failed");
//		}
		Assert.assertTrue(name.contains(LASTNAME));
	}
}
