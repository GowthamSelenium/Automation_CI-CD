package GowthamProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GowthamProject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	

	@FindBy(xpath="//div[@class='cartSection'] //h3")
	List<WebElement> CartProducts;
	
	

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement CheckOut;
	
	
	
	
	
	
	public boolean cartValidation(String Product)
	{
		
		
		Boolean Match = CartProducts.stream().anyMatch(s->s.getText().equals(Product));
		return Match;
	
	}
	
	public CheckOutPage goToCheckOut() {
		CheckOut.click();
		CheckOutPage CheckOutPage = new CheckOutPage(driver);
		return CheckOutPage;
	}

}
