package selenium_workout.Selenium_workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC14_Zalando {

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
		driver.get("https://www.zalando.com/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Get the Alert text and print it
		Alert alert = driver.switchTo().alert();
		String txtAlert = alert.getText();
		System.out.println("Alert Text:" + txtAlert);

		// Close the Alert box and click on Zalando.uk
		alert.accept();
		driver.findElement(By.xpath("//a[text()='Zalando.uk']")).click();
		Thread.sleep(5000);
		try {
			driver.findElement(By.id("uc-btn-accept-banner")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click Women--> Clothing and click Coat
		driver.findElement(By.xpath("(//span[text()='Women'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='Clothing']")).click();
		driver.findElement(By.xpath("(//a[@class='cat_link-mTK6o'])[2]")).click();
		Thread.sleep(3000);

		// Choose Material as cotton (100%) and Length as thigh-length
		driver.findElement(By.xpath("//span[text()='Material']")).click();
		driver.findElement(By.xpath("//span[text()='cotton (100%)']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Length']")).click();
		driver.findElement(By.xpath("//span[text()='thigh-length']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(3000);

		// Click on Q/S designed by MANTEL - Parka coat
		driver.findElement(By.xpath("(//a[contains(@href, 'qs-designed-by-mantel-parka')])[1]")).click();
		Thread.sleep(3000);

		// Check the availability for Color as Olive and Size as 'M'
		driver.findElement(By.xpath("(//img[@alt='olive'])[2]")).click();
		driver.findElement(By.xpath("//button[@id='picker-trigger']")).click();
		driver.findElement(By.xpath("//span[text()='M']")).click();

		// If the previous preference is not available, check availability for Color
		if (driver.findElement(By.xpath("//h2[text()='Out of stock']")).isDisplayed()) {
			driver.findElement(By.xpath("(//img[@alt='navy'])[2]")).click();
			System.out.println("Olive isn't available, Selected Navy coat");
		} else {
			driver.findElement(By.xpath("//button[@id='picker-trigger']")).click();
			driver.findElement(By.xpath("//span[text()='M']")).click();
			System.out.println("Oilve is in stock, Selected the Olive coat");
		}

		// Navy and Size 'M'
		driver.findElement(By.xpath("//span[text()='Choose your size']")).click();
		driver.findElement(By.xpath("//span[text()='M']")).click();

		// Add to bag only if Standard Delivery is free
		if (driver.findElement(By.xpath("(//span[text()='Free'])")).isDisplayed()) {
			driver.findElement(By.xpath("(//span[text()='Add to bag'])")).click();
			System.out.println("Free Delivery Available");
		} else {
			System.out.println("Standard Delivery fees may apply");
		}
		Thread.sleep(3000);
		// Mouse over on Your Bag and Click on "Go to Bag"
		ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Your bag']"));
		driver.findElement(By.xpath("//div[text()='Go to bag']")).click();
		Thread.sleep(3000);
		// Capture the Estimated Deliver Date and print
		System.out.println(driver.findElement(By.xpath("//div[@data-id='delivery-estimation']//span")).getText());
		
		// Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Free delivery & returns*']"));
		System.out.println(driver.findElement(By.xpath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]")).getAttribute("title"));
		
		// Click on FREE DElIVERY & RETURNS*
		driver.findElement(By.xpath("//a[text()='Free delivery & returns*']")).click();
		Thread.sleep(5000);
		
		// Click on Start chat in the Start chat and go to the new window
		driver.findElement(By.xpath("(//button[@class='faq-dx-button'])[1]")).click();
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		Thread.sleep(3000);
		
		// Enter you first name and a dummy email and click Start Chat
		driver.findElement(By.id("prechat_customer_name_id")).sendKeys("Rizu");
		driver.findElement(By.id("prechat_customer_email_id")).sendKeys("mymail@mail.com");
		driver.findElement(By.id("prechat_submit")).click();
		Thread.sleep(6000);
		
		// Type Hi, click Send and print thr reply message and close the chat window.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("liveAgentChatTextArea"))).sendKeys("Hello");
		driver.findElement(By.xpath("//button[text()='Send']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("(//span[@class='messageText'])[last()]")).getText());
		//driver.findElement(By.xpath("//button[text()='End Chat']")).click();
		driver.close();
		driver.quit();
		}
}