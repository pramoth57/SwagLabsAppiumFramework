package com.SwagLabs.Pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutOverView {

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
	
	

	public void Checkoutoverview_finish()
	{		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"FINISH\"));");
		finish.click();
		

	}

	public void Checkoutoverview_cancel()
	{		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"CANCEL\"));");
		cancel.click();
		

	}

	
	public void Checkoutoverview_amountvalidation()
	{		
		double productAmt = getAmount(productPrice.getText());
		double producttotal = getAmount(driver.findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"Item total:\");").getText().split(":")[1].trim());
		//String producttotal =driver.findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"Item total:\");").getText();
		//String producttotal = itemTotal.getText();
		//System.out.println(productAmt==producttotal);
		assertEquals(true, productAmt==producttotal);
		
		
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"FINISH\"));");
		finish.click();
		

	}
	public double getAmount(String value)

	{

		value= value.substring(1);

		double amount2value=Double.parseDouble(value);

		return amount2value;

	}

}
