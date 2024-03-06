package GowthamProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GowthamProject.AbstractComponents.AbstractComponent;

public class Product_Catalogue extends AbstractComponent{
	WebDriver driver;
	public  Product_Catalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	//List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> Products;
	
	@FindBy(css=".ng-animating")
	WebElement animator;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	By AddToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");
	By Product = By.cssSelector(".mb-3");
	
	
	
	
	public List<WebElement> getProductList (){
		WaitForElementToAppear(Product);
		return Products;
		
	}
	
	public WebElement getProductByName(String ProductName) {
		WebElement Product = Products.stream().filter(s->s.findElement(By.cssSelector("b"))
				.getText().contains(ProductName)).findFirst().orElse(null);
		return Product;
	}
	
	public void AddToCart(WebElement Product)
	{
		Product.findElement(AddToCart).click();
		WaitForElementToAppear(toastContainer);
		WaitForElementToDisappear(animator); 
	}
	
	
	public CartPage goToCart() {
		cart.click();
		return new CartPage(driver);
	}
	
	
	
}
