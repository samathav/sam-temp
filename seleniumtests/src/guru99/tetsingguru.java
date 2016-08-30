package guru99;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tetsingguru {
  
	public WebDriver driver = null;
	ProfilesIni profile = new ProfilesIni();
	FirefoxProfile FXprofile = profile.getProfile("default");
	XSSFWorkbook srcBook= null;
	XSSFSheet sourceSheet =null;
	XSSFRow sourceRow = null;
	String title = null;
	
	Util ut = new Util();
	
	@BeforeClass
	public void ActivateDriver(){
		System.setProperty("WebDriver.FirefoxDriver.driver", ut.driverpath);
		driver = new FirefoxDriver(FXprofile);
		driver.navigate().to(ut.baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Assert.assertEquals(ut.Hometitle, driver.getTitle());
	}
	
	@AfterClass
	public void DeactivateDriver() throws InterruptedException {
		Thread.sleep(15000);
		driver.quit();
	}
	
	public void ReadDataFromExcel(int rownm){
		sourceRow = sourceSheet.getRow(rownm);
    	XSSFCell cell1=sourceRow.getCell(0);
    	XSSFCell cell2=sourceRow.getCell(1);
    	
    	driver.findElement(By.name("uid")).sendKeys(cell1.getStringCellValue());
    	driver.findElement(By.name("password")).sendKeys(cell2.getStringCellValue());
    	driver.findElement(By.name("btnLogin")).click();
    	
    	waitCondition(driver);
    	
	}
	
	@Test
	public void Login() throws FileNotFoundException, InterruptedException {
		
		int rows = GetNumOfRows();
		
		for (int i=1; i<=rows; i++) {
			
			ReadDataFromExcel(i);
			Thread.sleep(4000);
        	//Assert.assertEquals(ut.Maintitle, driver.getTitle());
			
        	if ((driver.getTitle()).equals(ut.Maintitle)){ 
        		driver.findElement(By.xpath("html/body/div[2]/div/ul/li[15]/a")).click();
        		waitCondition(driver);
    		}else {
    			waitCondition(driver);
    		}
        	Thread.sleep(4000);
        }
		
	}
	
	public int GetNumOfRows() throws FileNotFoundException{
		try {
			srcBook = new XSSFWorkbook(ut.Filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}     
        sourceSheet = srcBook.getSheetAt(0);
        int rownum= sourceSheet.getLastRowNum();
        
        return rownum;
	}
	
	public void waitCondition (WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 2);
    	try{
    	    wait.until(ExpectedConditions.alertIsPresent());
    	    Alert alert = driver.switchTo().alert();
    	    alert.accept();
    	}
    	catch (Exception e){
    	    System.out.println("No alert");
    	}
	}
	
}
