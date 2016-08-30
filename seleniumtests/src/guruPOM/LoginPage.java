package guruPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPage {
	
	String baseURL = "http://www.demo.guru99.com/V4/";
	String driverpath = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	String Hometitle = "Guru99 Bank Manager HomePage";
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	public WebElement usernm;
	
	@FindBy(name="password")
	public WebElement passwd;
	
	@FindBy(name="btnLogin")
	public WebElement loginbtn;
	
	public void LoginGuru(String username, String password) {
		// TODO Auto-generated method stub
		usernm.sendKeys(username);
		passwd.sendKeys(password);
		loginbtn.click();
	}
	
}
