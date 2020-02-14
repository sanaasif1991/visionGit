package Reports;

import Utilities.CalendarUtil;
import Utilities.ScreenshotUtil;
import Utilities.CSVDownload;
import Utilities.LoginTest;


import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;




public class LogReports extends LoginTest{
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/LogReports.xlsx";
	static String SheetName = "Sheet1";
	CalendarUtil calendar=new CalendarUtil();
	CSVDownload csv=new CSVDownload();
	ScreenshotUtil screenshot=new ScreenshotUtil();
	
	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver=Log.Reports();
		driver.findElement(By.xpath("//td[contains(text(),'Log Reports')]")).click();
	}
	@Test(dataProvider = "getData",priority=1)
		public void Logs(String AppcodeTxt,String ClientTxt,String LogTxt,String From,String To) throws InterruptedException, IOException, ParseException {
		 driver.findElement(By.xpath("//label[contains(text(),'Smart Connect')]")).click();
	    	String searchText=AppcodeTxt;
		 List<WebElement> AppCodes = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
	    	int count = AppCodes.size();
	    	for(int i=0;i<count; i++)
	    	{ 
	   	      String text = AppCodes.get(i).getText();
	  	      if(text.contains(searchText))
	    	{   
	  	      AppCodes.get(i).click();
	  	    // Reporter.log(searchText + " is Selected. \n"+ driver.findElement(By.xpath("//div[@class='ui-growl-message']/p")).getText());
	    	      break;
	    	}
	    	}
			  Reporter.log("Selected "+ searchText + " Appcode from Dropdown ");
		    	String ClientsearchText=ClientTxt;
			  driver.findElement(By.xpath("//td[@id='mainContainer-data']//td[2]//p-dropdown[1]//div//label")).click();
			  List<WebElement> Client = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		    	int count1 = Client.size();
		    	for(int j=0;j<count1; j++)
		    	{ 
		   	      String text1 = Client.get(j).getText();
		  	      if(text1.contains(ClientsearchText))
		    	{   
		  	      Client.get(j).click();
		  	   //  Reporter.log(ClientsearchText + " is Selected. \n"+ driver.findElement(By.xpath("//div[@class='ui-growl-message']/p")).getText());
		    	      break;
		    	}
		    	}
		    	
		    	String LogSearch=LogTxt;
				driver.findElement(By.xpath("//body/my-app/div[@class='wrapper']/section[@class='shell-routed-content']/section/my-reports/section/table[@class='middleRowTable']/tbody/tr/td[@id='mainContainer-data']/div[@id='SPA_Views']/status-report/section/div/table[contains(@class,'table table-responsive')]/tbody/tr[@class='noBorder']/td[3]/p-dropdown[1]/div[1]")).click();
			    List<WebElement> LogTypes = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
			   	int count2 = LogTypes.size();
			    	for(int k=0;k<count2; k++)
			    	{ 
			   	      String text2 = LogTypes.get(k).getText();
			  	      if(text2.contains(LogSearch))
			    	{   
			  	      LogTypes.get(k).click();
			  	     //Reporter.log(LogSearch + " is Selected. \n"+ driver.findElement(By.xpath("//div[@class='ui-growl-message']/p")).getText());
			    	      break;
			    	}
			    	}
		    String FromDate= calendar.getDate(From);
		    String FromMonth=calendar.getMonth(From);
		    WebElement FromCal=driver.findElement(By.xpath("//td[@id='mainContainer-data']//td[1]//div[1]//p-calendar[1]"));
			FromCal.click();
			SelectDay(FromDate,FromMonth);
	    	Reporter.log(From + " From Date is selected");
           String ToDate=calendar.getDate(To);
           String ToMonth=calendar.getMonth(To);
           WebElement ToCal=driver.findElement(By.xpath("//td[@id='mainContainer-data']//td[2]//div[1]//p-calendar[1]"));
           ToCal.click();
		   SelectDay(ToDate,ToMonth);
		   Reporter.log(To + " To Date is selected");
		   driver.findElement(By.xpath("//span[contains(text(),'Submit')]")).click();
		   CSVDownload.waitForTheExcelFileToDownload("Log Report.csv", 500);
		   Reporter.log("Submit is clicked");
		   screenshot.ScreenshotFunction(driver, "LogReports\\Logs.png");
     	driver.findElement(By.xpath("//span[contains(text(),'Clear')]")).click();
		 CSVDownload.waitForTheExcelFileToDownload("Log Report(1).csv", 500);
       	Reporter.log("Clear is clicked");
		   screenshot.ScreenshotFunction(driver, "LogReports\\ClearLogs.png");
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
	 
	 @DataProvider
		public Object[][] getData() throws IOException {
			FileInputStream file = new FileInputStream(file_location);
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormatter formatter = new DataFormatter(Locale.US);
			Object[][] Data = new Object[1][5];
			Data[0][0] = formatter.formatCellValue(sheet.getRow(1).getCell(0));
			Data[0][1] = formatter.formatCellValue(sheet.getRow(1).getCell(1));
			Data[0][2] = formatter.formatCellValue(sheet.getRow(1).getCell(2));
			Data[0][3] = formatter.formatCellValue(sheet.getRow(1).getCell(3));
			Data[0][4] = formatter.formatCellValue(sheet.getRow(1).getCell(4));
			return Data;
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
