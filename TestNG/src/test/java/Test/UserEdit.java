package Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.LoginTest;

public class UserEdit extends LoginTest{
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/UserEdit.xlsx";
	static String SheetName = "Sheet1";

	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver = Log.Admin();
		WebElement Usermanagement=driver.findElement(By.xpath("/html/body/my-app/div/section/section/my-admin/section/table/tbody/tr/td[1]/div/table/tbody/tr[2]/td/div/a/table/tbody/tr/td[1]"));
        Usermanagement.click();
	}

	@Parameters({ "UserSearch" })
	@Test(priority = 1)
	  public void SearchUser(String UserSearch) throws InterruptedException {
		Thread.sleep(1000);
	  	   Actions a=new Actions(driver);
	 	   WebElement move=driver.findElement(By.xpath("//th[1]//input[1]"));
	   	   a.moveToElement(move).click().sendKeys(UserSearch).build().perform();
	   	   Reporter.log("Search for UserId");
	   	    try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   	  WebElement table=driver.findElement(By.xpath("//*[@id=\"userDataTable\"]/div/div[2]/div/div[2]/div/table/tbody"));	     	
	   	  System.out.print(table.findElements(By.tagName("tr")).size());
	   	  List<WebElement> tableCells= table.findElements(By.xpath("//tr/td[1]/span[2]"));
	   	  for(WebElement cell : tableCells) {
	   		 System.out.println( "Value = " + cell.getText());
	   		 if(cell.getText().equals(UserSearch)) {
	   			   cell.click();
	   			   driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable'][contains(text(),'Edit')]")).click();
	   			   Reporter.log("Edit UserID");
	   			   break;
	   			    }
	   			}
				    }
	@Test(dataProvider = "getData", priority = 2)
	  public void EditUserDetails(String Name, String Email, String Phoneno, String Role, String UserType,
				String UserActive) throws InterruptedException {
		WebElement UserID=driver.findElement(By.name("login"));
		Reporter.log(UserID.getText() + " is UserId and Enabled: " +  UserID.isEnabled());
		  driver.findElement(By.id("name")).clear();
		  driver.findElement(By.id("name")).sendKeys(Name);
		  Reporter.log("Send name ");
		  driver.findElement(By.id("email")).clear();
		 driver.findElement(By.id("email")).sendKeys(Email);
		  Reporter.log("Send Email ID of " + Email);
		  driver.findElement(By.id("phoneNumber")).clear();
		driver.findElement(By.id("phoneNumber")).sendKeys(Phoneno);	
		  Reporter.log("Send PhoneNumber ");
		String searchText=Role;
	    driver.findElement(By.xpath("//*[@id=\'role\']/div/label")).click();
		List<WebElement> Roledrp = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count = Roledrp.size();
		for(int i=0;i<count; i++)
		{ 
		      String text = Roledrp.get(i).getText();
		      if(text.contains(searchText))
		{   
		      Roledrp.get(i).click();
		      break;
		}
		}
		  Reporter.log("Selected Role from Dropdown");
		   /////////////// Type of User-Internal/External
		   String searchUser=UserType;
		   driver.findElement(By.xpath("//*[@id=\"verifyPasswordAgainst\"]/div/label")).click();//select user field
	   	   WebElement dropdown1 = driver.findElement(By.xpath("//*[@id=\'verifyPasswordAgainst\']/div/div[4]"));//find dropdown values
	   	List<WebElement> options1 = dropdown1.findElements(By.xpath("//*[@id=\"verifyPasswordAgainst\"]/div/div[4]/div/ul/li"));
	   	 System.out.println(options1.size());
	   	   for(int j=0;j<options1.size();j++){
	   	    //Print the text
	       	    System.out.println(options1.get(j).getText());
	       	    String optionName1 = options1.get(j).getText();
	       	    if (optionName1.equals(searchUser))
	       	    {
	       	        options1.get(j).click(); // click the desired option
	       	        break;
	       	    }
	   	    }
			  Reporter.log("Selected User Type from Dropdown");
			/// Active User checkbox
			  Thread.sleep(500);
				boolean Userchoice = Boolean.parseBoolean(UserActive);
				boolean isChecked;
				isChecked = driver.findElement(By.xpath("//*[@id=\'active\']/div/div[2]")).isEnabled();
				if (isChecked == Userchoice) {

				} else {
					driver.findElement(By.xpath("//*[@id=\'active\']/div/div[2]")).click();
				}
				Reporter.log("Checked Type of User");
				WebElement Submit=driver.findElement(By.xpath("//*[@id=\"SPA_Views\"]/usermangement/div/div[2]/p-dialog/div/div[3]/p-footer/div/button[1]/span[2]"));
		       	Submit.click();	
		       	Reporter.log("Submit button is Enabled " + Submit.isEnabled());
		       	Reporter.log("Submit User");
	   	    	System.out.print(driver.findElement(By.xpath("//*[contains(text(),'Updated.')]")).getText());
	   	    	System.out.print("\n");  
	  }
	
	@DataProvider
	public Object[][] getData() throws IOException {
		FileInputStream file = new FileInputStream(file_location);

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter formatter = new DataFormatter(Locale.US);
		// Iterate through each rows one by one
		int rowcount = sheet.getPhysicalNumberOfRows();
		int count = 0;
		Object[][] Data = new Object[1][6];
		Data[0][0] = formatter.formatCellValue(sheet.getRow(1).getCell(0));
		Data[0][1] = formatter.formatCellValue(sheet.getRow(1).getCell(1));
		Data[0][2] = formatter.formatCellValue(sheet.getRow(1).getCell(2));
		Data[0][3] = formatter.formatCellValue(sheet.getRow(1).getCell(3));
		Data[0][4] = formatter.formatCellValue(sheet.getRow(1).getCell(4));
		Data[0][5] = formatter.formatCellValue(sheet.getRow(1).getCell(5));
		//Data[0][6] = formatter.formatCellValue(sheet.getRow(1).getCell(6));

		return Data;
	}
	  
}
