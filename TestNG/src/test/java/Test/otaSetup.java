package Test;

import org.testng.annotations.Test;

import PageObject.OtaSetupPO;
import Utilities.ScreenshotUtil;
import Utilities.LoginTest;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class otaSetup extends LoginTest {
	
public WebDriver driver;

OtaSetupPO otapo;
	@BeforeClass
	public void intialize() throws IOException
	{
		LoginTest Log = new LoginTest();
		Log.Startup();//this method is needed to ini. driver in logintest class
		Log.HomePage();
	    Log.Login();
	    	 
	    	System.out.println("logged in");
	   
	    driver=Log.Admin();
	    	System.out.println("admin clicked");
	    	driver.getTitle();
		/*otaSetup ota=new otaSetup();
		driver=ota.intializeDriver();
		ota.Startup();
		ota.HomePage();
		ota.Login();
		ota.Admin();*/
	}
	
	@Test(groups= {"SmokeTest"})  
	
	public void otasetupPg() throws InterruptedException 
	{
		otapo=new OtaSetupPO(driver);
		
			otapo.otasetuppgclick(); //clicks on ota management then ota setup pg
		
					Thread.sleep(4000);
			otapo.selectOptions();		
					Thread.sleep(4000);
	}
	
	
	@AfterClass
	
	public void closingTest()
	{
		if(driver!=null) {
			System.out.println("Closing browser");
			driver.quit();//Closes all browser windows and safely ends the session. calls Dispose() func
			
			//.Close() - Close the browser window that the driver has focus of.
		}
	}
	
	
	
	
	
	
	
	
	
	
}
