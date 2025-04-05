package organizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import ElementRepository.CreateOrganizationPage;
import ElementRepository.HomePage;
import ElementRepository.OrganizationInfoPage;
import ElementRepository.OrganizationPage;
import GenericUtility.BaseClass;
import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.WebDriverUtility;

public class ToCreateOrganizationwithTypeTest extends BaseClass {

	@Test(groups = "regression")
	public void toCreateOrganizationwithTypeTest_004() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationButton().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganization().click();
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2)+jutil.toGetRandomNumber();;
		
		cop.getAccountname().sendKeys(ORGNAME);
		
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toHandleDropdown(cop.getIndustrydropdown(), "Energy");
		wutil.toHandleDropdown(cop.getTypedropdown(), "Customer");
		
		cop.getSaveOrganization().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String name = oip.getOrganizationInfo().getText();
		if(name.contains(ORGNAME)) {
			System.out.println(name+"---Passed");
		} else {
			System.out.println(name+"---Failed");
		}
	}
}
