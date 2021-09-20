package com.SwagLabs.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutComplete extends Base{

	AppiumDriver<MobileElement> driver;
	public SwagLabs_CheckoutComplete(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	@AndroidFindBy(xpath = "//*[@text='BACK HOME']")
	private WebElement backHome_btn;
	
	

	public void checkoutComplete() throws IOException
	{	
		elementVisibility("CHECKOUT: COMPLETE!");
		elementClick(backHome_btn, "Back Home");	

	}


}
