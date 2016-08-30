package guru99;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sample {

	static int a1=0, a2=1, a3;
	public static void fseries(int ct)
	{
		if (ct > 0)
		{
			a3 = a1+a2;
			a1 = a2;
			a2 = a3;
			
			System.out.print(a3+",");
			fseries(ct - 1);	
		}
	}
	
	public static int fact(int ct)
	{
		int result = 0;
		if (ct > 0)
		{
			if (ct == 1)
				return ct;
			result = fact(ct-1)*ct;
		}
		return result;
	}
//--------------------------------------------------------------
	
	public static void main(String[] args) throws InterruptedException {
		
		//WebDriver driver = new FirefoxDriver();
		//driver.get("http://www.amazon.in/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=laptops");
				
		//WebElement ele5 = driver.findElement(By.xpath(".//*[@id='result_3']/div/div[3]/div[1]/a/h2"));
		//String title1 = ele5.getText();
		//System.out.println(title1);
		//ele5.click();
		//Thread.sleep(10000);
		
		//Set<String> windows = driver.getWindowHandles();
		//for (String s:windows)
		//{
			//driver.switchTo().window(s);
			//String title = driver.getTitle();
			//System.out.println(title);			
			//if (title.contains("title1"))
			//{
				//break;
			//}
		//}
		
		
		//WebElement ele = driver.findElement(By.xpath(".//*[@id='sims-fbt-form']/div[3]/fieldset/ul/li[2]/span/span/div/label/input"));
		//WebElement ele1 = driver.findElement(By.xpath(".//*[@id='sims-fbt-form']/div[3]/fieldset/ul/li[3]/span/span/div/label/input"));
						
		//if (ele.isSelected())
		//{
			//ele.click();
		//}  
		//if (ele1.isSelected())
		//{
			//ele1.click();
		//} 
		
		//driver.findElement(By.xpath(".//*[@id='sims-fbt-form']/div[1]/div/div[2]/div/span/span/input")).click();
//-------------------------------------------------------------------------------------		

	String str1 ="samatha";
	char []str2 = str1.toCharArray();
	
	int len = str2.length;
	int count = 0;
	
	for (int i=0; i<=len-1; i++)
	{
		count = 0;
		for (int j=0; j<=len-1; j++)
		{
			if (str2[i]==str2[j])
			{
				count = count + 1;
			}
		}
		System.out.print(str2[i]+": "+count);
		System.out.println("");
	}
	
	String name="pr%m*u";
	
    int len3=name.length();
    char []c1 = name.toCharArray();
    int len4 = len3/2;
		
    for(int i=0;i<len4;i++)
    {
    	char temp= c1[i];
    	c1[i] = c1[(len3-i)-1];
		c1[(len3-i)-1]=temp;
    }

    System.out.println("Swapping string is: ");
    System.out.println(c1);
//------------------------------------------------------------------------------------		
		
//--------------------------------------------------------------------------------------		

		// fibonocci series

		int a = 0; 
		int b=1;
		System.out.print(a +","+b+",");
		int temp=10;
		int c;
		
		for (int i = 2; i< temp; i++)
		{
			c = a + b;
			System.out.print(c+",");
			a = b;
			b = c;			
		}
		
		System.out.print(a1 +","+a2+",");
		int count1 = 10;
		
		fseries(count1-2);
		
		System.out.println("");
//------------------------------------------------------------
		//prime numbers
		
		for (int i=1; i<25; i++){
			boolean iprime = true;
			
			for (int j=2; j<i; j++) {		
				if (i%j == 0)
				{
					iprime = false;
					break;
				}
			}
			if (iprime)
			System.out.print(i+",");
		}
		
//------------------------------------------------------------
		//factorial
		
	int sum=1;
	for (int h=1; h<= 5; h++){
		sum = sum * h;
	}
	
	System.out.println("\n"+sum);
	
//-----------------------------------------------------------
	
	int sum1 = 1;
	int ctnum = 5;
	sum1 = fact(ctnum);
	System.out.println(sum1);
		
//------------------------------------------------------------
		//sorting of an array
		
		int [] array = {1,5,76,84,23,41,12,3,234};
		
		for (int i=1; i<array.length; i++) {
			for (int j=1; j<array.length; j++){
				if (array[j-1] > array[j])
				{
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
		for (int k = 0; k<array.length; k++){
		System.out.print(array[k] +",");
		}
//------------------------------------------------------
		//if a number is prime
		int num = 31; int m = num; int flag = 0;
		for (int j=2; j<num; j++)
		{
			if (num%j == 0)
			{
				System.out.println("is not prime");
				flag = 1;
				break;
			}
			
		}
		if (flag == 0){ System.out.println("is prime");}
		
//------------------------------------------------------------
		//Palindrome
		int revnum = 454; 
		int revnum1 = 0;
		int n;
		int g1 = 0;
		
		while (revnum > 0)
		{
			g1 = revnum%10;
			revnum1 = (revnum1 *10)+ g1;
			revnum = revnum/10;
		}
		System.out.println(revnum1);
		
	}

}
