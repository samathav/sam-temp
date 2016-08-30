package guruPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class MainMenuPage {
  
	public WebDriver driver;
	
	public MainMenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
	WebElement MainPageUserName;
	
	@FindBy(xpath="html/body/div[2]/div/ul/li[15]/a")
	WebElement Logout;
	
	public String MainPageManagerUserName(){
        return MainPageUserName.getText();
       }
	
	public void MainPageLogout() {
		// TODO Auto-generated method stub
		Logout.click();
	}
}
