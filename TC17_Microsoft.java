package selenium_workout.Selenium_workout;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC17_Microsoft {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notification");
		ChromeDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);

		// Go to https://azure.microsoft.com/en-in/
		driver.get("https://azure.microsoft.com/en-in/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Click on Pricing
		driver.findElement(By.id("navigation-pricing")).click();
		Thread.sleep(2000);

		// 3) Click on Pricing Calculator
		driver.findElement(By.xpath("(//ul[@class='linkList initial-list']//following::li)[2]/a")).click();
		Thread.sleep(2000);

		// 4) Click on Containers
		driver.findElement(By.xpath("//button[text()='Containers']")).click();
		Thread.sleep(2000);

		// 5) Select Container Instances
		driver.findElement(By.xpath("(//span[text()='Container Instances'])[3]")).click();
		Thread.sleep(2000);

		// 6) Click on Container Instance Added View
		driver.findElement(By.xpath("//a[text()='View']")).click();

		// 7) Select Region as "South India"
		Select regionDdown = new Select(driver.findElement(By.xpath("(//select[@name='region'])[1]")));
		regionDdown.selectByVisibleText("South India");

		// 8) Set the Duration as 180000 seconds
		driver.findElement(By.xpath("(//input[@name='seconds'])[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "180000",
				Keys.TAB);

		// 9) Select the Memory as 4GB
		Select memoryDdown = new Select(driver.findElement(By.xpath("(//select[@name='memory'])[1]")));
		memoryDdown.selectByVisibleText("4 GB");

		js.executeScript("window.scrollBy(0,700)");

		// 10) Enable SHOW DEV/TEST PRICING
		driver.findElement(By.xpath("//button[@name='devTestSelected']")).click();

		// 11) Select Indian Rupee as currency
		Select currencyDdown = new Select(driver.findElement(By.xpath("//select[@class='select currency-dropdown']")));
		currencyDdown.selectByVisibleText("Indian Rupee (₹)");

		// 12) Print the Estimated monthly price
		System.out.println("Estimated Monthly cost:"
				+ driver.findElement(By.xpath("(//span[@class='numeric'])[4]//span")).getText());

		// 13) Click on Export to download the estimate as excel
		driver.findElement(By.xpath("//button[text()='Export']")).click();
		Thread.sleep(8000);

		// 14) Verify the downloded file in the local folder
		File file = new File("C:\\Users\\Rizwana\\Downloads\\ExportedEstimate.xlsx");
		if (file.exists()) {
			System.out.println("File downloaded successfully");
		} else {
			System.out.println("File not found");
		}

		js.executeScript("window.scrollBy(700,0)");

		// 15) Navigate to Example Scenarios and Select CI/CD for Containers
		action.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).click().build().perform();
		driver.findElement(By.xpath("//button[@title='CI/CD for Containers']")).click();

		// 16) Click Add to Estimate
		driver.findElement(By.xpath("//button[text()='Add to estimate']")).click();
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,600)");

		// 17) Change the Currency as Indian Rupee
		Select currencyDdown2 = new Select(driver.findElement(By.xpath("//select[@class='select currency-dropdown']")));
		currencyDdown2.selectByVisibleText("Indian Rupee (₹)");

		// 18) Enable SHOW DEV/TEST PRICING
		driver.findElement(By.xpath("//button[@name='devTestSelected']")).click();

		// 19) Export the Estimate
		driver.findElement(By.xpath("//button[text()='Export']")).click();
		Thread.sleep(10000);

		// 20) Verify the downloded file in the local folder
		File file1 = new File("C:\\Users\\Rizwana\\Downloads\\ExportedEstimate (1).xlsx");
		if (file1.exists()) {
			System.out.println("File downloaded successfully");
		} else {
			System.out.println("File not found");
		}
	}

}
