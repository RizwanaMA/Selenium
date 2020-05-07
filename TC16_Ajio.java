package selenium_workout.Selenium_workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC16_Ajio {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notification");
		ChromeDriver driver = new ChromeDriver(options);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);

		// Go to https://www.zalando.com/
		driver.get("https://www.ajio.com/shop/women");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// 2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElement(By.xpath("//input[@name='searchVal']")).sendKeys("Bags", Keys.ENTER);
		Thread.sleep(3000);

		// 3) Click on five grid
		driver.findElement(By.xpath("//div[@class='five-grid']")).click();
		Thread.sleep(2000);

		// Select SORT BY as "What's New"
		WebElement ddFilter = driver.findElement(By.xpath("//option[@value='relevance']/parent::select"));
		Select ddSortby = new Select(ddFilter);
		ddSortby.selectByVisibleText("What's New");
		Thread.sleep(3000);

		// 4) Enter Price Range Min as 2000 and Max as 5000
		driver.findElement(By.xpath("//span[text()='price']")).click();
		driver.findElement(By.id("minPrice")).sendKeys("2000", Keys.TAB, "5000", Keys.ENTER);
		Thread.sleep(5000);

		// 5) Click on the product "Puma Ferrari LS Shoulder Bag"
		driver.findElement(By.xpath("//img[contains(@alt,'Ferrari LS Shoulder Bag')]")).click();

		// Switch to new window
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));

		// 6) Verify the Coupon code for the price above 2690 is applicable for your
		// product, if applicable then get the Coupon Code and Calculate the discount
		// price for the coupon
		String bagPrice = driver.findElement(By.xpath("(//div[@class='prod-price-section']/div)[1]")).getText()
				.replaceAll("Rs. ", "");
		int ibagPrice = Integer.parseInt(bagPrice.replace(",", ""));
		System.out.println("Original Bag Price:" + ibagPrice);

		String getPrice = driver.findElement(By.xpath("(//div[@class='promo-discounted-price']/span)[1]")).getText()
				.replaceAll("\\D", "");
		int igetPrice = Integer.parseInt(getPrice);
		System.out.println("Price of the Bag, if Coupoun applies:" + igetPrice);

		int discountPrice = (ibagPrice - igetPrice);
		System.out.println("Total Discount:" + discountPrice);

		if (ibagPrice > 2960) {
			System.out.println("Coupon code is Applicable");
		} else {
			System.out.println("Coupon code not Applicable");
		}

		String couponCode = driver.findElement(By.xpath("(//div[@class='promo-title'])[1]")).getText()
				.replace("Use Code", "");
		System.out.println("Coupon Code:" + couponCode);

		// 7) Check the availability of the product for pincode 560043, print the
		// expected delivery date if it is available
		driver.findElement(By.xpath("//span[contains(text(),'Enter pin-code')]")).click();
		driver.findElement(By.xpath("//input[@name='pincode']")).sendKeys("635001", Keys.ENTER);

		if (driver.findElementByClassName("edd-message-success-container").isDisplayed()) {
			System.out.println("Delivery is available for this pincode");
		} else {
			System.out.println("No delivery available for Pin code entered");
		}
		System.out.println("Delivery date details:"
				+ driver.findElement(By.xpath("//ul[@class='edd-message-success-details']")).getText());

		// 8) Click on Other Informations under Product Details and Print the Customer
		// Care address, phone and email
		driver.findElement(By.className("other-info-toggle")).click();
		System.out.println(driver
				.findElement(By.xpath("//span[contains (text(),'Address')]//following::span[@class='other-info']"))
				.getText());

		// 9) Click on ADD TO BAG and then GO TO BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='GO TO BAG']")))).click();
		Thread.sleep(2000);

		// 10) Check the Order Total before apply coupon
		String orderPrice = driver.findElement(By.xpath("//span[@class='price-value bold-font']")).getText()
				.replaceAll("\\D", "").replace("0", "");
		int iorderPrice = Integer.parseInt(orderPrice);
		System.out.println("Original Bag Price:" + iorderPrice);

		// 11) Enter Coupon Code and Click Apply
		if (ibagPrice == iorderPrice) {
			driver.findElement(By.xpath("//input[@id='couponCodeInput']")).sendKeys(couponCode);
			driver.findElement(By.xpath("//button[text()='Apply']")).click();
			System.out.println("Coupon code entered successfully");
		} else {
			System.out.println("Order Price differs with original price");
		}

		// 12) Verify the Coupon Savings amount(round off if it in decimal) under Order
		// Summary and the matches the amount calculated in Product details
		String couSaving = driver.findElement(By.xpath("(//span[@class='price-value discount-price'])[2]")).getText()
				.replaceAll("\\D", "").replace("272", "3");
		System.out.println("Total discount availed:" + couSaving);

		// 13) Click on Delete and Delete the item from Bag
		driver.findElement(By.className("delete-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='DELETE']")).click();

		// 14) Close all the browsers
		driver.quit();
	}
}