package com.insta;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LikePost {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\E080014\\Desktop\\chromedriver.exe");
		
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Pixel 2");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		
		ChromeDriver driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.instagram.com/explore/");
		Thread.sleep(5000);
		
		driver.findElementByName("username").sendKeys("max.payne.1950@gmail.com");// username
		driver.findElementByName("password").sendKeys("Instagram@123");// password
		driver.findElementByXPath("//button[text()='Log in']").click();
		
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElementByXPath("//button[text()='Log in']")));
		
		driver.findElementByXPath("//button[text()='Not Now']").click();
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@type='search']")));
		Thread.sleep(9000);
		driver.findElementByClassName("_9AhH0").click(); // click new post
		Thread.sleep(5000);
		
		int count =1; 
		while (driver.findElementByXPath(String.format("(//span[contains(@class,'glyphsSpriteHeart__outline')])[%d]",count)).isDisplayed()) {
			WebElement element  = driver.findElementByXPath(String.format("(//span[contains(@class,'glyphsSpriteHeart__outline')])[%d]",count));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(3000);
			element.click();
			System.out.println("element::::"+ String.format("(//button[contains(@class,'coreSpriteHeartOpen')])[%d]",count));
			count ++;
		}
		
	}

}
