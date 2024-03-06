package GowthamProject.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GowthamProject.PageObject.CartPage;
import GowthamProject.PageObject.CheckOutPage;
import GowthamProject.PageObject.OrderPage;
import GowthamProject.PageObject.Product_Catalogue;
import GowthamProject.PageObject.SummaryPage;
import GowthamProject.PageObject.landing_page;
import GowthamProject.TestComponent.Base_Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderSubmission extends Base_Test{

	public String OrderID;
	@Test(dataProvider="getData",groups={"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		landingPage.goTo();
		Product_Catalogue ProductCatalogue = landingPage.loginAction(input.get("email"),input.get("Password"));
		WebElement Product = ProductCatalogue.getProductByName(input.get("Product"));
		ProductCatalogue.AddToCart(Product);
		CartPage CartPage = ProductCatalogue.goToCart();
		Boolean Match = CartPage.cartValidation(input.get("Product"));
		Assert.assertTrue(Match);
		CheckOutPage CheckOutPage = CartPage.goToCheckOut();
		SummaryPage SummaryPage = CheckOutPage.placeOrder();
		OrderID = SummaryPage.Summary();
		System.out.println(SummaryPage.ConfirmationTxt());
		OrderPage OrderPage = landingPage.goToOrdersPage();
		Assert.assertEquals(input.get("Product"), OrderPage.FetchOrderName(input.get("Product")));
		List<WebElement> LatestOrderID = OrderPage.FetchOrderNo(OrderID);
		String ModifiedOrderID = ("| "+LatestOrderID.get(0).getText()+" |");
		Assert.assertEquals(OrderID, ModifiedOrderID);
		
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\GowthamProject\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
	
	
	/*   
	 * HashMap<String,String> Map = new HashMap<String,String>(); Map.put("email",
	 * "gowthampsg98@gmail.com"); Map.put("Password","Selenium@24");
	 * Map.put("Product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> Map1 = new HashMap<String,String>(); Map1.put("email",
	 * "anshika@gmail.com"); Map1.put("Password","Iamking@000"); Map1.put("Product",
	 * "ADIDAS ORIGINAL");
	 */
	
	
	/*
	 * @DataProvider public Object getData() { return new Object[][]
	 * {{"gowthampsg98@gmail.com","Selenium@24","ZARA COAT 3"},
	 * {"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}}; }
	 */
}
