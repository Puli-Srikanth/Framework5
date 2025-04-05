package ElementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	//Constructor
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindAll({@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']"),@FindBy(xpath="//img[@title='Create Organization...']")})
	private WebElement createOrganization;
	
	public WebElement getCreateOrganization() {
		return createOrganization;
		
	}
	
}
