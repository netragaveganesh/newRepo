package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtilities;

public class HomePOMPage {

	//Declaration
	@FindBy(linkText="Contacts")
	private WebElement contactsBtn;
	
	@FindBy(xpath="//span[text()=' Administrator']/../../descendant::img")
	private WebElement admin;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signOut;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationBtn;
	
	@FindBy(partialLinkText="Home")
	private WebElement header;
	
	
	
	
	//Initialization
	public HomePOMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}




	//Utilization
	/**
	 * this method is used to click on contacts link/button on home page
	 */
	public void getContactsBtn() {
		 contactsBtn.click();
	}


	/**
	 * this method returns admin button webElement of home page
	 * @return
	 */
	public WebElement getAdmin() {
		return admin;
	}

	/**
	 * this method is used to click on logout button on home page
	 */
	public void getSignOut() {
		 signOut.click();
	}

	/**
	 * this method is used to click on organization link/button on home page
	 */
	public void getOrganizationBtn() {
		 organizationBtn.click();;
	}

	/**
	 * This method is used to fetch the Header text of Home page
	 * @return Header text in string format
	 */
	public String getHeader() {
		return header.getText();
	}
	
	
	//Business logic method
	/**
	 * This method is used to logout from application, using this method we can perform all the actions
	 * such as mouse over and click on logout button
	 * @param driver
	 */
	public void logout(WebDriver driver) {
		WebDriverUtilities wutility= new WebDriverUtilities();
		wutility.mouseOverOnElement(driver, admin);
		signOut.click();
	}
	
	
}
