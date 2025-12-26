package VedithKumarJupaka.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import VedithKumarJupaka.TestComponents.BaseTest;
import VedithKumarJupaka.pageObjects.CartPage;
import VedithKumarJupaka.pageObjects.CheckOutPage;
import VedithKumarJupaka.pageObjects.ConfirmationPage;
import VedithKumarJupaka.pageObjects.LandingPage;
import VedithKumarJupaka.pageObjects.ProductCatalouge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalouge = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to the Cart$")
	public void i_add_product_to_the_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addToCart(productName);

	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed on the confirmation page")
	public void message_displayed_confirmation_page(String string) {
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();

	}

	@Then("{string} message is displayed")
	public void error_message_is_displayed(String expectedMessage) {
		String actualMessage = landingPage.getErrorMessage();
		Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());
		driver.close();

	}

}
