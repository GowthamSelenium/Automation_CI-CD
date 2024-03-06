package GowthamProject.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import GowthamProject.PageObject.landing_page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		landing_page landingPage = new landing_page(driver);
		driver.findElement(By.id("userEmail")).sendKeys("gowthampsg98@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@24");
		driver.findElement(By.id("login")).click();
		List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
		//System.out.println(driver.findElement(By.xpath("//div[@class='card']/div/h5/b")).getText());
		//Just adding The Comment with name Gowtham.
		WebElement ProductName = Products.stream().filter(s->s.findElement(By.cssSelector("b"))
				.getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		WebElement Desired_Product = ProductName.findElement(By.cssSelector("b"));
		ProductName.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
		
		List<WebElement> ProductCart = driver.findElements(By.xpath("//div[@class='cartSection'] //h3"));
		Boolean Match = ProductCart.stream().anyMatch(s->s.getText().equals("ZARA COAT 3"));
		Assert.assertTrue(Match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List <WebElement> CountrySugg = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		WebElement DesiredCountry = CountrySugg.stream().filter(Country->Country.getText().equals("India")).findFirst().orElse(null);
		DesiredCountry.click();
		driver.findElement(By.xpath("//div[text()='CVV Code ']/following-sibling :: input[@class='input txt']")).sendKeys("1234");
		driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling :: input[@class='input txt']")).sendKeys("GowthamMurugesan");
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		System.out.println(driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText());
		driver.close();
		
		}



}
