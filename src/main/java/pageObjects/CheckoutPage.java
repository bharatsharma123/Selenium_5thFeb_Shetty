package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	public WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}

	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css="a[class*='action__submit']")
	WebElement submit;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		selectCountry.click();
		//Below code written as there was exception element is not clickable at point(1085,615)
		//WebElement element = driver.findElement(By.cssSelector("a[class*='action__submit']"));
		
		//driver.findElement(By.cssSelector(".action__submit")).click();
	}
	public ConfirmationPage submitOrder()
	{
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("window.scrollBy(0,500)", "");
		//waitForWebElementToAppear(submit);
		// wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()",submit);
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
		
		//executor.executeScript("arguments[0].click()",submit);
		
		//ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		//return confirmationPage;
		
	}
	
}
