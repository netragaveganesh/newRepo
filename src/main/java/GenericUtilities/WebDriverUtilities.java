package GenericUtilities;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities {

	/**
	 * This method is used to maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to give implicit wait of 10 sec
	 * @param driver
	 * @param timeouts
	 */
	public void waitImplicitly(WebDriver driver, String timeouts) {
		Long time = Long.parseLong(timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	/**
	 * This method is used to navigate to an application
	 * @param driver
	 * @param url
	 */
	public void navigateToApp(WebDriver driver,String url) {
		driver.get(url);
	}
	
	/**
	 * This method is used to handle alert popup and click on ok on it
	 * @param driver
	 */
	public void handleAlertPopUpClickOnOk(WebDriver driver) {
		Alert al=driver.switchTo().alert();
		al.accept();
	}
	
	/**
	 * This method is used to Move the cursor on webElement
	 * @param driver
	 * @param element
	 */
	public void  mouseOverOnElement(WebDriver driver,WebElement element) {
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to close the browser
	 * @param driver
	 */
	public void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
	/**
	 * This method is used to handle the industry DD and select the industry by using value
	 * @param industryDD
	 * @param indName
	 */
	public void selectIndustryByValue(WebElement industryDD,String indName) {
		Select sl= new Select(industryDD);
		sl.selectByValue(indName);
	}
	
	/**
	 * This method is used to select the industry Type
	 * @param indTypeDD
	 * @param indType
	 */
	public void selectIndustryType(WebElement indTypeDD, String indType) {
		Select sl= new Select(indTypeDD);
		sl.selectByValue(indType);
	}
	
	/**
	 * This method is used to select start/current date
	 * @return String value i.e startDate
	 */
	public String selectStartDate() {
		Date dobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(dobj);
		return startDate;
	}
	
	/**
	 * This method is used to select Enddate(i.e,after 30 days from current selected date)  
	 * @return end Date
	 */
	public String selectEndDate() {
		Date dobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(dobj);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sdf.format(cal.getTime());
		return endDate;
	}
	
	/**
	 * This method is used to get the parent window id
	 * @param driver
	 * @return String id
	 */
	public String getParentWindowId(WebDriver driver) {
		String pwid = driver.getWindowHandle();
		return pwid;
	}
	
	/**
	 * This Method is used to get All the window id's 
	 * @param driver
	 * @return all window id's
	 */
	public Set<String> getAllWindowIds(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		return windows;
	}
	
	/**
	 * This method is used to Switch to the child windowUsing URL
	 * @param driver
	 * @param windows
	 */
	public void switchToChildWindowUsingUrl(WebDriver driver, Set<String> windows) {
		for (String s : windows) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains("module=Accounts&action=Popup")) {
				break;
			}
		}		
	}
	
	/**
	 * This method is used to switch to Child window using title
	 * @param driver
	 * @param windows
	 * @param title
	 */
	public void switchToChildWindowUsingTitle(WebDriver driver, Set<String> windows, String title) {
		for(String s: windows) {
			if(driver.getTitle().contains(title)) {
				break;
			}
		}
	}
	
	public void waitUntilElementIsVisible(WebDriver driver,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitUntilElementIsClickable(WebDriver driver,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitUntilElementTitleIsVisible(WebDriver driver,String title) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	
	public void selectDDUsing_Index(WebElement element, int index) {
		Select s= new Select(element);
		s.selectByIndex(index);
	}
	
	public void selectDDByUsing_Text(WebElement element, String text) {
		Select s= new Select(element);
		s.selectByVisibleText(text);
	}
	
	public void handleAlertPopUpClickOnCancel(WebDriver driver) {
		Alert a=driver.switchTo().alert();
		a.dismiss();	
	}
	
	public String handleAlertPopUpAndFetchTheText(WebDriver driver) {
		Alert a= driver.switchTo().alert();
		String alertText= a.getText();
		return alertText;		
	}
	
	public void handleAlertPopUpPassTheText(WebDriver driver, String text) {
		Alert a= driver.switchTo().alert();
		a.sendKeys(text);
	}
	
	public void switchToFrameByIndex(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrameByID_Name(WebDriver driver, String id_name) {
		driver.switchTo().frame(id_name);
	}
	
	public void switchToFrameByWebelement(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchBackToMainWindowFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	
	
}
