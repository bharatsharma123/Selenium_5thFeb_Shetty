package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
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
	
	//String productNamee = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		// TODO Auto-generated method stub
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductByName(input.get("productName"));
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
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
			/*@Test(dependsOnMethods= {"submitTest"})
			public void orderHistoryTest()
			{
				ProductCatalogue productCatalogue=landingPage.loginApplication("bharatsharma@gmail.com", "Admin@123");
				OrderPage orderPage=productCatalogue.goToOrdersPage();
				Assert.assertTrue(orderPage.verifyOrderDisplay(productNamee));
			}
			*/
			
			
			/*@DataProvider
			public Object[][] getData()
			{
				return new Object[][] {{"bharatsharma@gmail.com", "Admin@123","ZARA COAT 3"},{"anshika@gmail.com", "Iamking@000","ADIDAS ORIGINAL"}};
			}*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		HashMap<String,String>map=new HashMap<String,String>();
		map.put("email", "bharatsharma@gmail.com");
		map.put("password", "Admin@123");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String>map1=new HashMap<String,String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};
	
	
	}

}
