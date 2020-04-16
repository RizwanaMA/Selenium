package selenium_workout.Selenium_workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC02_Nykaa {
	
	public static void main(String[] args) throws InterruptedException {
		
		  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("--disable-Notification");
		  ChromeDriver driver = new ChromeDriver(options);
		  driver.get("https://www.nykaa.com/");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		  WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		  Actions builder = new Actions(driver);
		  builder.moveToElement(brands).perform();
		  
		  driver.findElementByXPath("//a[text()='Popular']").click();
		  driver.findElementByXPath("(//li[@class='brand-logo menu-links'])[5]").click();
		  
		  Set<String> window = driver.getWindowHandles();
		  List<String> winList = new ArrayList<String>(window);
		  driver.switchTo().window(winList.get(1));
		  
		  driver.getTitle();
		  
		  //System.out.println(driver.getTitle());
		  
		  if(driver.getTitle().contains("L'Oreal Paris"))
			  System.out.println("Loreal Paris is displayed");
		  else
			  System.out.println("The Name Loreal Paris Not displayed");
		  
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollBy(0,1000)");
		 */
		  Thread.sleep(5000);
		  driver.findElementByXPath("//span[@class='pull-right']").click();
		  driver.findElementByXPath("//span[text()='customer top rated']").click();
		  Thread.sleep(2000);
		  driver.findElementByXPath("//div[text()='Category']").click();
		  driver.findElementByXPath("//span[text()='Shampoo (21)']").click();
		  String filterTxt = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']//li").getText();
		  System.out.println(filterTxt);
		  if(filterTxt.contains("Shampoo"))
			  System.out.println("Filter Applied");
		  else
			  System.out.println("Filter not Applied");
			  
		  driver.findElementByXPath("(//div[@class='m-content__product-list__title']/h2)[4]").click();
		  
		  Set<String> window2 = driver.getWindowHandles();
		  List<String> winList2 = new ArrayList<String>(window2);
		  driver.switchTo().window(winList2.get(2));
		  System.out.println(driver.getTitle());
		  
		
		  
		  driver.findElementByXPath("(//span[@class='size-pallets'])[2]").click();
		  
		  String MRP = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText(); String
		  regMRP = MRP.replaceAll("\\D", "");
		  System.out.println("MRP of the product:"+regMRP);
		  
		  driver.findElementByXPath("(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])[1]").click();
		  Thread.sleep(5000);
		 
		  driver.findElementByClassName("AddToBagbox").click();
		  
		  Thread.sleep(3000); 
		  String grandTot =driver.findElementByXPath("//div[@class='value medium-strong']").getText(); 
		  String reggrandTot = grandTot.replaceAll("\\D", "");
		  System.out.println("Final Total Amount:"+reggrandTot);
		  
		  driver.findElementByClassName("proceed-arrow").click();
		  Thread.sleep(2000);
		  driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		  
		  Thread.sleep(2000);
		  System.out.println(driver.findElementByXPath("//div[@class='message']").getText());
		  
		  driver.quit();
		 
	}
}
