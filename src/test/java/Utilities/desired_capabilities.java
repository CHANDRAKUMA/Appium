package Utilities;



import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import logs.logs;

public class desired_capabilities {
	 public static AndroidDriver driver_Android;
	 public static IOSDriver driver_IOS;
	 public static AppiumDriver desired_capabilities_IOS(String Orientation) {	 
		 try {
			// JSON File name
		 	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		 	desiredCapabilities.setCapability("","");   
		 	//All Required Capabilities 
		       		URL remoteUrl;
				remoteUrl = new URL("http://127.0.0.1:4723/");
				driver_IOS = new IOSDriver(remoteUrl, desiredCapabilities);
		} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		return driver_IOS;    
	}
	 public static AppiumDriver capabilities_Android(String Orientation) {
		 
		 try {
			// JSON File name
		 	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		 	desiredCapabilities.setCapability("","");   
		 	// All Required Capabilities 
		       		URL remoteUrl;
				remoteUrl = new URL("http://127.0.0.1:4723/");
				driver_Android = new AndroidDriver(remoteUrl, desiredCapabilities);
		} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return driver_Android;
	}
	
}