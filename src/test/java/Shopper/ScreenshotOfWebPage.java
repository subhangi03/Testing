package Shopper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotOfWebPage {
	public static void main(String[] args) throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.selenium.dev/");
		
		//1. DOWNCAST WEB DRVER REF TO TAKE4S SCREENSHOT
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		//2.CALL THE getScreenshotAs() and pass OutputType.FILE as an argument of File type
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		//3.DECLARE LOCATION OF FILE WHERE IT IS TO BE STORED. PASS THE PATH OF THE FILE  AS AN ARGUMENT TO THE CONSTRUCTOR 
		File target=new File("C:\\Users\\SUBHANGI\\Desktop\\Capgemini VVAT Training\\apiTesting\\screenshot\\screenshot1.png");
		
		//Source and Target
		//4.CALL THE COPY() OF FILEHANDLER CLSS AND PASS SOURCE VARIABLE AND TARGET
		FileHandler.copy(source,target);
		System.out.println("commiting in branch");
	}
}
