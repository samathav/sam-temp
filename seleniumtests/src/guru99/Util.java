package guru99;

import org.openqa.selenium.WebDriver;

public class Util {
  
	public WebDriver driver;
	String driverpath = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	String baseURL = "http://www.demo.guru99.com/V4/";
	String Hometitle = "Guru99 Bank Home Page";
	String Maintitle = "Guru99 Bank Manager HomePage";
	String Filepath = "C:\\Users\\Samatha\\Workspace\\Logindata.xlsx";
	
	public void Util(){
		this.driver = driver;
	}
	

}
