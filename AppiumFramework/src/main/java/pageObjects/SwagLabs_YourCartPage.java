package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_YourCartPage {

	AndroidDriver<AndroidElement> driver;
	public SwagLabs_YourCartPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	
	@AndroidFindBy(xpath = "//*[@text='CHECKOUT']")
	private WebElement checkout;
	
	@AndroidFindBy(xpath = "//*[@text='CONTINUE SHOPPING']")
	private WebElement continueShooping_btn;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
	private List<WebElement> remove;
	



	public void Checkout()
	{		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"CHECKOUT\"));");
		checkout.click();
		//viewCart.click();


	}
	
	public void Click_Continue()
	{		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"CONTINUE SHOPPING\"));");
		continueShooping_btn.click();
		//viewCart.click();


	}
	
	public void removeAll_Checkout() throws InterruptedException
	{		
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(1)"));
		
		Thread.sleep(2000);
		remove.get(0).click();
		Thread.sleep(2000);
		remove.get(0).click();
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"CHECKOUT\"));");
		checkout.click();
		//viewCart.click();


	}
	
	public void remove_Checkout() throws InterruptedException
	{		
		
		
		Thread.sleep(2000);
		remove.get(0).click();
		
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"CHECKOUT\"));");
		checkout.click();
		//viewCart.click();


	}



}
