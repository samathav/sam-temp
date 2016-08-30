package guruPOM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class LoginActivatePage {
	
	XSSFSheet worksheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;
	
	ProfilesIni  profile = new ProfilesIni();
	FirefoxProfile FXprofile = profile.getProfile("default");
	public WebDriver driver = new FirefoxDriver(FXprofile);
	
	LoginPage loginpg = new LoginPage(driver);
	Utils utility = new Utils();
	MainMenuPage MainPage; 
	
	@DataProvider(name="data")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = getExcelData(utility.Utils());
		return arrayObject;
	}

	public String[][] getExcelData(XSSFSheet xssfSheet) {
		
		String[][] arrayExcelData = null;
		int totalNoOfRows = xssfSheet.getLastRowNum();
		Row row = xssfSheet.getRow(0);
		int totalNoOfCols = row.getLastCellNum();
		
		arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
		
		for (int i= 1 ; i < totalNoOfRows; i++) {

			for (int j=0; j < totalNoOfCols; j++) {
				arrayExcelData[i-1][j] = xssfSheet.getRow(i).getCell(j).getStringCellValue();
			}

		} 
		return arrayExcelData;
	}
	
	@BeforeClass
	public void SetUp(){
		driver.get(loginpg.baseURL);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void ShutDown() throws InterruptedException{
		driver.close();
	}
	
	@Test (dataProvider ="data")
	public void ActivateSignIn(String username, String password) throws IOException, InterruptedException{
		
		loginpg.LoginGuru(username, password);
		waitCondition(driver);
		
		Thread.sleep(4000);
			
		MainPage = new MainMenuPage(driver);
		WebDriverWait wait1 = new WebDriverWait(driver, 2);
			
		try{
				wait1.until(ExpectedConditions.textToBePresentInElement(MainPage.MainPageUserName, MainPage.MainPageManagerUserName()));
				
				File Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.moveFile(Screenshot, new File("C:\\Users\\Samatha\\screenshot.png"));
						
				MainPage.MainPageLogout();
        		waitCondition(driver);
		}
		catch (Exception e){
		    	waitCondition(driver);
		}
		Thread.sleep(4000);
	}
	
	public int GetNumOfRows() throws IOException{    
	    worksheet = utility.Utils();
		int rownum = worksheet.getLastRowNum();    
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
