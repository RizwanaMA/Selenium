package selenium_workout.Selenium_workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC11_Snapdeal {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notification");
		ChromeDriver driver = new ChromeDriver(options);
		
		DesiredCapabilities cap = new DesiredCapabilities(); 
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(cap); 
		
		//Go to https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//Mouse over on Toys, Kids' Fashion & more and click on Toys
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='catText'])[8]")));
		driver.findElement(By.xpath("(//span[@class='catText'])[8]")).click();
		
		//Click Educational Toys in Toys & Games  
		driver.findElement(By.xpath("//span[text()='Educational Toys']")).click();
		
		//Click the Customer Rating 4 star and Up
		driver.findElement(By.xpath("//label[@for='avgRating-4.0']")).click();
		Thread.sleep(2000);
		
		//Click the offer as 40-50
		driver.findElement(By.xpath("//label[@for='discount-40%20-%2050']")).click();
		
		//Check the availability for the pincode  
		driver.findElement(By.xpath("(//input[@class='sd-input'])[2]")).sendKeys("600056");
		driver.findElement(By.xpath("//button[@class='pincode-check']")).click();
		Thread.sleep(3000);
		
		//Click the Quick View of the first product
		WebElement mhover = driver.findElementByXPath("(//img[@class='product-image wooble'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(mhover).perform();
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='product-image wooble'])[1]")));
		driver.findElement(By.xpath("(//div[contains (@class,'quick-view-bar')])[1]")).click();
		Thread.sleep(3000);
		
		//Click on View Details
		driver.findElement(By.xpath("//a[@class=' btn btn-theme-secondary prodDetailBtn']")).click();
		
		//Capture the Price of the Product and Delivery Charge		
		String productPrice = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String pCharg = productPrice.replaceAll("\\D", "");
		int intPCharg = Integer.parseInt(pCharg);
		System.out.println("The Product Price:" +intPCharg);
		
		String charges = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String dCharg = charges.replaceAll("\\D", "");
		int intDelCharges = Integer.parseInt(dCharg);
		System.out.println("Deliver Charges: " +intDelCharges);
		Thread.sleep(2000);
		//Add to Cart
		driver.findElement(By.id("add-cart-button-id")).click();
		
		//Validate the You Pay amount matches the sum of (price+deliver charge)
		String finalTotal = driver.findElement(By.xpath("(//span[@class='price'])[2]")).getText();
		String ftotal = finalTotal.replaceAll("\\D", "");
		int product1 = Integer.parseInt(ftotal);
		System.out.println("The Total amount of product 1: ****"+product1+"****");
		
		if(product1 == intPCharg + intDelCharges)
			System.out.println("Product of the price matches the final payment for checkout");
		else
			System.out.println("Product price and the final price differs");
		
		//Search for Sanitizer
		driver.findElementByXPath("//input[@id='inputValEnter']").sendKeys("BioAyurveda Neem Power Hand Sanitizer");
		driver.findElement(By.xpath("//span[@class='searchTextSpan']")).click();
		
		//Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		driver.findElement(By.xpath("//img[@title='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1']")).click();
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		
		//Capture the Price and Delivery Charge
		String saniPrice = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		
		String saniCharg = saniPrice.replaceAll("\\D", "");
		int intsaniCharg = Integer.parseInt(saniCharg);
		System.out.println("The Sanitizer Price:" +intsaniCharg);
		
		String sanDcharges = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String sanDCharg = sanDcharges.replaceAll("\\D", "");
		int intsanDCharges = Integer.parseInt(sanDCharg);
		System.out.println("Sanitizer Deliver Charges: " +intsanDCharges);
		
		int product2 = intsaniCharg + intsanDCharges;
		System.out.println("The Total amount of product 2: ****" +product2+"****");
		
		//Click on Add to Cart
		driver.findElement(By.id("add-cart-button-id")).click();
		
		//Click on Cart/Checkout popup
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cs-show-cart"))).click();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//Thread.sleep(2000);		
		//Validate the Proceed to Pay matches the total amount of both the products
		String checkPrice = driver.findElement(By.xpath("//input[contains (@value,'PROCEED TO PAY')]")).getAttribute("value");
		String checkoutReg = checkPrice.replaceAll("\\D", "");
		int finalCheckout = Integer.parseInt(checkoutReg);
		System.out.println("Checkout Amount: ****"+finalCheckout+"****");
	
		if (finalCheckout == product1 + product2)
			System.out.println("Prices matches the Final Checkout");
		else
			System.out.println("Final amount is not matched with the Checkout Amount");
			
		//Close all the windows
		driver.quit();	
	}
}
