package Reports;

import org.testng.annotations.AfterClass;
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

import Utilities.LoginTest;
import Utilities.ScreenshotUtil;

public class FleetReport extends LoginTest{
	ScreenshotUtil screenshot=new ScreenshotUtil();

	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver=Log.Reports();
		driver.findElement(By.xpath("//td[contains(text(),'Fleet Report')]")).click();
	}	    
    @Test(priority=1)
	public void FleetReports() throws InterruptedException, IOException {
    	Thread.sleep(1000);
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
  	     Reporter.log(searchText + " is Selected. \n"+ driver.findElement(By.xpath("//div[@class='ui-growl-message']/p")).getText());
    	      break;
    	}
    	}
		  Reporter.log("Selected "+ searchText + " Client from Dropdown ");
		  screenshot.ScreenshotFunction(driver, "FleetReports\\FleetReports.png");
   }
    @Test(priority=2)
    public void ColumnSort() { 
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
			Reporter.log("Sorting of SerialNo is " + originalList1.equals(copiedList1));
			///////Alias
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
			Reporter.log("Sorting of Alias is " + originalList2.equals(copiedList2));
//			/////communicationdate
			WebElement sort2=table.findElement(By.xpath("//th[3]//span[2]"));
			sort2.click();
			sort2.click();
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
			Reporter.log("Sorting of Last Communication is " + originalList3.equals(copiedList3));
   }

    @Test(priority=3)
    public void DeviceSearch() throws IOException, InterruptedException { 
    	WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']/tr[1]/td[1]/span/span"));
    	String SerialNo=table.getText();
    	Reporter.log(SerialNo + " SerialNo is Searched");
    	WebElement DeviceSearchBox=driver.findElement(By.xpath("//th[1]//input[1]"));
    	DeviceSearchBox.sendKeys(SerialNo);
    	String ExpectedSerialNo=driver.findElement(By.xpath("//td[@id='mainContainer-data']//td[1]/span/span")).getText();
		  screenshot.ScreenshotFunction(driver, "FleetReports\\Device.png");
       	AssertJUnit.assertEquals(SerialNo, ExpectedSerialNo);
    	Reporter.log("Device Search : " + SerialNo.equals(ExpectedSerialNo));
    	DeviceSearchBox.clear();
    	DeviceSearchBox.click();
    	Thread.sleep(1500);
    }
    
    @Test(priority=4)
    public void AliasSearch() throws InterruptedException, IOException { 
    	//Thread.sleep(1500);
    	WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']/tr[1]/td[2]/span"));
    	String Alias=table.getText();
    	Reporter.log(Alias + " Alias is Searched");
    	WebElement AliasSearchBox=driver.findElement(By.xpath("//th[2]//input[1]"));
    	AliasSearchBox.sendKeys(Alias);
    	String ExpectedAlias=driver.findElement(By.xpath("//td[@id='mainContainer-data']//td[2]/span")).getText();
		screenshot.ScreenshotFunction(driver, "FleetReports\\Alias.png"); 
       	AssertJUnit.assertEquals(Alias, ExpectedAlias);
    	Reporter.log("Alias Search : " + Alias.equals(ExpectedAlias));
    	AliasSearchBox.clear();
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
    @Test(priority=5)
    public void DateSearch() throws InterruptedException, IOException { 
    	Thread.sleep(1500);
    	WebElement table=driver.findElement(By.xpath("//tbody[@class='ui-datatable-data ui-widget-content']/tr[1]/td[3]/span/span"));
    	String Date=table.getText();
    	Reporter.log(Date + " Date is Searched");
    	WebElement DateSearchBox=driver.findElement(By.xpath("//th[3]//input[1]"));
    	DateSearchBox.sendKeys(Date);
    	String ExpectedDate=driver.findElement(By.xpath("//td[@id='mainContainer-data']//td[2]/span")).getText();
		  screenshot.ScreenshotFunction(driver, "FleetReports\\Date.png");
 	//Assert.assertEquals(Date, ExpectedDate);
    	Reporter.log("Date Search : " + Date.equals(ExpectedDate));
    }
    
}
