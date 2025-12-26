package VedithKumarJupaka.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VedithKumarJupaka.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {

	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinnerElement;
	

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");
	

	public List<WebElement> getProductList() {

		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productItem) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productItem))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productItem) throws InterruptedException {
		
		WebElement prod = getProductByName(productItem);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(spinnerElement);
		CartPage cartPage = new CartPage(driver);
		
		
		
	
		
	}
	

}
