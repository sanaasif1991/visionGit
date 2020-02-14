package Utilities;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FullMapUtil {
	public void ZoomOut(WebDriver driver) throws InterruptedException, IOException	{
		WebElement ZoomOut=driver.findElement(By.xpath("//button[@class='mapboxgl-ctrl-icon mapboxgl-ctrl-zoom-out']"));
		ZoomOut.click();
		Thread.sleep(1000);
	}
	public void ZoomIn(WebDriver driver) throws InterruptedException, IOException	{
		WebElement ZoomIn=driver.findElement(By.xpath("//button[@class='mapboxgl-ctrl-icon mapboxgl-ctrl-zoom-in']"));
		ZoomIn.click();
		Thread.sleep(1000);
	}
	public void Street(WebDriver driver) throws InterruptedException, IOException	{
			driver.findElement(By.xpath("//div[@class='mapLayerStyles']")).click();
		WebElement Street=driver.findElement(By.id("radStreets"));
		Street.click();
		Thread.sleep(1000);
	}
	public void Satellite(WebDriver driver) throws InterruptedException, IOException	{
		WebElement Satellite=driver.findElement(By.id("radSatellite"));
		Satellite.click();
		Thread.sleep(1000);
	}
	

}
