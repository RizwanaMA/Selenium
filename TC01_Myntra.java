package selenium_workout.Selenium_workout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC01_Myntra {
	
	public static void main(String[] args) throws InterruptedException {
		
		  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		  ChromeDriver driver = new ChromeDriver();
		  driver.get("https://www.myntra.com/");
		  driver.manage().window().maximize();
		  Thread.sleep(2000);
		  WebElement womenMbar = driver.findElementByXPath("(//div[@class='desktop-navLinks']/div)[2]");
		  Actions builder = new Actions(driver);
		  builder.moveToElement(womenMbar).perform();
		  
		  driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
		  String count = driver.findElementByXPath("//span[@class='title-count']").getText();
		  String jcTotal = count.replaceAll("\\D", "");
		  int intJC = Integer.parseInt(jcTotal);
		  System.out.println(intJC);
		  
			String jacketCount = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
			String jCount = jacketCount.replaceAll("\\D", "");
			int intJacket = Integer.parseInt(jCount);
			System.out.println(intJacket);
			
		 
		String coatCount = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String cCount = coatCount.replaceAll("\\D", "");
		int intCoat = Integer.parseInt(cCount);
		System.out.println(intCoat);
		
		int total= intJacket+intCoat;
		System.out.println(total);
		
		/*
		 * if(intJC.equals) System.out.println("Count Matched"); else
		 * System.out.println("Count is not Matching");
		 */
		
		driver.findElementByClassName("common-checkboxIndicator").click();
		driver.findElementByClassName("brand-more").click();
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
		driver.findElementByXPath("(//label[@class='vertical-filters-label common-customCheckbox']/input)[3]").click();
		
		
		
		
		
		
		
		
		
		
		
	}
}
