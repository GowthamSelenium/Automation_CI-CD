package GowthamProject.StepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import GowthamProject.PageObject.CartPage;
import GowthamProject.PageObject.CheckOutPage;
import GowthamProject.PageObject.Product_Catalogue;
import GowthamProject.PageObject.SummaryPage;
import GowthamProject.PageObject.landing_page;
import GowthamProject.TestComponent.Base_Test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends Base_Test{

	public landing_page landingPage;
	public Product_Catalogue ProductCatalogue;
	public CheckOutPage CheckOutPage;
	public SummaryPage SummaryPage;
	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^I logged with (.+) and (.+)$")
	public void I_Logged_with_username_and_password(String Username, String Password) {
		ProductCatalogue = landingPage.loginAction(Username,Password);
	}
	
	@When("^I add the (.+) to Cart$")
	public void I_add_ProductName_to_cart(String ProductName) {
		WebElement Product = ProductCatalogue.getProductByName(ProductName);
		ProductCatalogue.AddToCart(Product);
		
	}
	
	@When("^I Checkout (.+) and Submit the Order$")
	public void I_Checkout_ProductName_and_submit_the_Order(String ProductName) {
		CartPage CartPage = ProductCatalogue.goToCart();
		Boolean Match = CartPage.cartValidation(ProductName);
		Assert.assertTrue(Match);
		CheckOutPage = CartPage.goToCheckOut();
		SummaryPage = CheckOutPage.placeOrder();
		
	}
	
	@Then("{string} message is displayed on the Confirmation Page")
	public void message_is_displayed_on_the_Confirmation_page(String string) {
		String ConfirmTxtMsg = SummaryPage.ConfirmationTxt();
		Assert.assertEquals(string, ConfirmTxtMsg);
		driver.close();
	}
	
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string){
		Assert.assertEquals(string,landingPage.getErrorMessage());
	}
}
