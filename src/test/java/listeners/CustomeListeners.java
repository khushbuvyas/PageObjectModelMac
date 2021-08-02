package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.Page;
import utilities.TestUtil;

public class CustomeListeners extends Page implements ITestListener {

	public void onTestStart(ITestResult result) {

		test = report.startTest(result.getName().toUpperCase());
		
		/*if (!TestUtil.isTestRunnable(result.getName(), excel)) {

			throw new SkipException("Test case: openAccountTest is Skipped as runmode is NO");
		}*/
		
	}

	public void onTestSuccess(ITestResult result) {

		// ExtentReport
		test.log(LogStatus.PASS, result.getName().toUpperCase(), " PASSED");
		// test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		report.endTest(test);
		report.flush();
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String methodName = result.getName();

		try {
			TestUtil.captureScreenshot(methodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ReportNg
		Reporter.log("Click to see screenshot !!!");
		Reporter.log("<a href=" + TestUtil.screenshotName + " target=\"_blank\">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=" + TestUtil.screenshotName + " target=\"_blank\"><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");

		// ExtentReport

		test.log(LogStatus.FAIL, methodName.toUpperCase(), " FAILED");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {

		try {
			TestUtil.captureScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExtentReport
		test.log(LogStatus.SKIP, result.getName().toUpperCase(), " SKIPPED the test cases as Runmode set to NO");
		test.log(LogStatus.SKIP, test.addScreenCapture(TestUtil.screenshotName));
		report.endTest(test);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
