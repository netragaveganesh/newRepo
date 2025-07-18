package OrganisationModule;

import java.io.IOException;
import java.time.Duration;
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

public class CreatOrgWithPhoneNumTest {

	@Test
	public void creatOrgWithPhoneNumTest() throws InterruptedException, EncryptedDocumentException, IOException {
		// Fetch the data from properties File
//		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//		Properties p = new Properties();
//		p.load(pfis);
//		String un = p.getProperty("un");
//		String pwd = p.getProperty("pwd");
//		String timeouts = p.getProperty("timeouts");
//		Long time = Long.parseLong(timeouts);
		
		ProperFileUtilities p= new ProperFileUtilities();
		String un=p.fetchDataFromPropFile("un");
		String pwd =p.fetchDataFromPropFile("pwd");
		String timeouts=p.fetchDataFromPropFile("timeouts");
		String url=p.fetchDataFromPropFile("url");
		Long time = Long.parseLong(timeouts);

		JavaUtilities jutility= new JavaUtilities();
		int rnum=jutility.generateRandomNumber();
		
		// Fetch data from Excel regular way of fetching data
//		FileInputStream efis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
//		Workbook wb = WorkbookFactory.create(efis);
//		String orgName = wb.getSheet("Org_Data").getRow(4).getCell(2).toString();
//		String orgPhone = wb.getSheet("Org_Data").getRow(4).getCell(3).toString();
		//Advance/updated way of fetching data
		ExcelFileUtilities ex= new ExcelFileUtilities();
		String orgName =ex.fetchDataFromExcelFile("Org_Data", 4, 2)+rnum;
		String orgPhone =ex.fetchDataFromExcelFile("Org_Data", 4, 3)+rnum;
		ex.closeExcel();
		
		// Launch the browser
		WebDriver driver = new ChromeDriver();

		// Mximise the window
//		driver.manage().window().maximize();
		WebDriverUtilities wutility= new WebDriverUtilities();
		wutility.maximizeWindow(driver);
		
		// Give implict wait of 10 sec
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		wutility.waitImplicitly(driver, timeouts);
		
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

		// Identify mobile number text field and enter the text
		driver.findElement(By.id("phone")).sendKeys(orgPhone);

		// Click on save button
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		Thread.sleep(2000);
		// Verify/validate newly created organization name
		WebElement neworg = driver.findElement(By.id("dtlview_Organization Name"));

		if (neworg.getText().contains(orgName)) {
			System.out.println("Create organization with phone number test pass");
		} else {
			System.out.println("Create organization with phone number test fail");
		}

		// Verify valid phone number in newly created organization
		WebElement phno = driver.findElement(By.id("dtlview_Phone"));
		if (phno.getText().contains(orgPhone)) {
			System.out.println("Valid Phone Number");
		} else {
			System.out.println("Invalid phone number");
		}

		// identify organization link and click on it
//		driver.findElement(By.linkText("Organizations")).click();
		homepage.getOrganizationBtn();
		
		Thread.sleep(2000);
		// Identify the newly created oranization and click on delete
		driver.findElement(By
				.xpath("(//a[text()='" + orgName + "'])[2]/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);
		// Handle the confirmation Popup
//		Alert al = driver.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();
		wutility.handleAlertPopUpClickOnOk(driver);

		Thread.sleep(2000);
		// Identify profile button and mouseover on it
		WebElement profile = driver.findElement(By.xpath("//td[contains(@onmouseout,\"fnHideDrop('o\")]"));

		//LOGOUT SCENARIO
//		Actions act = new Actions(driver);
//		act.moveToElement(profile).perform();
//		wutility.mouseOverOnElement(driver, profile);
//
		Thread.sleep(2000);
//		// click on logout button
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.logout(driver);
		
		
		Thread.sleep(2000);
		// close the browser
//		driver.quit();
		wutility.closeBrowser(driver);
		
	}
}
