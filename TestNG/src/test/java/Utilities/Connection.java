package Utilities;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Properties;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class Connection {

	public WebDriver driver;

	public WebDriver intializeDriver() throws IOException

	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sanaa\\Desktop\\Vis\\chromedriver.exe");
		// Open the Chrome browser
		return  driver = new ChromeDriver();

		

		 

	}

}
