package VedithKumarJupaka.tests;

import org.testng.annotations.Test;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import VedithKumarJupaka.TestComponents.BaseTest;
import VedithKumarJupaka.TestComponents.Retry;
import VedithKumarJupaka.pageObjects.CartPage;
import VedithKumarJupaka.pageObjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		//String productItem = "ZARA COAT 3";

		landingPage.loginApplication("jupaka.40@gmail.com", "Qwerty@12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		

	}
	
	@Test
	public void productErrorValidation() throws InterruptedException {
		String productItem = "ZARA COAT 3";

		ProductCatalouge productCatalouge = landingPage.loginApplication("jupaka.40@gmail.com", "Qwerty@123456");

		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addToCart(productItem);
		CartPage cartPage = productCatalouge.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
