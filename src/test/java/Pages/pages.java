package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumBy.ById;

public class pages {
	WebDriver driver;
	public pages (WebDriver driver) {
		this.driver=driver;
	}
	public static By Email = ById.accessibilityId(" ");  
	 //Can use Any ID to find the elements
}