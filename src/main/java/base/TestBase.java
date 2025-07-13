
package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.qameta.allure.Attachment;
import utils.ScreenshotUtil;


/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */


public class TestBase {

	@BeforeMethod
	public void setUp() {
		DriverManager.initDriver();
	}
	
	
	
	public void captureScreenshotOnFailure(String testName) {
        ScreenshotUtil.takeScreenshot(getDriver(), testName);
        attachScreenshotToAllure(getDriver());
    }

    // Optional for Allure reports
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshotToAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

	public WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}