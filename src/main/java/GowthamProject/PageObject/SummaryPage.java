package GowthamProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GowthamProject.AbstractComponents.AbstractComponent;

public class SummaryPage extends AbstractComponent{
	WebDriver driver;
	public SummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//label[@class='ng-star-inserted']")
	WebElement Order;
	
	@FindBy(xpath="//h1[text()=' Thankyou for the order. ']")
	WebElement ConfirmTxt;
	
	public String Summary() {
		String OrderId = Order.getText();
		return OrderId;
	}
	
	public String ConfirmationTxt() {
		String msg = ConfirmTxt.getText();
		return msg;
	}
}
