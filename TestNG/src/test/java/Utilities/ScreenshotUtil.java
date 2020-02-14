package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
 public void ScreenshotFunction(WebDriver driver,String filename) throws IOException {
	File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    File dest = new File("C:\\Users\\sanaa\\Desktop\\Vis\\"+ filename);
	    FileUtils.copyFile(scr, dest);
 }
}
