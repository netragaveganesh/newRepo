package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOMPage {

	//Declaration
	@FindBy(name="user_name")
	private WebElement un;
	
	@FindBy(name="user_password")
	private WebElement pwd;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	
	@FindBy(linkText="vtiger")
	private WebElement header;
	
	
	//Initialization
	public LoginPOMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	
	//Utilization
	/**
	 * This Method is used to enter the user name in login UserName Text field, user needs to pass the UN during execution
	 * @param userName
	 */
	public void getUn(String userName) {
		  un.sendKeys(userName);;
	}

	
	/**
	 * This Method is used to enter the user password in password Text field, user needs to pass the password during execution
	 * @param userPwd
	 */
	public void getPwd(String userPwd) {
		 pwd.sendKeys(userPwd);
	}


	/**
	 * This method is used to click on logout button
	 */
	public void getLoginbtn() {
		 loginbtn.click();
	}


	/**
	 * This method is used to get the Login page Header text
	 * @return String Header text
	 */
	public String getHeader() {
		return header.getText();
	}
	
	/**
	 * Using this method we can login
	 * @param userName
	 * @param userPwd
	 */
	public void loginToApp(String userName, String userPwd) {
		un.sendKeys(userName);
		pwd.sendKeys(userPwd);
		loginbtn.click();
	}
	
	
}
