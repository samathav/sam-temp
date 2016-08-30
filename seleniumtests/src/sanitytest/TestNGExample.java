package sanitytest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGExample {
	
	public WebDriver driver;
	private String baseURL;

  @Test(timeOut=20000)
  public void launchGoogleAndLogin() throws InterruptedException
  {
	    WebElement ele;
	    driver.get(baseURL);  
	    
	    try { if (isElementPresent(By.linkText("Gmail"))) 
	        driver.findElement(By.linkText("Gmail")).click();
	     } catch (Exception e) {}
	        	  
	     Thread.sleep(1000);
	    
	     driver.findElement(By.id("Email")).sendKeys("samathav71@gmail.com");
	     driver.findElement(By.id("next")).click();
	     Thread.sleep(3000);
	     driver.findElement(By.id("Passwd")).sendKeys("ramana66");
	     driver.findElement(By.id("signIn")).click();
  }
  
  @Test(dependsOnMethods={"launchGoogleAndLogin"})
  public void SendEmail() throws InterruptedException {
	  	Thread.sleep(5000);
		driver.findElement(By.xpath("//*[contains(text(),'COMPOSE')]")).click();
		
		Thread.sleep(8000);
		driver.findElement(By.id(":oj")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id(":oj")).sendKeys("samathav71@gmail.com");
		Thread.sleep(5000);
		driver.findElement(By.name("subjectbox")).sendKeys("test");
		Thread.sleep(5000);
		driver.findElement(By.id(":p8")).sendKeys("this is to test selenium code");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("html/body/div[14]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/div/div/div[4]/table/tbody/tr/td[1]/div/div[2]")).click();
}

  @Test(timeOut=30000, dependsOnMethods={"SendEmail"})
  public void Logout() throws InterruptedException {   
        //try { if (isElementPresent(By.xpath(".//*[@id='gb']/div[2]/div[1]/div[2]/div[4]/div[1]/a/span")))
        	driver.findElement(By.xpath(".//*[@id='qb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")).click();
        	driver.findElement(By.xpath(".//*[@id='qb_71']")).click();
         // break; } catch (Exception e) {}
  }
  
  
  @BeforeClass
  public void setupBeforeLogin() {
     baseURL = "http://google.com";
    try {
     driver = new FirefoxDriver();
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     } catch (Exception e) {
    	 System.out.println("here2");
      throw new IllegalStateException("Can't start Web Driver", e);
    }
    
  }
  
  @AfterClass
  public void setupAfterLogin() {
    //driver.close();
    //driver.quit();
  }
    
    private boolean isElementPresent(By by) {
        try {
          driver.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
   }
}
  
