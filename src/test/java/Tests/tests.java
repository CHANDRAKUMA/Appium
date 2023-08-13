package Tests;

	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Listeners;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;
	import java.io.IOException;
	import java.time.LocalTime;
	import io.appium.java_client.AppiumDriver;


	@Listeners(ListenerTest.class)		

	public class tests {
	public static AppiumDriver driver;
		String clname = this.getClass().getSimpleName(); 
		LocalTime time;
		@BeforeTest
		@Parameters({"drivername","driverorientation"})
		public void Devicesetup(String drivername,String orientation) throws InterruptedException, IOException {
			time =LocalTime.now();
			switch (drivername) {
			  case "IOS":
				 // Code for IOS driver invoke
			 break;
			  case "Android":
				// Code for Android driver invoke
				  break;
			}}
		
		@BeforeMethod
		@Parameters({"drivername","driverorientation"})
		public void Video (String drivername,String orientation)  {
			// Code for Video Start
		}
		@BeforeClass
		@Parameters({"drivername","driverorientation"})
		public void Report(String drivername,String orientation)  {
			// Code for Report Start
		}
		@AfterClass
		@Parameters({"Rowvalue","drivername"})
		public void Reportflush(int cell,String drivername)  {
			// Code for Report flush
		}
			
		@AfterMethod
		@Parameters({"drivername","driverorientation","Author","Device","Category"})
		public void Report(ITestResult result,String drivername,String orientation,String Author,String Device,String Category) {
			// Code for Video Stop
			// Code for  Screen Shots
			if (result.isSuccess()) {
			// Code for report Add for Pass Cases
			}else {
			// Code for report Add for Fail Cases
			}
		}
	@Test
	@Parameters({"sheetname","cellvalue"})
	public void  TestName (String sheetname,int cellvalue){
			// Code for test
	 }

	}

