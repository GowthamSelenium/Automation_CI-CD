package GowthamProject.Tests;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	import GowthamProject.PageObject.CartPage;
	import GowthamProject.PageObject.CheckOutPage;
	import GowthamProject.PageObject.Product_Catalogue;
	import GowthamProject.PageObject.SummaryPage;
	import GowthamProject.PageObject.landing_page;
	import GowthamProject.TestComponent.Base_Test;
import GowthamProject.TestComponent.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

	public class Error_Validation extends Base_Test{

		@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void ErrorValidations() throws IOException{
			landingPage.goTo();
			landingPage.loginAction("gowthampsg98@gmail.com","Selenium24");
			Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
			
			

		}

	}


  