package com.SwagLabs.Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutOverView extends Base{

	AndroidDriver<MobileElement> driver;
	public SwagLabs_CheckoutOverView(AndroidDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	@AndroidFindBy(xpath = "//*[@text='FINISH']")
	private WebElement finish;
	
	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	private WebElement cancel;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView")
	private WebElement productPrice;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Item total:']")
	private WebElement itemTotal;
	
	

	public void checkoutoverview_finish1()
	{		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"FINISH\"));");
		finish.click();
	}

	public void checkoutoverview_cancel() throws IOException
	{		
		
		checkoutOverView_ProductValidation();
		elementScrollBy_DescriptionAndText("test-CHECKOUT: OVERVIEW", "CANCEL");
		elementClick(cancel, "Cancel");
	
	}

	
	public void checkoutoverview_finish() throws InterruptedException, IOException
	{	
		checkoutOverView_ProductValidation();	
		elementScrollByText("FINISH");
		elementClick(finish, "Finish");
		
	}
	

}
