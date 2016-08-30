package TestNGPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.google.common.base.Function;

public class TestNGClass {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup(){
		//initiate the driver and launch the application under test
		driver = new AppDriver().getDriver();
		driver.get("http://www.cleartrip.com/");
		driver.manage().window().maximize();
	}
	
	@Test 
	public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {
		
		chooseToHaveAOneWayJourney();
		enterOriginAs("Bangalore");
		selectTheFirstAvailableAutoCompleteOption("ui-id-1");
				
		enterDestinationAs("Delhi");
		selectTheFirstAvailableAutoCompleteOption("ui-id-2");
				
		enterDepartureDate();
		
		searchForTheJourney();
		waitForSearchResultsToAppear();
		
		//verify that result appears for the provided journey search
		//Assert.assertTrue(isElementPresent(By.id("outbound")));
	}
	
	@Test
	public void testThatResultsAppearForAReturnJourney() throws InterruptedException{
	
		chooseToHaveAReturnJourney();

		enterOriginAs("Bangalore");
		selectTheFirstAvailableAutoCompleteOption("ui-id-1");
	
		enterDestinationAs("Delhi");
		selectTheFirstAvailableAutoCompleteOption("ui-id-2");
	
		enterDepartureDate();
		enterReturnDate();
	
		//all fields filled in. Now click on search
		searchForTheJourney();
		waitForSearchResultsToAppear();
	
		//verify that result appears for the provided journey search
		//Assert.assertTrue(isElementPresent(By.id("outbound")));
		//Assert.assertTrue(isElementPresent(By.id("return")));

	}
	
	@AfterMethod
	public void teardown() {
		//close the browser
		driver.close();
	}
	
	private void chooseToHaveAOneWayJourney() {
		driver.findElement(By.id("OneWay")).click();
	}
	
	private void chooseToHaveAReturnJourney() {
		driver.findElement(By.id("RoundTrip")).click();
	}	
	
	private void searchForTheJourney() {
		driver.findElement(By.id("SearchBtn")).click();
	}
	
	private void enterDepartureDate() {
		driver.findElement(By.id("DepartDate")).clear();
		driver.findElement(By.id("DepartDate")).sendKeys(tomorrow());
		Actions act1 = new Actions(driver);
		act1.sendKeys(Keys.TAB).build().perform();
	}
	
	private void enterReturnDate() {
		driver.findElement(By.id("ReturnDate")).clear();
		driver.findElement(By.id("ReturnDate")).sendKeys(dayAfterTomorrow());
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
	}
	
	private void enterDestinationAs(String destination) {
		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys(destination);
	}
			
	private void enterOriginAs(String origin) {
		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys(origin);
	}
	
	private void selectTheFirstAvailableAutoCompleteOption(String ID) {
		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(30, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		WebElement optionListElement = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ID)));
		
		//select the first item from the auto complete list
		WebElement originOptionsElement = optionListElement;
		List<WebElement> originOptions = originOptionsElement.findElements(By.tagName("li"));
		originOptions.get(0).click();
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
		
	public String tomorrow(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
	}
		
	public String dayAfterTomorrow(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 2);
		return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
	}
		
	public void waitForSearchResultsToAppear() throws InterruptedException {
		Thread.sleep(8000);
		WebElement ele = driver.findElement(By.id("modifySearchLink"));
		if (ele.isDisplayed()){
			System.out.println("displayed");
		}
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(visibilityOfElementLocated(By.id("modifySearchLink")));
	}
	
	public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement toReturn = driver.findElement(locator);
				if (toReturn.isDisplayed()) {
					return toReturn;
				}
				return null;
			}
		};
	}
 
}
