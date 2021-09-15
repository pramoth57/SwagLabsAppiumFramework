package com.SwagLabs.Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutOverView extends Base{

	AndroidDriver<AndroidElement> driver;
	public SwagLabs_CheckoutOverView(AndroidDriver<AndroidElement> driver)
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
		//elementScrollByText("CANCEL");
		checkoutOverView_ProductValidation();
		elementScrollBy_DescriptionAndText("test-CHECKOUT: OVERVIEW", "CANCEL");
		elementClick(cancel, "Cancel");
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"CANCEL\"));");
		//cancel.click();
		

	}

	
	public void checkoutoverview_finish() throws InterruptedException, IOException
	{	
		//double productAmt= 0.0;
		
		checkoutOverView_ProductValidation();
		//elementScrollByText("FINISH");
		//test-CHECKOUT: OVERVIEW
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"FINISH\"));");
		elementScrollByText("FINISH");
		elementClick(finish, "Finish");
		//finish.click();
		

	}
	

}
