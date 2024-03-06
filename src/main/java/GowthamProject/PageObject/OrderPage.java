package GowthamProject.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GowthamProject.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> Orderlist;
	
	@FindBy(xpath="//tbody/tr/th")
	List<WebElement> OrdernumberList;
	
	
	public String FetchOrderName(String ProductName) {
		WebElement LatestOrder = Orderlist.stream().filter(product->product.getText().equalsIgnoreCase(ProductName)).findFirst().orElse(null);
		return LatestOrder.getText();
	}
	
	public List<WebElement> FetchOrderNo(String OrderID) {
		return OrdernumberList;
		
	}
		
}
