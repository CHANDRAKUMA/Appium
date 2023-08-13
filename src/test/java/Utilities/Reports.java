package Utilities;

import java.time.LocalTime;
import java.util.Arrays;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Actions.Actions;
import logs.logs;

public class Reports {
	public static ExtentReports extents;
	public static ExtentTest exttest;
	public static ExtentSparkReporter htmlreporter;
	public static void html(String reportname) {
		// JSON file Name
		htmlreporter = new ExtentSparkReporter(Actions.Report+"/"+"extendreports_"+reportname+".html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setReportName("AutomationTestReports");
		htmlreporter.config().setTheme(Theme.DARK);
		extents = new ExtentReports();
		extents.setReportUsesManualConfiguration(true);
		extents.setSystemInfo("QA", "");
		extents.setSystemInfo("Organization", "MobileProgramming");
		extents.setSystemInfo("Device", "");
		extents.attachReporter(htmlreporter);
	}

	public static void ReportTest(String drivername,String testname,String testdata,String folder,LocalTime time,String Author,String Device,String Category){
		exttest = extents.createTest(testname).assignAuthor(Author).assignCategory(Category).assignDevice(Device);
		exttest.pass(MarkupHelper.createLabel("Test Started at"+time, ExtentColor.GREEN));
		exttest.pass(MarkupHelper.createLabel("Test Data is "+ testdata, ExtentColor.GREEN));
		exttest.pass("ScreenShot",MediaEntityBuilder.createScreenCaptureFromPath(Actions.Screenshot+"/"+folder+drivername+"/"+testname+"_"+drivername+".jpg").build());
		exttest.pass(MarkupHelper.createLabel("Test Sucessfully ended at "+LocalTime.now(), ExtentColor.GREEN));
		//logs(testname);
	}
	public static void ReportTestfail(String drivername,String testname,String testdata,String folder,LocalTime time,String Author,String Device,String Category){
		exttest = extents.createTest(testname).assignAuthor(Author).assignCategory(Category).assignDevice(Device);
		exttest.fail(MarkupHelper.createLabel("Test Started at"+time, ExtentColor.GREEN));
		exttest.fail(MarkupHelper.createLabel("Test Data is "+ testdata, ExtentColor.GREEN));
		exttest.fail("ScreenShot",MediaEntityBuilder.createScreenCaptureFromPath(Actions.Screenshot+"/"+folder+drivername+"/"+testname+"_"+drivername+".jpg").build());
		exttest.fail(MarkupHelper.createLabel("Test Failed at "+LocalTime.now(), ExtentColor.GREEN));
		//logs(testname);
	}
	public static void ReportTest2_IOS(String testname,String Author,String Device,String Category) {
		exttest = extents.createTest(testname).assignAuthor(Author).assignCategory(Category).assignDevice(Device);
		exttest.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[] {Actions.Values})));
		//logs(testname);
	}
	public static void Reportflush() {
		extents.flush();
	}

	
}