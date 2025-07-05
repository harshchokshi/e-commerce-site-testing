package testreporting;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Instant;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestReport {
	
	   WebDriver driver;
	   ExtentReports extent;
	   ExtentSparkReporter spark;
	   ExtentTest test;
	   String ScreenshotPath; 
	
	
	public TestReport(WebDriver driver) {
		this.driver = driver;
		this.extent = new ExtentReports();
		this.spark = new ExtentSparkReporter("testreport/TestReport.html");
		this.extent.attachReporter(spark);	
		this.ScreenshotPath = "target/Spark/";
	}
	
	public void startTest(String TestName) {
		test= extent.createTest(TestName);
		test.log(Status.INFO, "Start of Test");
	}
	public void endTest() {
		test.log(Status.INFO, "End of Test");
	}
	
	public void endTestSuite() {
		test.log(Status.INFO, "End of Test Suite");
		extent.flush();
	}
	
	public void logPass(String checkpointDetails) {
	    try {
	        String screenshotFilePath = getScreenshot();
	        test.pass(checkpointDetails, MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());
	    } catch (IOException e) {
	        test.pass(checkpointDetails + " (Screenshot not available)");
	        test.info("IOException during screenshot: " + e.getMessage());
	    }
	}

	public void logFail(String checkpointDetails) {
	    try {
	        String screenshotFilePath = getScreenshot();
	        test.fail(checkpointDetails, MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());
	    } catch (IOException e) {
	        test.fail(checkpointDetails + " (Screenshot not available)");
	        test.info("IOException during screenshot: " + e.getMessage());
	    }
	}


	
	public  void logInfo(String checkpointDetails) {
		test.info(checkpointDetails);
	}
	
	public String getScreenshot() throws IOException {
		 
	    String fileName = "screenshot" + timeStamp() + ".png";
	    String directory = "testreport/screenshots/";
	    new File(directory).mkdirs();
	    String screenshotFilePath = directory + fileName;
	    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screenshotFile, new File(screenshotFilePath));
	    return "screenshots/" + fileName;
	}
	
	public String timeStamp() {
	    Instant instant = Instant.now();
	    return instant.toString().replace("-", "_").replace(":", "_").replace(".", "_");
	}


}
