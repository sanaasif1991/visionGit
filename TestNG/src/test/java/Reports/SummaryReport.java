package Reports;

import Utilities.LoginTest;
import Utilities.ScreenshotUtil;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;


////Assertion of BatteryHealth Volts,Total Miles Driven commented
public class SummaryReport extends LoginTest{
	ScreenshotUtil screenshot=new ScreenshotUtil();
	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver=Log.Reports();
	}	    
    @Test(priority=1)
	public void SummaryReports() throws InterruptedException, IOException {
    	Thread.sleep(500);
	    	 String searchText="Danlaw Gen 2.5";
		        driver.findElement(By.xpath("//label[@class='ui-dropdown-label ui-inputtext ui-corner-all']")).click();
		    	WebElement SearchBox=driver.findElement(By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']"));
		        SearchBox.click();
		    	SearchBox.clear();
		    	SearchBox.sendKeys(searchText);
		    	List<WebElement> Clients = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		    	int count = Clients.size();
		    	for(int i=0;i<count; i++)
		    	{ 
		   	      String text = Clients.get(i).getText();
		  	      if(text.contains(searchText))
		    	{   
		  	      Clients.get(i).click();
		    	      break;
		    	}
		    	}
				  Reporter.log("Selected "+ searchText + " Client from Dropdown ");
				   screenshot.ScreenshotFunction(driver, "SummaryReports\\SummaryReports.png");
}
    
	   @Test(priority=2)
	   public void DaysBehindWheel() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-4']//div[1]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]"));
		  view.click();	
		  Thread.sleep(1500);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("DAYS BEHIND THE WHEEL",Actual_Message);
		  WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			WebElement sort1=table.findElement(By.xpath("//th[2]//span[2]"));
			sort1.click();
			List<WebElement> SecondColList= table.findElements(By.cssSelector("tr td:nth-child(2)"));
			ArrayList<String> originalList2=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList2.add(SecondColList.get(i).getText());
			}
			ArrayList<String> copiedList2=new ArrayList<String>(originalList2);
			Collections.sort(copiedList2,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList2.equals(copiedList2));
			Reporter.log("Sorting of DaysBehindWheel is " + originalList2.equals(copiedList2));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\DaysBehindWheel.png");
			  driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();  	    
	   }
	   
	   @Test(priority=3)
	   public void LongestTrip() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-4']//div[2]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();	
		  Thread.sleep(1000);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		 		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("LONGEST TRIP",Actual_Message);
		   WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			///////Last Known
//			WebElement sort1=table.findElement(By.xpath("//th[2]//span[2]"));
//			sort1.click();
//			List<WebElement> SecondColList= table.findElements(By.cssSelector("tr td:nth-child(2)"));
//			ArrayList<String> originalList2=new ArrayList<String>();
//			for(int i=0;i<firstColList.size();i++)
//			{
//			originalList2.add(SecondColList.get(i).getText());
//			}
//			ArrayList<String> copiedList2=new ArrayList<String>(originalList2);
//			Collections.sort(copiedList2,String.CASE_INSENSITIVE_ORDER);
//			Assert.assertTrue(originalList2.equals(copiedList2));
//			Reporter.log("Sorting of Last Known Date is " + originalList2.equals(copiedList2));
//			/////
			WebElement sort2=table.findElement(By.xpath("//th[3]//span[2]"));
			sort2.click();
			List<WebElement> ThirdColList= table.findElements(By.cssSelector("tr td:nth-child(3)"));
			ArrayList<String> originalList3=new ArrayList<String>();
			for(int i=0;i<ThirdColList.size();i++)
			{
			originalList3.add(ThirdColList.get(i).getText());
			}
			ArrayList<String> copiedList3=new ArrayList<String>(originalList3);
			Collections.sort(copiedList3,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList3.equals(copiedList3));
			Reporter.log("Sorting of Longest is " + originalList3.equals(copiedList3));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\LongestTrip.png");
		driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   @Test(priority=4)
	   public void SpeedingEvents() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-4']//div[3]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();
		  Thread.sleep(1500);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("SPEEDING EVENTS",Actual_Message);
		   WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			WebElement sort1=table.findElement(By.xpath("//th[2]//span[2]"));
			sort1.click();
			List<WebElement> SecondColList= table.findElements(By.cssSelector("tr td:nth-child(2)"));
			ArrayList<String> originalList2=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList2.add(SecondColList.get(i).getText());
			}
			ArrayList<String> copiedList2=new ArrayList<String>(originalList2);
			Collections.sort(copiedList2,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList2.equals(copiedList2));
			Reporter.log("Sorting of Speeding Events is " + originalList2.equals(copiedList2));
			screenshot.ScreenshotFunction(driver, "SummaryReports\\SpeedingEvents.png");        		 
			driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   @Test(priority=5)
	   public void TOTAL_MILES_DRIVEN() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-4']//div[4]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();	
		  Thread.sleep(1500);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("TOTAL MILES DRIVEN",Actual_Message);
		   WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			WebElement sort1=table.findElement(By.xpath("//th[2]//span[2]"));
			sort1.click();
			List<WebElement> SecondColList= table.findElements(By.cssSelector("tr td:nth-child(2)"));
			ArrayList<String> originalList2=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList2.add(SecondColList.get(i).getText());
			}
			ArrayList<String> copiedList2=new ArrayList<String>(originalList2);
			Collections.sort(copiedList2,String.CASE_INSENSITIVE_ORDER);
			//Assert.assertTrue(originalList2.equals(copiedList2));
			Reporter.log("Sorting of Miles Driven is " + originalList2.equals(copiedList2));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\Total_Miles_Driven.png");
		  		driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   @Test(priority=6)
	   public void BATTERY_HEALTH() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[5]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();	
		  Thread.sleep(1500);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("BATTERY HEALTH",Actual_Message);
		   WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			///////
			WebElement sort2=table.findElement(By.xpath("//th[3]//span[2]"));
			sort2.click();
			List<WebElement> ThirdColList= table.findElements(By.cssSelector("tr td:nth-child(3)"));
			ArrayList<String> originalList3=new ArrayList<String>();
			for(int i=0;i<ThirdColList.size();i++)
			{
			originalList3.add(ThirdColList.get(i).getText());
			}
			ArrayList<String> copiedList3=new ArrayList<String>(originalList3);
			Collections.sort(copiedList3,String.CASE_INSENSITIVE_ORDER);
			//Assert.assertTrue(originalList3.equals(copiedList3));
			Reporter.log("Sorting of BatteryHealth is " + originalList3.equals(copiedList3));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\Battery_Health.png");         
		  driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   @Test(priority=7)
	   public void HARD_BRAKING_EVENTS() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[6]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();	
		  Thread.sleep(1500);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("HARD BRAKING EVENTS",Actual_Message);
		  WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			WebElement sort1=table.findElement(By.xpath("//th[2]//span[2]"));
			sort1.click();
			List<WebElement> SecondColList= table.findElements(By.cssSelector("tr td:nth-child(2)"));
			ArrayList<String> originalList2=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList2.add(SecondColList.get(i).getText());
			}
			ArrayList<String> copiedList2=new ArrayList<String>(originalList2);
			Collections.sort(copiedList2,String.CASE_INSENSITIVE_ORDER);
			//Assert.assertTrue(originalList2.equals(copiedList2));
			Reporter.log("Sorting of Hard Braking Events is " + originalList2.equals(copiedList2));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\Hard_Braking_Events.png");     		 
	      driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   
	   @Test(priority=8)
	   public void Vehicle_Location() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-7']//div[2]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();
		  Thread.sleep(1000);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("VEHICLE LOCATION",Actual_Message);
	//////////////////////
		  WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			///////Last Known
			/////
		WebElement sort2=table.findElement(By.xpath("//th[3]//span[2]"));
			sort2.click();
			List<WebElement> ThirdColList= table.findElements(By.cssSelector("tr td:nth-child(3)"));
			ArrayList<String> originalList3=new ArrayList<String>();
			for(int i=0;i<ThirdColList.size();i++)
			{
			originalList3.add(ThirdColList.get(i).getText());
			}
			ArrayList<String> copiedList3=new ArrayList<String>(originalList3);
			Collections.sort(copiedList3,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList3.equals(copiedList3));
			Reporter.log("Sorting of Vehicle_Location is " + originalList3.equals(copiedList3));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\Vehicle_Location.png");		 
		  driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   
	   @Test(priority=9)
	   public void Diagnostic_Trouble_Code() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-7']//div[3]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();	 
		  Thread.sleep(1000);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("DIAGNOSTIC TROUBLE CODES",Actual_Message);
		  //////////////////////
    WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
	WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
	sort.click();
	List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
	ArrayList<String> originalList1=new ArrayList<String>();
	for(int i=0;i<firstColList.size();i++)
	{
	originalList1.add(firstColList.get(i).getText());
	}
	ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
	Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
	AssertJUnit.assertTrue(originalList1.equals(copiedList1));
	Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
	//	/////
	WebElement sort2=table.findElement(By.xpath("//th[3]//span[2]"));
	sort2.click();
	List<WebElement> ThirdColList= table.findElements(By.cssSelector("tr td:nth-child(3)"));
	ArrayList<String> originalList3=new ArrayList<String>();
	for(int i=0;i<ThirdColList.size();i++)
	{
	originalList3.add(ThirdColList.get(i).getText());
	}
	ArrayList<String> copiedList3=new ArrayList<String>(originalList3);
	Collections.sort(copiedList3,String.CASE_INSENSITIVE_ORDER);
	AssertJUnit.assertTrue(originalList3.equals(copiedList3));
	Reporter.log("Sorting of Vehicle_Location is " + originalList3.equals(copiedList3));
	   screenshot.ScreenshotFunction(driver, "SummaryReports\\Diagnostic_Trouble_Code.png");         
		  driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();
	   }
	   @Test(priority=10)
	   public void Hard_Acceleration_Events() throws InterruptedException, IOException {
		  WebElement view= driver.findElement(By.xpath("//div[@class='col-md-7']//div[4]//section[1]//div[1]//div[1]//div[2]//div[1]//span[2]//a[1]"));
		  view.click();	 
		  Thread.sleep(1000);
		  String Actual_Message=driver.findElement(By.xpath("//*[@id=\"widgetsContainer\"]/p-dialog/div/div[1]/span/p-header/span")).getText();
		  Reporter.log(Actual_Message + " is Opened"); 
		   AssertJUnit.assertEquals("HARD ACCELERATION EVENTS",Actual_Message);
		  WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']"));
			WebElement sort=table.findElement(By.xpath("//th[1]//span[2]"));
			sort.click();
			List<WebElement> firstColList= table.findElements(By.cssSelector("tr td:nth-child(1)"));
			ArrayList<String> originalList1=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList1.add(firstColList.get(i).getText());
			}
			ArrayList<String> copiedList1 =new ArrayList<String>(originalList1);
			Collections.sort(copiedList1,String.CASE_INSENSITIVE_ORDER);
			AssertJUnit.assertTrue(originalList1.equals(copiedList1));
			Reporter.log("Sorting of Vehicle is " + originalList1.equals(copiedList1));
			WebElement sort1=table.findElement(By.xpath("//th[2]//span[2]"));
			sort1.click();
			List<WebElement> SecondColList= table.findElements(By.cssSelector("tr td:nth-child(2)"));
			ArrayList<String> originalList2=new ArrayList<String>();
			for(int i=0;i<firstColList.size();i++)
			{
			originalList2.add(SecondColList.get(i).getText());
			}
			ArrayList<String> copiedList2=new ArrayList<String>(originalList2);
			Collections.sort(copiedList2,String.CASE_INSENSITIVE_ORDER);
			//Assert.assertTrue(originalList2.equals(copiedList2));
			Reporter.log("Sorting of Hard Braking Events is " + originalList2.equals(copiedList2));
			   screenshot.ScreenshotFunction(driver, "SummaryReports\\Hard_Acc_Events.png");       
		  	  driver.findElement(By.xpath("//div[@id='widgetsContainer']//span[@class='fa fa-fw fa-close']")).click();

	   }
		@AfterClass
		public void Logout() {
			WebElement element = driver.findElement(By.xpath("//span[@class='nav-welcome-name']"));
			WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click()", element);
			executor.executeScript("arguments[0].click()", logout);
			Reporter.log("Logged Out");
			driver.close();
		}

  
}
