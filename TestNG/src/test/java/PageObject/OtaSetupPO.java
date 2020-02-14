package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Utilities.LoginTest;

public class OtaSetupPO extends LoginTest {

	
	
	private WebDriver driver;

 public OtaSetupPO(WebDriver driver) {
	this.driver=driver;
	
	//Initialise Elements
    PageFactory.initElements(driver, this);
}
	


	private By otaManagementlink = By.linkText("OTA Management");
	private By otaSetuplink = By.linkText("OTA Setup");
	private By clientDropdown= By.xpath("//div[@id='SPA_Views']/otasetup/div/div[2]/div[3]/table/tbody/tr[2]/td[2]/p-dropdown/div/label");
	private By dataLoggerClient= By.xpath("//div[@id='SPA_Views']/otasetup/div/div[2]/div[3]/table/tbody/tr[2]/td[2]/p-dropdown/div/div[4]/div/ul/li[5]/span");
	
	
	public void otasetuppgclick() throws InterruptedException {
		
		driver.findElement(otaManagementlink).click();
		
				Thread.sleep(4000);

		driver.findElement(otaSetuplink).click();
	}
	
	
	public void selectOptions() throws InterruptedException 
	{
		driver.findElement(clientDropdown).click();
				Thread.sleep(4000);
				
		driver.findElement(dataLoggerClient).click();
	}
	
	
	// Selenium is designed to automate front end of an application. It is NOT supposed to do API testing automation unless rest assured is usd 2 extnd framework.u can do it via executescript
	//method 2 run javascript com bt nt a recommended way
	
	//selecting last five checkboxes using Selenium.
	//List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
	//System.out.println("Total checkboxes found: " + allCheckboxes.size());
	// Starting index will be count of elements-5. i.e. if there are 10 check boxes,
	// you need to go (10-5)=5th index
	// to check 6th element.
//	for (int i = (allCheckboxes.size() - 5); i < allCheckboxes.size(); i++) {
	//	allCheckboxes.get(i).click();
		// Putting sleep to show execution
	//	public static void main(String[] args) is starting point of exec fr java. static in a Java class belong to class not its instance or object. main can beverloaded
	
	//A window handle is a unique identifier that is assigned to each window created.getWindowHandle() returns the window handle of currently focused window/tab. getWindowHandles() returns all windows/tabs
	
	
}
