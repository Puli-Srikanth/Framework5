package ElementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {

	//Constructor
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastname;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveContact;

	@FindBy(xpath="//input[@name='account_name']/../..//img[@title='Select']")
	private WebElement addElement;
	
//	@FindBy(xpath = "//input[@name='account_id']/../..//img[@alt='Select']")
//	private WebElement addeleElement1;
	
	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getSaveContact() {
		return saveContact;
	}

	public WebElement getAddElement() {
		return addElement;
	}

//	public WebElement getAddeleElement1() {
//		return addeleElement1;
//	}
	
}
