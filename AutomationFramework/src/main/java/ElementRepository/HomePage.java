package ElementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//Constructor
		public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(linkText = "Contacts")
		private WebElement contactButton;
		
		@FindBy(linkText="Organizations")
		private WebElement organizationButton;
		
		@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
		private WebElement logout;
		
		@FindBy(linkText = "Sign Out")
		private WebElement signoutButton;
		
		public WebElement getContactButton() {
			return contactButton;
		}

		public WebElement getOrganizationButton() {
			return organizationButton;
		}
		
		public WebElement getLogout() {
			return logout;
		}

		public WebElement getSignoutButton() {
			return signoutButton;
		}
		
}
