package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.Page;


public class TestUtil extends Page{

	@DataProvider(name="dp1")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName();
		
		log.debug("Get the method name as - "+m.getName());
		
		int rowCount = excel.getRowCount(sheetName);
		int colCount = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rowCount-1][1];
		Hashtable<String,String> table;
		
		for(int rowNum=2; rowNum<=rowCount; rowNum++) {
			
			table = new Hashtable<String,String>();
			
			for(int colNum=0; colNum<colCount; colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				
				data[rowNum-2][0] = table;
			}
		}
		
		return data;
	}
	
	
	public static String screenshotName;
	public static String screenshotName1;

	public static void captureScreenshot(String method) throws IOException {
		
		Date d = new Date();
		screenshotName = method+"_"+d.toString().replace(":", "_").replace(" ", "_")+".jpg";

		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenshotName));
		
	}
	
public static void captureScreenshot() throws IOException {
		
		Date d = new Date();
		screenshotName1 = d.toString().replace(":", "_").replace(" ", "_")+".jpg";

		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenshotName1));
		
	}

public static boolean isTestRunnable(String test, ExcelReader excel) {

	String sheetName = "testsuite";
	int rows = excel.getRowCount(sheetName);

	for (int rowNum = 2; rowNum <= rows; rowNum++) {

		String testcaseName = excel.getCellData(sheetName, "testcase", rowNum);

		if (testcaseName.equalsIgnoreCase(test)) {

			String runMode = excel.getCellData(sheetName, "runmodes", rowNum);

			if (runMode.equalsIgnoreCase("Y"))
				return true;
			else
				return false;
		}
	}
	return false;
	
}
	
}
