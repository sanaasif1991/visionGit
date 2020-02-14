package Test;

import static org.testng.Assert.assertTrue;

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
import Utilities.LoginTest;

public class DeviceEdit extends LoginTest {
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/DeviceEdit.xlsx";
	static String SheetName = "Sheet1";

	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
		Log.Login();
		driver = Log.Admin();
		driver.findElement(By.xpath("//td[contains(text(),'Device Management')]")).click();
	}

	@Parameters({ "DeviceSearch" })
	@Test(priority = 1)
	public void SearchDevice(String DeviceSearch) throws InterruptedException {
		Thread.sleep(1000);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.endsWith("#/admin/deviceProvisioning"));
		Actions a = new Actions(driver);
		WebElement move = driver.findElement(By.xpath("//th[3]//input[1]"));
		a.moveToElement(move).click().sendKeys(DeviceSearch).build().perform();
		Reporter.log(DeviceSearch + "Device is Searched Successfully");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//tr[@class='ui-datatable-even ui-widget-content']")).click();
	}

	@Test(dataProvider = "getData", priority = 2)
	public void Edit(String ClientID, String IMEINo, String serialno, String Vehicle, String CommandGroup,
			String ProviderName, String Device, String DeviceGrp, String VehicleData, String userActive) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable'][contains(text(),'Edit')]")).click();
		String searchText = ClientID;
		driver.findElement(By.id("clientId")).click();
		List<WebElement> Clients = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count = Clients.size();
		for (int i = 0; i < count; i++) {
			String text = Clients.get(i).getText();
			if (text.contains(searchText)) {
				Clients.get(i).click();
				break;
			}
		}
		Reporter.log("Selected Client from Dropdown");
		WebElement IMEI = driver.findElement(By.id("imei"));
		IMEI.clear();
		IMEI.sendKeys(IMEINo);
		WebElement SerialNum = driver.findElement(By.id("serialNo"));
		SerialNum.clear();
		SerialNum.sendKeys(serialno);
		WebElement VehicleAlias = driver.findElement(By.id("vehicleAlias"));
		VehicleAlias.clear();
		VehicleAlias.sendKeys(Vehicle);
		Reporter.log("Assign Serial No,IMEI,Vehicle Alias");
		///////////// Command Group
		String CommandGrp = CommandGroup;
		driver.findElement(By.id("commandsGroupName")).click();
		List<WebElement> Groups = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count1 = Groups.size();
		for (int i = 0; i < count1; i++) {
			String text = Groups.get(i).getText();
			if (text.contains(CommandGrp)) {
				Groups.get(i).click();
				break;
			}
		}
		Reporter.log("Assign Command Group");
		//////////////// Provider
		String Provider = ProviderName;
		driver.findElement(By.id("provider")).click();
		List<WebElement> Providers = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count2 = Providers.size();
		for (int i = 0; i < count2; i++) {
			String text = Providers.get(i).getText();
			if (text.contains(Provider)) {
				Providers.get(i).click();
				break;
			}
		}
		Reporter.log("Assign Provider");
		////////// Device Type
		String DeviceType = Device;
		driver.findElement(By.id("deviceType")).click();
		List<WebElement> Types = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count3 = Types.size();
		for (int i = 0; i < count3; i++) {
			String text = Types.get(i).getText();
			if (text.contains(DeviceType)) {
				Types.get(i).click();
				break;
			}
		}
		Reporter.log("Device Type");
		///////////////// Device Group
		String DeviceGroup = DeviceGrp;
		driver.findElement(By.id("deviceGroup")).click();
		List<WebElement> devGroups = driver.findElements(By.xpath("//div[@class='ui-dropdown-items-wrapper']//ul//li"));
		int count4 = devGroups.size();
		for (int i = 0; i < count4; i++) {
			String text = devGroups.get(i).getText();
			if (text.contains(DeviceGroup)) {
				devGroups.get(i).click();
				break;
			}
		}
		Reporter.log("Assign Device Group");
		/////////////////////////// Only Vehicle Data
		Thread.sleep(500);
		boolean Vehiclechoice = Boolean.parseBoolean(VehicleData);
		boolean isChecked;
		isChecked = driver.findElement(By.xpath("//*[@id='showOnlyCurrentVehicleData']/div/div[2]")).isEnabled();
		if (false == Vehiclechoice) {

		} else {
			driver.findElement(By.xpath("//*[@id='showOnlyCurrentVehicleData']/div/div[2]")).click();
		}
		Reporter.log("Show only Vehicle Alias Data");
		//////////////// Active
		Thread.sleep(500);
		boolean Userchoice = Boolean.parseBoolean(userActive);
		boolean isChecked1;
		isChecked1 = driver.findElement(By.xpath("//*[@id=\"active\"]/div/div[2]")).isEnabled();
		if (isChecked1 == Userchoice) {

		} else {
			driver.findElement(By.xpath("//*[@id=\'active\']/div/div[2]")).click();
		}
		Reporter.log("Checked Type of Customer");
		driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();
		System.out.print(driver.findElement(By.xpath("//*[contains(text(),'Updated.')]")).getText());
		System.out.print("\n");
		Reporter.log("Device Updated Successfully");
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
		Object[][] Data = new Object[1][10];
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
		return Data;
	}

}
