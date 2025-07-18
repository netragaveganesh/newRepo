package ContactModule;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class Create_ContactWithStartAndEndDateTest {

	@Test
	public void createContactWitStartAndEndDate() throws Exception {
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
		
		// Fetch data from Excel
//		FileInputStream efis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
//		Workbook wb = WorkbookFactory.create(efis);
//		String contName = wb.getSheet("Cont_Data").getRow(4).getCell(2).toString();
		//Advance/updated way of fetching data
		ExcelFileUtilities ex= new ExcelFileUtilities();
		String contName =ex.fetchDataFromExcelFile("Cont_Data", 4, 2)+rnum;
		ex.closeExcel();
		
		Thread.sleep(2000);
		
		// Launch the browser
		WebDriver driver = new ChromeDriver();

		// Mximise the window
//		driver.manage().window().maximize();
		WebDriverUtilities wutility = new WebDriverUtilities();
		wutility.maximizeWindow(driver);
		
		// Give implict wait of 10 sec
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
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
		// Identfy Contact link and click on it
//		driver.findElement(By.linkText("Contacts")).click();
		HomePOMPage homepage=new HomePOMPage(driver);
		homepage.getContactsBtn();

		Thread.sleep(2000);
		// Identify and click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		Thread.sleep(2000);
		// Identify last name TF and enter the text
		driver.findElement(By.name("lastname")).sendKeys(contName);

		// identify support start date TF
		WebElement suppStartDateTF = driver.findElement(By.name("support_start_date"));
		suppStartDateTF.clear();

		// Fetch the current date
//		Date dobj = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String startDate = sdf.format(dobj);
		String startDate=jutility.getSystemCurrentDate();
		suppStartDateTF.sendKeys(startDate);
		System.out.println(startDate);

		// Identify support end date TF
		WebElement suppEndDateTF = driver.findElement(By.name("support_end_date"));
		suppEndDateTF.clear();

		// Fetch the date after 30 days
//		Date dobj = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String startDate = sdf.format(dobj);
//		Calendar cal = sdf.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH, 30);
//		String endDate = sdf.format(cal.getTime());
		String endDate =jutility.getDateAfterSpecificDays(60);		
		suppEndDateTF.sendKeys(endDate);
		System.out.println(endDate);

		Thread.sleep(2000);
		// Click on save button
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		Thread.sleep(2000);
		// Verify newly created contact is present or not
		WebElement newCont = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));

		if (newCont.getText().contains(contName)) {
			System.out.println("Create contact with name test pass");
		} else {
			System.out.println("Create contact with name test fail");
		}

		// Verify Contact with start date
		WebElement verifyStartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']"));
		if (verifyStartDate.getText().contains(startDate)) {
			System.out.println("Start date test is pass");
		} else {
			System.out.println("Start date test is fail");
		} 

		// Verify contact with end date
		WebElement verifyEndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']"));
		if (verifyEndDate.getText().contains(endDate)) {
			System.out.println("EndDate test pass");
		} else {
			System.out.println("EndDate test pass");
		}

		// Identfy Contact link and click on it
		//driver.findElement(By.linkText("Contacts")).click();
		homepage.getContactsBtn();
		

//identify and Delete newly created contact
		driver.findElement(
				By.xpath("//a[text()='" + contName + "']/ancestor::tr[@onmouseout]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(2000);
// Handle the confirmation Popup
//		Alert al = driver.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();
		wutility.handleAlertPopUpClickOnOk(driver);

		Thread.sleep(2000);
// Identify profile button and mouseover on it
//		WebElement profile = driver.findElement(By.xpath("//td[contains(@onmouseout,\"fnHideDrop('o\")]"));

			//LOGOUT SCENARIO
//		Actions act = new Actions(driver);
//		act.moveToElement(profile).perform();
//		wutility.mouseOverOnElement(driver, profile);
//
//// Thread.sleep(2000);
//// click on logout button
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.logout(driver);

		Thread.sleep(2000);
// close the browser
//		driver.quit();
		wutility.closeBrowser(driver);

	}
}
