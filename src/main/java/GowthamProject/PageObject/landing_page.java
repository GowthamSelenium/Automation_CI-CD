package GowthamProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GowthamProject.AbstractComponents.AbstractComponent;

public class landing_page extends AbstractComponent {
	WebDriver driver;
	public  landing_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement Passwordelement;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement Submit_Btn;
	
	@FindBy(xpath="//div[@id='toast-container']/div")
	WebElement Error;
	
	@FindBy(xpath="//li/button[contains(text(),'ORDERS')]")
	WebElement OrdersTab;
	
	
	
	
	public Product_Catalogue loginAction(String email,String Password) {
		userEmail.sendKeys(email);
		Passwordelement.sendKeys(Password);
		Submit_Btn.click();
		Product_Catalogue ProductCatalogue = new Product_Catalogue(driver);
		return ProductCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		WaitForWebElementToAppear(Error);
		return Error.getText();
	}
	
	public OrderPage goToOrdersPage() {
		OrdersTab.click();
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
	}
}
