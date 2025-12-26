package VedithKumarJupaka.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import VedithKumarJupaka.TestComponents.BaseTest;
import VedithKumarJupaka.pageObjects.CartPage;
import VedithKumarJupaka.pageObjects.CheckOutPage;
import VedithKumarJupaka.pageObjects.ConfirmationPage;
import VedithKumarJupaka.pageObjects.OrderPage;
import VedithKumarJupaka.pageObjects.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {

	String productItem = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addToCart(input.get("product"));
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalouge productCatalouge = landingPage.loginApplication("jupaka.40@gmail.com", "Qwerty@123456");
		OrderPage orderPage = productCatalouge.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderHistory(productItem));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//VedithKumarJupaka//data//PurchareOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
