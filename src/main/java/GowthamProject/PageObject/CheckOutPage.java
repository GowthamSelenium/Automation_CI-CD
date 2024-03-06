package GowthamProject.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GowthamProject.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement SuggestiveDropdown;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> CountrySugg;
	
	@FindBy(xpath="//div[text()='CVV Code ']/following-sibling :: input[@class='input txt']")
	WebElement CVV;
	
	@FindBy(xpath="//div[text()='Name on Card ']/following-sibling :: input[@class='input txt']")
	WebElement FullName;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement PlaceOrder;
	
	
	
	
	
	public SummaryPage placeOrder() {
		
		SuggestiveDropdown.sendKeys("ind");
		WebElement DesiredCountry = getDesiredCountry(CountrySugg);
		DesiredCountry.click();
		CVV.sendKeys("1234");
		FullName.sendKeys("GowthamMurugesan");
		PlaceOrder.click();
		SummaryPage SummaryPage = new SummaryPage(driver);
		return SummaryPage;
	}
	
	
	
	public WebElement getDesiredCountry(List<WebElement> CountrySugg) {
		WebElement DesiredCountry = CountrySugg.stream().filter(Country->Country.getText().equals("India")).findFirst().orElse(null);
		return DesiredCountry;
		
	}
	
	
}
