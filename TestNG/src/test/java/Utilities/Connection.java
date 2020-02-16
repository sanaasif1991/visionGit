package Utilities;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Properties;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Connection {

	public WebDriver driver;

	public WebDriver intializeDriver(String browser) throws IOException

	{
		
		if(browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sanaa\\Desktop\\Vis\\chromedriver.exe");
		// Open the Chrome browser
		driver = new ChromeDriver(ChromeProfile());
		
		}//if ends
		
		
		else if (browser.equalsIgnoreCase("firefox")){
			driver=new FirefoxDriver();
			
				
		}//else ends

		return  driver;
		
	}
	

	public   ChromeOptions ChromeProfile(){
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--start-maximized");
		
		return options;
	}


}
