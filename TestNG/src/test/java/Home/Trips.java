package Home;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.CalendarUtil;
import Utilities.FullMapUtil;
import Utilities.LoginTest;
import Utilities.ScreenshotUtil;

public class Trips extends LoginTest{
	ScreenshotUtil screenshot=new ScreenshotUtil();
	CalendarUtil calendar=new CalendarUtil();
	FullMapUtil FullMap=new FullMapUtil();

	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
	    Log.Login();
	    driver=Log.Dashboard();
		driver.findElement(By.xpath("//a[contains(text(),'Trips')]")).click();
	}
	
	//@Parameters({ "From","Device"})
	@Test(priority=1)
	public void TripsPage() throws IOException, ParseException, InterruptedException{
		//Assert.assertTrue(driver.getCurrentUrl().endsWith("#/home/trips"));
		Thread.sleep(2000);
		Reporter.log("Trips in Home Page is Accessed");
		String From="11/26/2019";
		String Device="2222221111";		
	    	WebElement ListBox=driver.findElement(By.xpath("//label[@class='ui-dropdown-label ui-inputtext ui-corner-all']"));
			ListBox.click();
			WebElement SearchInput=driver.findElement(By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']"));
			SearchInput.sendKeys(Device);
			driver.findElement(By.xpath("//li[@class='ui-dropdown-item ui-corner-all']")).click();
			WebElement DeviceName=driver.findElement(By.xpath("//label[@class='ui-dropdown-label ui-inputtext ui-corner-all']"));
			Reporter.log(DeviceName.getText() + " is Selected");
			String FromDate= calendar.getDate(From);
		    String FromMonth=calendar.getMonth(From);
		    WebElement FromCal=driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable fa fa-fw fa-calendar']"));
			FromCal.click();
			SelectDay(FromDate,FromMonth);
	    	Reporter.log(From + " Calendar Date is selected");	
	    	Thread.sleep(1200);
			String Text=driver.findElement(By.xpath("//div[@class='ui-growl-item']//div//p")).getText();
			Reporter.log(Text);
			
	}
	@Test(priority=3)
	public void Trip() throws InterruptedException, IOException {
		WebElement table=driver.findElement(By.xpath("//div[@class='trip-list-table-wrapper']//table//tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		Reporter.log("Total Trips :  " + rows.size());
		int i=1;
		for (WebElement row : rows) {
			Reporter.log(row.getAttribute("title"));
			row.click();
			Thread.sleep(1000);
			WebElement Triptable=driver.findElement(By.xpath("//div[@class='trip-details-table-wrapper']//table//tbody//tr[2]"));
			List<WebElement> Details=Triptable.findElements(By.tagName("td"));	
		    	 Reporter.log("Trip Start Time " + Details.get(0).getText()); 
		    	 Reporter.log("Trip End Time " + Details.get(1).getText()); 
		    	 Reporter.log("Trip Distance " + Details.get(2).getText()); 
		    	 Reporter.log("Trip Events " + Details.get(3).getText()); 
		    	// WebElement selection=Triptable.findElement(By.xpath("td//select"));
		    	 Select drpProtocol = new Select(Triptable.findElement(By.xpath("td//select")));
		    	 drpProtocol.selectByVisibleText("UDP");
		    	 Thread.sleep(1000);
		    	 Reporter.log("UDP Protocol is selected from Dropdown");
		    	 screenshot.ScreenshotFunction(driver, "Trips\\UDP_"+ i + ".png");
		    	 drpProtocol.selectByVisibleText("TCP");
		    	 Thread.sleep(1000);
		    	 Reporter.log("TCP Protocol is selected from Dropdown");
		    	 screenshot.ScreenshotFunction(driver, "Trips\\TCP_"+ i + ".png");
		    	 Details.get(5).click();
		    	 FullMap.ZoomOut(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\FullView\\ZoomOut_"+ i + ".png");
		    	 FullMap.ZoomIn(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\FullView\\ZoomIn_"+ i + ".png");
		    	 FullMap.Street(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\FullView\\Street"+ i + ".png");
		    	 FullMap.Satellite(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\FullView\\Satellite_"+ i + ".png");
		    	 driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable fa fa-fw fa-close']")).click();
		    	Reporter.log("Full View Map Screenshots Taken");
		    	 ///SmallView
		    	 FullMap.ZoomOut(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\WindowView\\ZoomOut_"+ i + ".png");
		    	 FullMap.ZoomIn(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\WindowView\\ZoomIn_"+ i + ".png");
		    	 FullMap.Street(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\WindowView\\Street"+ i + ".png");
		    	 FullMap.Satellite(driver);
		    	 screenshot.ScreenshotFunction(driver, "Trips\\WindowView\\Satellite_"+ i + ".png");
			    	Reporter.log("Window View Map Screenshots Taken");
		    	 JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,500)");
		        screenshot.ScreenshotFunction(driver, "Trips\\TripEvents_"+ i + ".png");
		    	 i++;
		}
	}

	public void SelectDay(String Date,String Month) {
		WebDriverWait wait = new WebDriverWait(driver, 60); 
        By Timexpath= By.xpath("//div[@class='ui-datepicker-title']");
		WebElement CurrentToTime = wait.until(ExpectedConditions.elementToBeClickable(Timexpath));
		WebElement ArrowElement1=driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//a[1]"));
		 while(true) {
	    	   //ArrowElement1.click();
	    	  if( CurrentToTime.getText().equalsIgnoreCase(Month)){
	    		  List<WebElement> Tocolumns=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,' '))]/a"));
	    		  for (WebElement cell: Tocolumns) {	 	        	
	 	        		 System.out.println(cell.getText());	 	        	 
	 	        		 if (cell.getText().equals(Date)) {
	 	        			 cell.click();
	 	        			 break;	 	             
	 	        	 }
	 	         }
		    		 break; 
	    	  }
	    	  else {
	    		  ArrowElement1.click();
	    	  }
	       } 	    	
	}	
}
