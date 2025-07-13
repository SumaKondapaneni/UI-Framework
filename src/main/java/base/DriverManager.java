package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.ConfigReader;

import java.time.Duration;
/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class DriverManager {
	private static final Logger logger = LogManager.getLogger(DriverManager.class);
	private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	// Initialize driver based on config.properties
	public static void initDriver() {
		if (driverThreadLocal.get() != null) {
			return;
		}

		String browser = ConfigReader.get("browser");
		String url = ConfigReader.get("app.url");

		logger.info("Initializing driver for browser: " + browser);

		WebDriver driver;

		switch (browser.toLowerCase()) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "chrome":
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(url);

		driverThreadLocal.set(driver);
		logger.info("Driver initialized and navigated to " + url);
	}

	// Get current thread’s driver
	public static WebDriver getDriver() {
		return driverThreadLocal.get();
	}

	// Cleanup
	public static void quitDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			logger.info("Quitting driver");
			driver.quit();
			driverThreadLocal.remove();
		}
	}
}
