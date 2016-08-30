package guruPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class NewCustomerPage {

	public void NewCustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@Test
	public void AddCustomerInfo(WebDriver driver) {
		//PageFactory.initElements(driver, this);
	}
}
