package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
//import rahulshettyacademy.pageobjects.LandingPage;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws IOException
	{
		// TODO Auto-generated method stub
		ProductCatalogue productCatalogue=landingPage.loginApplication("bharatsharma@gmail.com", "Admin@123");
		productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);//anyMatch() retuns the boolean value
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		
		/*
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		*/
		

	}
	
	//To verify if "ZARA COAT 3" is displayed in the orders page
			@Test(dependsOnMethods= {"submitTest"})
			public void orderHistoryTest()
			{
				ProductCatalogue productCatalogue=landingPage.loginApplication("bharatsharma@gmail.com", "Admin@123");
				OrderPage orderPage=productCatalogue.goToOrdersPage();
				Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
			}

}
