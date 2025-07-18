package ContactModule;

import java.io.IOException;
import java.time.Duration;
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

public class Create_ContactTest {

	@Test
	public void createContactTest() throws IOException, InterruptedException {
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
		String url=p.fetchDataFromPropFile("url");
		String timeouts=p.fetchDataFromPropFile("timeouts");
		
		JavaUtilities jutility= new JavaUtilities();
		int rnum=jutility.generateRandomNumber();

		// Fetch data from Excel
//		FileInputStream efis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
//		Workbook wb = WorkbookFactory.create(efis);
//		String contName = wb.getSheet("Cont_Data").getRow(1).getCell(2).toString();
		//Advance/updated way of fetching data
		ExcelFileUtilities ex= new ExcelFileUtilities();
		String contName = ex.fetchDataFromExcelFile("Cont_Data", 1, 2)+rnum;
		//ex.closeExcel();
		
		// Launch the browser
		WebDriver driver = new ChromeDriver();

		// Mximise the window
		WebDriverUtilities wutility=new WebDriverUtilities();
		wutility.maximizeWindow(driver);

		// Give implict wait of 10 sec
		wutility.waitImplicitly(driver, timeouts);

		// Navigate to an application
//		driver.get("http://localhost:8888/");
		wutility.navigateToApp(driver, url);

		Thread.sleep(3000);
		//LOGIN SCENARIO
//		// identify username TF and enter user name
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
		//Identfy Contact link and click on it
//		driver.findElement(By.linkText("Contacts")).click();
		HomePOMPage homepage=new HomePOMPage(driver);
		homepage.getContactsBtn();
		
		
		Thread.sleep(2000);
		//Identify and click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		Thread.sleep(2000);
		//Identify last name TF and enter the text
		driver.findElement(By.name("lastname")).sendKeys(contName);
		
		Thread.sleep(2000);
		// Click on save button
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		Thread.sleep(2000);
		//Verify newly created contact is present or not
		WebElement newCont=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		
		if(newCont.getText().contains(contName)) {
			System.out.println("Create contact with name test pass");
		}
		else {
			System.out.println("Create contact with name test fail");
		}
		
		//Identfy Contact link and click on it
//		driver.findElement(By.linkText("Contacts")).click();
		homepage.getContactsBtn();
				
		//identify and Delete newly created contact
		driver.findElement(By.xpath("//a[text()='"+contName+"']/ancestor::tr[@onmouseout]/descendant::a[text()='del']")).click();
		
		
		Thread.sleep(2000);
		// Handle the confirmation Popup
//		Alert al = driver.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();
		wutility.handleAlertPopUpClickOnOk(driver);

		Thread.sleep(2000);
		// Identify profile button and mouseover on it
		WebElement profile = driver.findElement(By.xpath("//span[text()=' Administrator']/../../descendant::img"));

//		Actions act = new Actions(driver);
//		act.moveToElement(profile).build().perform();
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
