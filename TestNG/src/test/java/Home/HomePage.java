package Home;
import org.testng.annotations.Test;
import Utilities.ScreenshotUtil;
import Utilities.LoginTest;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class HomePage extends LoginTest{
	ScreenshotUtil screenshot=new ScreenshotUtil();
	@Test(priority = 0)
	public void Test() throws IOException {
		LoginTest Log = new LoginTest();
		Log.Startup();
		Log.HomePage();
	    Log.Login();
	    driver=Log.Dashboard();
	}
	
	@Test(priority=1)
	public void DashboardPage() throws IOException{
		WebElement ListBox=driver.findElement(By.xpath("//label[@class='ui-dropdown-label ui-inputtext ui-corner-all']"));
		ListBox.click();
		WebElement SearchInput=driver.findElement(By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']"));
		SearchInput.sendKeys("8103040095");
		driver.findElement(By.xpath("//li[@class='ui-dropdown-item ui-corner-all']")).click();
		WebElement DeviceName=driver.findElement(By.xpath("//label[@class='ui-dropdown-label ui-inputtext ui-corner-all']"));
		Reporter.log(DeviceName.getText() + "is Selected");
		   screenshot.ScreenshotFunction(driver, "Dashboard\\Dashboard-1.png");
					JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,500)");
			   screenshot.ScreenshotFunction(driver, "Dashboard\\Dashboard-2.png");
	}
	@Test(priority=2)
	public void LatestAlerts(){
		driver.findElement(By.xpath("//div[@class='recent-alerts']//span[@class='dashboard-widget-view-all-small']")).click();
		WebElement Title=driver.findElement(By.xpath("//div[@class='recent-alerts']//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']/span"));
		Reporter.log(Title.getText() + " is Opened");
		WebElement Messages=driver.findElement(By.xpath("//div[@class='recent-alerts']//p-dialog//div[@class='widgetNoDataMsg']"));
		Reporter.log(Messages.getText() );
		driver.findElement(By.xpath("//div[@class='recent-alerts']//span[@class='fa fa-fw fa-close']")).click();
	}
	@Test(priority=3)
	public void VehicleStatus() {
		driver.findElement(By.xpath("//div[@class='vehicle-status']//span[@class='dashboard-widget-view-all-small']")).click();
		WebElement Title=driver.findElement(By.xpath("//div[@class='recent-alerts']//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']/span"));
		Reporter.log(Title.getText() + " is Opened");
		WebElement Messages=driver.findElement(By.xpath("//div[@class='recent-alerts']//p-dialog//div[@class='widgetNoDataMsg']"));
		Reporter.log(Messages.getText() );
		driver.findElement(By.xpath("//div[@class='vehicle-status']//span[@class='fa fa-fw fa-close']")).click();
	}
	@Test(priority=4)
	public void DeviceEvents() {
		driver.findElement(By.xpath("//div[@class='device-events']//a[contains(text(),'View More')]")).click();
		WebElement Title=driver.findElement(By.xpath("//div[@class='recent-alerts']//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']/span"));
		Reporter.log(Title.getText() + " is Opened");
		WebElement Messages=driver.findElement(By.xpath("//div[@class='recent-alerts']//p-dialog//div[@class='widgetNoDataMsg']"));
		Reporter.log(Messages.getText() );
		driver.findElement(By.xpath("//div[@class='device-events']//span[@class='fa fa-fw fa-close']")).click();
	}
	@Test(priority=5)
	public void Driving_behaviour() {
		driver.findElement(By.xpath("//div[@class='driving-behavior']//a[contains(text(),'View More')]")).click();
		WebElement Title=driver.findElement(By.xpath("//div[@class='recent-alerts']//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']/span"));
		Reporter.log(Title.getText() + " is Opened");
		WebElement Messages=driver.findElement(By.xpath("//div[@class='recent-alerts']//p-dialog//div[@class='widgetNoDataMsg']"));
		Reporter.log(Messages.getText() );
		driver.findElement(By.xpath("//div[@class='driving-behavior']//span[@class='fa fa-fw fa-close']")).click();
	}
	@Test(priority=6)
	public void Map() throws IOException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(50,250)");
		WebElement ZoomOut=driver.findElement(By.xpath("//button[@class='mapboxgl-ctrl-icon mapboxgl-ctrl-zoom-out']"));
		ZoomOut.click();
		Thread.sleep(1000);
		   screenshot.ScreenshotFunction(driver, "Dashboard\\ZoomOut.png");               	
		WebElement ZoomIn=driver.findElement(By.xpath("//button[@class='mapboxgl-ctrl-icon mapboxgl-ctrl-zoom-in']"));
		ZoomIn.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\ZoomIn.png"); 		                	
		driver.findElement(By.xpath("//div[@class='mapLayerStyles']")).click();
		WebElement Street=driver.findElement(By.id("radStreets"));
		Street.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\StreetView.png"); 
		WebElement Satellite=driver.findElement(By.id("radSatellite"));
		Satellite.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\SatelliteView.png"); 
			}
	@Test(priority=6)
	public void FullMap() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Full View')]")).click();
		WebElement ZoomOut=driver.findElement(By.xpath("//button[@class='mapboxgl-ctrl-icon mapboxgl-ctrl-zoom-out']"));
		ZoomOut.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\FullViewMap\\ZoomOut.png"); 
		WebElement ZoomIn=driver.findElement(By.xpath("//button[@class='mapboxgl-ctrl-icon mapboxgl-ctrl-zoom-in']"));
		ZoomIn.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\FullViewMap\\ZoomIn.png"); 
			driver.findElement(By.xpath("//div[@class='mapLayerStyles']")).click();
		WebElement Street=driver.findElement(By.id("radStreets"));
		Street.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\FullViewMap\\StreetView.png"); 
		WebElement Satellite=driver.findElement(By.id("radSatellite"));
		Satellite.click();
		Thread.sleep(1000);
		 screenshot.ScreenshotFunction(driver, "Dashboard\\FullViewMap\\SatelliteView.png"); 
		driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable fa fa-fw fa-close']")).click();		
	}
	
}
	
	

