package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties properties;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// loading config.properties file

		FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");

		properties = new Properties();
		properties.load(fis);

		logger = LogManager.getLogger(this.getClass()); // log4j2

		// Selenium Grid remote environment (select env in config.propertis)- taking too much time 
		
		if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);

			} else if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			} else {
				System.out.println("System not matching");
				return;
			}

			// browser

			switch (br.toLowerCase()) {

			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browser ");
				return;
			}
//			logger.info("Trying to connect to Selenium Grid at http://192.168.0.106:4444/wd/hub with browser: " + br);

			driver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		}

		// if execution environment is local

		if (properties.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("invalid browser name ");
				return;
			}
		}
		
//    convert into local environment above in step 10 selenium grid
    /*
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("invalid browser name ");
			return;
		}
*/
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(properties.getProperty("appUrl"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);

		return (generatedString + "$" + generatedNumber);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;

	}
}
