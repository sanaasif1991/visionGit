package Test;

import Utilities.LoginTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CustomerEdit extends LoginTest {
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/CustomerEdit.xlsx";
	static String SheetName = "Sheet1";

	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver = Log.Admin();
	}

	@Parameters({ "ClientSearch" })
	@Test(priority = 1)
	public void Search(String ClientSearch) {
		Actions a = new Actions(driver);
		WebElement move = driver.findElement(By.xpath("//th[1]//input[1]"));
		a.moveToElement(move).click().sendKeys(ClientSearch).build().perform();
		Reporter.log("Search for Client");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//tr[@class='ui-datatable-even ui-widget-content']")).click();
	}

	@Test(dataProvider = "getData", priority = 2)
	public void EditCustomer(String UserName, String EmailID, String Add1, String Add2, String CityName,
			String StateName, String CountryName, String ZIPCode, String PhoneNo, String RoleType, String userActive) {
		driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable'][contains(text(),'Edit')]")).click();
		WebElement Name = driver.findElement(By.id("name"));
		Name.clear();
		Name.sendKeys(UserName);
		WebElement Email = driver.findElement(By.id("email"));
		Email.clear();
		Email.sendKeys(EmailID);
		WebElement Address1 = driver.findElement(By.id("address1"));
		Address1.clear();
		Address1.sendKeys(Add1);
		WebElement Address2 = driver.findElement(By.id("address2"));
		Address2.clear();
		Address2.sendKeys(Add2);
		WebElement City = driver.findElement(By.id("city"));
		City.clear();
		City.sendKeys(CityName);
		WebElement State = driver.findElement(By.id("state"));
		State.clear();
		State.sendKeys(StateName);
		WebElement Country = driver.findElement(By.id("country"));
		Country.clear();
		Country.sendKeys(CountryName);
		WebElement Zip = driver.findElement(By.id("zip"));
		Zip.clear();
		Zip.sendKeys(ZIPCode);
		WebElement Phone = driver.findElement(By.id("phone"));
		Phone.clear();
		Phone.sendKeys(PhoneNo);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String searchText = RoleType;
		driver.findElement(By.id("accountType")).click();
		List<WebElement> AccountType = driver
				.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count = AccountType.size();
		for (int i = 0; i < count; i++) {
			String text = AccountType.get(i).getText();
			if (text.contains(searchText)) {
				AccountType.get(i).click();
				break;
			}
		}
		Reporter.log("Selected Account Type from Dropdown");
		boolean Userchoice = Boolean.parseBoolean(userActive);
		boolean isChecked;
		isChecked = driver.findElement(By.xpath("//*[@id=\"active\"]/div/div[2]")).isEnabled();
		if (isChecked == Userchoice) {

		} else {
			driver.findElement(By.xpath("//*[@id=\'active\']/div/div[2]")).click();
		}
		WebElement Submit = driver.findElement(By.xpath("//span[contains(text(),'Save')]"));
		Submit.click();
		Reporter.log("Submit Button Enabled " + Submit.isEnabled());
		System.out.print(driver.findElement(By.xpath("//*[contains(text(),'Updated.')]")).getText());
		System.out.print("\n");
		Reporter.log(driver.findElement(By.xpath("//*[contains(text(),'Updated.')]")).getText());
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
		// Iterate through each rows one by one
		int rowcount = sheet.getPhysicalNumberOfRows();
		int count = 0;
		Object[][] Data = new Object[1][11];
		Data[0][0] = formatter.formatCellValue(sheet.getRow(1).getCell(0));
		Data[0][1] = formatter.formatCellValue(sheet.getRow(1).getCell(1));
		Data[0][2] = formatter.formatCellValue(sheet.getRow(1).getCell(2));
		Data[0][3] = formatter.formatCellValue(sheet.getRow(1).getCell(3));
		Data[0][4] = formatter.formatCellValue(sheet.getRow(1).getCell(4));
		Data[0][5] = formatter.formatCellValue(sheet.getRow(1).getCell(5));
		Data[0][6] = formatter.formatCellValue(sheet.getRow(1).getCell(6));
		Data[0][7] = formatter.formatCellValue(sheet.getRow(1).getCell(7));
		Data[0][8] = formatter.formatCellValue(sheet.getRow(1).getCell(8));
		Data[0][9] = formatter.formatCellValue(sheet.getRow(1).getCell(9));
		Data[0][10] = formatter.formatCellValue(sheet.getRow(1).getCell(10));
		return Data;
	}
}
