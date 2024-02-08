package testcases;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("bharatsharma@gmail.com", "Admin@1123");
		String message=landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.",message);
	}
	
	
	@Test(groups= {"ErrorHandling"})
	public void productValidation()
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("bharatsharma@gmail.com", "Admin@123");
		//List<WebElement>products=productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);//anyMatch() retuns the boolean value
	}
	

}
