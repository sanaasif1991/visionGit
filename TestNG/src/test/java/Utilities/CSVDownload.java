package Utilities;

import java.io.File;
import java.io.IOException;

import org.testng.Reporter;

public class CSVDownload {
	 public static void waitForTheExcelFileToDownload(String fileName, int timeWait)
             throws IOException, InterruptedException {
         String downloadPath ="C:\\Users\\krishnasivanip\\Downloads";
         File dir = new File(downloadPath);
         File[] dirContents = dir.listFiles();

         for (int i = 0; i < dirContents.length; i++) {
             if (dirContents[i].getName().equalsIgnoreCase(fileName)) {
            	 Reporter.log(fileName + "File found");
                 break;
             }else {
                 Thread.sleep(timeWait);
             }
         }
     }

}
