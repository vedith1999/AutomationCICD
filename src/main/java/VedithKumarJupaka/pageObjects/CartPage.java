package VedithKumarJupaka.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VedithKumarJupaka.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow .btn.btn-primary")
	WebElement checkOut;
	

	public Boolean verifyProductDisplay(String productItem) {

		return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productItem));
	}
	
	public CheckOutPage goToCheckout() {
		checkOut.click();
		return new CheckOutPage(driver);
	}
	

	

}
