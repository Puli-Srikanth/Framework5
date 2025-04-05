package ElementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	//Constructor
			public ContactPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}
			
			@FindAll({@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']"),@FindBy(xpath="//img[@title='Create Contact...']")})
			private WebElement createContact;

			public WebElement getCreateContact() {
				return createContact;
			}
}
