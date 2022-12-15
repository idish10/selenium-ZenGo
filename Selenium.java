/**
 * This program tests various objects from zengo.com using 	Selenium web driver technology.
 * 
 */

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium {
	WebDriver driver;
	

	/**
	 * verify if the home page opens properly
	 * @param options to maximized the browser 
	 * 
	 */
	public void launchBrowser() {
		ChromeOptions options = new ChromeOptions().addArguments("start-maximized"); 
		System.setProperty("webdriver.chrome.driver", "C:/Users/dnisnbaum/Desktop/chromedriver.exe");
		driver= new ChromeDriver(options);
		driver.get("https://zengo.com/");
		if(!(driver.getTitle().equals("ZenGo - Simple & Secure Crypto Wallet App")))
			 System.exit(0);
	}
	
	public void search() throws InterruptedException  {
		/**
		 * verify if the "Free Bitcoin" button launching  properly
		 */
		Thread.sleep(2000);
		driver.findElement(By.linkText("Free Bitcoin")).click();
		Thread.sleep(2000);
		if (!(driver.getCurrentUrl().equals("https://zengo.com/free-bitcoin/"))) 
			System.exit(0);
						
	}
	/**
	 * verify if the "Download ZenGo" button launching a pop-up and uploading successfully a QR image 
	 * 
	 */
	
	public void download() throws InterruptedException  {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Download ZenGo")).click();
		Thread.sleep(2000);
		if(!(driver.findElement(By.id("popup-model")).isDisplayed()))
				System.exit(0);
		List<WebElement> listimg= driver.findElements(By.tagName("img"));
		
		/*Searching the QR Image */
		for(int i=0; i<listimg.size();i++) {
			if(listimg.get(i).getAttribute("src").equals("https://zengo.com/wp-content/themes/zengo/images/ZenGo-QR-1.png"))
				return;			
		/*Else the QR image is not displaying*/
			
		System.exit(0);

	}
	
	/**
	 * Navigate back to home page 
	 * @throws InterruptedException
	 */
	public void navigateBack() throws InterruptedException {
		driver.navigate().back();
		Thread.sleep(2000);
	}
	
	
	/**
	 * Closing the browser after the test completed
	 */
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Selenium obj= new Selenium();
		obj.launchBrowser();
		obj.search();
		obj.download();
		obj.navigateBack();
		obj.closeBrowser();	
	}
}
