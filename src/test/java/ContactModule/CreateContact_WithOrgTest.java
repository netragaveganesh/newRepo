package ContactModule;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtilities;
import GenericUtilities.JavaUtilities;
import GenericUtilities.ProperFileUtilities;
import GenericUtilities.WebDriverUtilities;
import POMUtilities.HomePOMPage;
import POMUtilities.LoginPOMPage;

public class CreateContact_WithOrgTest {

	@Test
	public void createContactWithorg() throws EncryptedDocumentException, IOException, InterruptedException {
		// Fetch the data from properties File
//				FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//				Properties p = new Properties();
//				p.load(pfis);
//				String un = p.getProperty("un");
//				String pwd = p.getProperty("pwd");
//				String timeouts = p.getProperty("timeouts");
//				Long time = Long.parseLong(timeouts);

		ProperFileUtilities p = new ProperFileUtilities();
		String un = p.fetchDataFromPropFile("un");
		String pwd = p.fetchDataFromPropFile("pwd");
		String timeouts = p.fetchDataFromPropFile("timeouts");
		String url=p.fetchDataFromPropFile("url");
		Long time = Long.parseLong(timeouts);

		JavaUtilities jutility= new JavaUtilities();
		int rnum=jutility.generateRandomNumber();
		
		// Fetch data from Excel
//		FileInputStream efis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
//		Workbook wb = WorkbookFactory.create(efis);
//		String orgName = wb.getSheet("Cont_Data").getRow(7).getCell(2).toString();
//		String contName = wb.getSheet("Cont_Data").getRow(7).getCell(3).toString();
		//Advance/updated way of fetching data
		ExcelFileUtilities ex=new ExcelFileUtilities();
		String orgName =ex.fetchDataFromExcelFile("Cont_Data", 7, 2)+rnum;
		String contName =ex.fetchDataFromExcelFile("Cont_Data", 7, 3)+rnum;
		ex.closeExcel();

		// Launch the browser
		WebDriver driver = new ChromeDriver();

		// Mximise the window
//		driver.manage().window().maximize();
		WebDriverUtilities wutility = new WebDriverUtilities();
		wutility.maximizeWindow(driver);
		
		// Give implict wait of 10 sec
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

		// Navigate to an application
//		driver.get("http://localhost:8888/");
		wutility.navigateToApp(driver, url);
		
		Thread.sleep(3000);
		//LOGIN SCENARIO
		// identify username TF and enter user name
//		driver.findElement(By.name("user_name")).sendKeys(un);
//
//		// Identify password TF and enter text
//		driver.findElement(By.name("user_password")).sendKeys(pwd);
//
//		// Identify login button and click on it
//		driver.findElement(By.id("submitButton")).click();
		LoginPOMPage login= new LoginPOMPage(driver);
		login.loginToApp(un, pwd);
		
		

		Thread.sleep(2000);
		// identify organization link and click on it
//		driver.findElement(By.linkText("Organizations")).click();
		HomePOMPage homepage=new HomePOMPage(driver);
		homepage.getOrganizationBtn();
		
		Thread.sleep(2000);
		// identify and Click on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		Thread.sleep(2000);
		// Identify organization text filed and enter text
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		// Click on save button
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		Thread.sleep(2000);
		// Verify/validate newly created organization name
		WebElement neworg = driver.findElement(By.id("dtlview_Organization Name"));

		if (neworg.getText().contains(orgName)) {
			System.out.println("Create organization test pass");
		} else {
			System.out.println("Create organization test fail");
		}

		Thread.sleep(2000);
		// Identify Contact link and click on it
//		driver.findElement(By.linkText("Contacts")).click();
		homepage.getContactsBtn();
		
		
		Thread.sleep(2000);
		// Identify and click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		Thread.sleep(2000);
		// Identify last name TF and enter the text
		driver.findElement(By.name("lastname")).sendKeys(contName);

		// Get parent window id
//		String pwid = driver.getWindowHandle();
		String pwid=wutility.getParentWindowId(driver);
		
		// Identify Add organization button and click on it
		driver.findElement(By.xpath("//img[@title='Select']")).click();

//		Set<String> windows = driver.getWindowHandles();
		Set<String> windows=wutility.getAllWindowIds(driver);
//		for (String s : windows) {
//			driver.switchTo().window(s);
//			if (driver.getCurrentUrl().contains("module=Accounts&action=Popup")) {
		wutility.switchToChildWindowUsingUrl(driver, windows);
				// Identify search field and enter organisation name
				driver.findElement(By.id("search_txt")).sendKeys(orgName);
				// Identify search button and click on it
				driver.findElement(By.name("search")).click();
				// click on the desired organisation
				driver.findElement(By.linkText(orgName)).click();

			
		

		// switch back to main window
		driver.switchTo().window(pwid);
		
		Thread.sleep(2000);
		// Click on save button
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		// Verify newly created contact with organization is created or not from contact
		// information page
		WebElement newCont = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		WebElement newOrg = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a"));

		if (newCont.getText().contains(contName)) {
			System.out.println("Contact  test pass");
		} else {
			System.out.println("Contact  test fail");
		}
		if (newOrg.getText().contains(orgName)) {
			System.out.println("Contact with organization test pass");
		} else {
			System.out.println("Contact with organization test fail");
		}

		// Identfy Contact link and click on it
		//driver.findElement(By.linkText("Contacts")).click();
		homepage.getContactsBtn();
		

		// identify and Delete newly created contact from conatact page
		driver.findElement(
				By.xpath("//a[text()='" + contName + "']/ancestor::tr[@onmouseout]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);
		// Handle the confirmation Popup
//		Alert al = driver.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();
		wutility.handleAlertPopUpClickOnOk(driver);

		// identify organization link and click on it
		//driver.findElement(By.linkText("Organizations")).click();
		homepage.getOrganizationBtn();
		
		Thread.sleep(2000);
		// Identify the newly created oranization and click on delete
		driver.findElement(By
				.xpath("(//a[text()='" + orgName + "'])[2]/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);
		// Handle the confirmation Popup
//		Alert al1 = driver.switchTo().alert();
//		System.out.println(al1.getText());
//		al1.accept();
		wutility.handleAlertPopUpClickOnOk(driver);

		Thread.sleep(2000);
		// Identify profile button and mouseover on it
//		WebElement profile = driver.findElement(By.xpath("//td[contains(@onmouseout,\"fnHideDrop('o\")]"));

		//LOGOUT SCENARIO
//		Actions act = new Actions(driver);
//		act.moveToElement(profile).perform();
//		wutility.mouseOverOnElement(driver, profile);
//
//		// Thread.sleep(2000);
//		// click on logout button
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.logout(driver);
		
		Thread.sleep(2000);
		// close the browser
//		driver.quit();
		wutility.closeBrowser(driver);

	}
}
