package Reports;
import Utilities.CSVDownload;
import Utilities.CalendarUtil;
import Utilities.ScreenshotUtil;
import Utilities.LoginTest;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

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


public class CustomerBilling extends LoginTest{
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/CustomerBilling.xlsx";
	static String SheetName = "Sheet1";
	CalendarUtil calendar=new CalendarUtil();
	ScreenshotUtil screenshot=new ScreenshotUtil();

	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver=Log.Reports();
		driver.findElement(By.xpath("//td[contains(text(),'Customer Billing')]")).click();		
	}	
	
	@Test(dataProvider = "getData",priority=1)
	public void Customer(String ClientName,String From,String To) throws InterruptedException, ParseException, IOException {
		assertTrue(driver.getCurrentUrl().endsWith("#/reports/customerBilling"));
		Reporter.log("Customer Billing Page is Accessed");
		String ClientsearchText=ClientName;
		WebDriverWait wait = new WebDriverWait(driver, 30); 
		By xpath = By.xpath("//div[contains(@class,'ui-dropdown ui-widget ui-state-default ui-corner-all ui-helper-clearfix')]");
		//wait for element to be clickable, then click
		WebElement ClientElement = wait.until(ExpectedConditions.elementToBeClickable(xpath));
		ClientElement.click(); 
		  List<WebElement> Client = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
	    	int count1 = Client.size();
	    	for(int j=0;j<count1; j++)
	    	{ 
	   	      String text1 = Client.get(j).getText();
	  	      if(text1.contains(ClientsearchText))
	    	{   
	  	      Client.get(j).click();
	  	      Reporter.log(ClientsearchText + " is Selected");
	    	      break;
	    	}
	    	}
	    	String FromDate= calendar.getDate(From);
            String FromMonth=calendar.getMonth(From);
	    	 WebElement FromCal=driver.findElement(By.xpath("//body/my-app/div[@class='wrapper']/section[@class='shell-routed-content']/section/my-reports/section/table[@class='middleRowTable']/tbody/tr/td[@id='mainContainer-data']/div[@id='SPA_Views']/customerbilling-dahsboard/section/div/table[contains(@class,'table table-responsive')]/tbody/tr[@class='noBorder']/td[2]/div[1]/p-calendar"));
	    	 FromCal.click();
	    	  SelectDay(FromDate,FromMonth);
		    	Reporter.log(From + " From Date is selected");
	         String ToDate=calendar.getDate(To);
             String ToMonth=calendar.getMonth(To);
	        WebElement ToCal=driver.findElement(By.xpath("//body/my-app/div[@class='wrapper']/section[@class='shell-routed-content']/section/my-reports/section/table[@class='middleRowTable']/tbody/tr/td[@id='mainContainer-data']/div[@id='SPA_Views']/customerbilling-dahsboard/section/div/table[contains(@class,'table table-responsive')]/tbody/tr[@class='noBorder']/td[3]/div[1]/p-calendar"));
	        ToCal.click();
	        SelectDay(ToDate,ToMonth);
		    Reporter.log(To + " To Date is selected");
		   driver.findElement(By.xpath("//span[contains(text(),'Download Report')]")).click();
		   CSVDownload.waitForTheExcelFileToDownload("Customer Billing Report.csv", 300);
		   Reporter.log("Download Report is Clicked");
		   screenshot.ScreenshotFunction(driver, "CustomerBilling\\ReportDownload.png");
	       	driver.findElement(By.xpath("//span[contains(text(),'Clear')]")).click();
	       	Reporter.log("Clear Button is Clicked");
			   screenshot.ScreenshotFunction(driver, "CustomerBilling\\ClearLogs.png");
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
		Object[][] Data = new Object[1][3];
		Data[0][0] = formatter.formatCellValue(sheet.getRow(1).getCell(0));
		Data[0][1] = formatter.formatCellValue(sheet.getRow(1).getCell(1));
		Data[0][2] = formatter.formatCellValue(sheet.getRow(1).getCell(2));
		return Data;
}

	public void SelectDay(String Date,String Month) {
		WebDriverWait wait = new WebDriverWait(driver, 30); 
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
