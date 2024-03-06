package GowthamProject.TestComponent;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import GowthamProject.PageObject.landing_page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test {

	public WebDriver driver;
	public landing_page landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\GowthamProject\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null? System.getProperty("browser"):prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}			
			driver = new ChromeDriver(options);
			

		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String JsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = (List<HashMap<String, String>>) mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot Capture = (TakesScreenshot) driver;
		File source = Capture.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFileToDirectory(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public landing_page launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new landing_page(driver);
		landingPage.goTo();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.close();
	}

}
