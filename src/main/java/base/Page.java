package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;

	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/Excel/Testdata.xlsx");
	public static WebDriverWait wait;

	public static ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;

	public Page() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/Properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("Config file loaded !!!");
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/Properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("OR file loaded !!!");

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);
				driver = new ChromeDriver();

			} else if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();

				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

			} else if (config.getProperty("browser").equals("safari")) {

				driver = new SafariDriver();

			}

		

		log.debug(config.getProperty("browser") + " launched !!");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://www.zoho.com/");

		log.debug("Open the TestURL !!!");

		menu = new TopMenu(driver);
		}

	}

	public static boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void isVerifyEquals(String expected, String actual) {

		try {

			Assert.assertEquals(expected, actual);

		} catch (Throwable t) {

			try {
				TestUtil.captureScreenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			test.log(LogStatus.FAIL, "Verification has failed !! " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName1));

			Reporter.log("<br>" + "Verification is failed-" + t.getMessage() + "<br>");
			Reporter.log("<a href=" + TestUtil.screenshotName1 + " target=\"_blank\"><img src="
					+ TestUtil.screenshotName1 + " height=200 width=200></img></a>");
			Reporter.log("<br>");

		}
	}

	// common action keyword that use in testcases

	public static void click(String locator) {

		if (locator.contains("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		} else if (locator.contains("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		} else if (locator.contains("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
		}

		
		  test.log(LogStatus.INFO, "Click on the - " + locator); report.endTest(test);
		  report.flush();
		 
	}

	public static void type(String locator, String value) {

		if (locator.contains("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
		} else if (locator.contains("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		} else if (locator.contains("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
		}

		
		  test.log(LogStatus.INFO, "Enter the data in " + locator + " on the - " +value); 
		  report.endTest(test); 
		  report.flush();
		 
	}

	public static WebElement element;
	public static Select select;

	public static void select(String locator, String value) {

		if (locator.contains("_CSS")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.contains("_XPATH")) {
			element = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.contains("_ID")) {
			element = driver.findElement(By.id(or.getProperty(locator)));
		}

		select = new Select(element);

		select.selectByVisibleText(value);
		
		  test.log(LogStatus.INFO, "Select the value as  " + value + " from - " +locator); 
		  report.endTest(test); 
		  report.flush();
		 

	}

	public static void quit() {

		if (driver != null) {

			driver.quit();
		}
	}
}
