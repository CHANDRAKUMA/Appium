package Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class Actions {
	public static String Screenshot = null;
	public static String Video = null;
	public static String Frameworkfolder = null;
	public static int i = 0;
	public static String nameofCurrMethod;
	public static String TestResources;
	public static String TestData;
	public static String DataProviderExcelFile;
	public static String Report;
	public static String IOSDevice;
	public static String IOSApp;
	public static String AndroidDevice;
	public static String AndroidApp;
	public static String Values;


	public static void waitforelement(AppiumDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	}

	public static  void  scrolldown(AppiumDriver driver,double percent,String direction)  {
		String drivername;
	    int height = driver.manage().window().getSize().getHeight();
	    int width = driver.manage().window().getSize().getWidth();
		drivername = driver.getCapabilities().getCapability("platformName").toString();
		String Decide = "";
		if (drivername.equals("ANDROID")) {
			Decide="Gesture";
		}
		((JavascriptExecutor) driver).executeScript("mobile: scroll"+Decide, ImmutableMap.of(
			    "left", 200, "top", 100, "width", width/2, "height", height/2,
			    "direction", direction,
			    "percent", percent
			));
	}
	
	public static  void  drag(AppiumDriver driver,WebElement element)  {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "endX", 100,
			    "endY", 100
			));
	}
	public static void screenshot(AppiumDriver driver,String drivername,String filename,String folder) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = Screenshot+"/"+folder+"/"+filename+"_"+drivername+".jpg";
		File DestFile=new File(path);
		try {
			FileUtils.copyFile(scrFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void videostart(AppiumDriver driver,String filename) {
		((CanRecordScreen) driver).startRecordingScreen(
	            new IOSStartScreenRecordingOptions()
	                    .withVideoScale("1280x720")
	                    .withVideoType("mpeg4")
	                    .withTimeLimit(Duration.ofSeconds(1000)));
		}

	public static void videostop(AppiumDriver driver,String filename){
		 String video =((CanRecordScreen)driver).stopRecordingScreen();
	     byte[] decode = Base64.getDecoder().decode(video);
	     try {
			FileUtils.writeByteArrayToFile(new File(Video+"/"+filename+".mov"),decode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void excelvalues(String dataname,int cellvalue)  {
		  nameofCurrMethod = new Throwable()
                .getStackTrace()[1]
                .getMethodName();
		// JSON file
		FileInputStream file;
		try {
		file = new FileInputStream(new File("DataProviderExcelFile"));
		XSSFWorkbook workbook;
		workbook = new XSSFWorkbook(file);
		XSSFCell cell1 = workbook.getSheet(dataname).getRow(i).getCell(cellvalue);
		int celltype = cell1.getCellType();
		if (celltype==0) {
			double CVD = cell1.getNumericCellValue();
			Values = String.valueOf((int)CVD);
		}
		else if (celltype==4) 
		{
		boolean BVC = cell1.getBooleanCellValue();
			Values = String.valueOf(BVC); 
		}
		else
		{
			Values = cell1.getStringCellValue();
		}
		i=i+1;
		PrintWriter out = new PrintWriter(new FileWriter(Frameworkfolder+"/outlogsfromexcel.txt", true), true);
	      out.write(nameofCurrMethod+":"+Values+"\n");
	      out.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}	
	}	
	public  static  void JSONreader(){
		 JSONParser parser = new JSONParser();
	     try {
	        Frameworkfolder = System.getProperty("user.dir");
	        Object obj = parser.parse(new FileReader(Frameworkfolder+"/LocalSettings.json"));
	        JSONObject jsonObject = (JSONObject)obj;
	        Frameworkfolder = System.getProperty("user.dir");
	        TestResources = Frameworkfolder+"/"+(String)jsonObject.get("TestResources");
	        TestData = Frameworkfolder+"/"+(String)jsonObject.get("TestData");
	        DataProviderExcelFile =TestData+"/"+(String)jsonObject.get("DataProviderExcelFile");
	        Screenshot = Frameworkfolder+"/"+(String)jsonObject.get("Screenshot");
	        Report = Frameworkfolder+"/"+(String)jsonObject.get("Report");
	        Video = Frameworkfolder+"/"+(String)jsonObject.get("Video");
	        IOSDevice = (String)jsonObject.get("IOSDevice");
	        IOSApp = TestResources+"/"+(String)jsonObject.get("IOSApp");
	        AndroidDevice = (String)jsonObject.get("AndroidDevice");
	        AndroidApp = TestResources+"/"+(String)jsonObject.get("AndroidApp");
	        } catch(Exception e) {
	        e.printStackTrace();
	     }
}
}
