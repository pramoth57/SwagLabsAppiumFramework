package com.SwagLabs.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutComplete extends Base{

	AndroidDriver<AndroidElement> driver;
	public SwagLabs_CheckoutComplete(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	@AndroidFindBy(xpath = "//*[@text='BACK HOME']")
	private WebElement backHome_btn;
	
	

	public void checkoutComplete() throws IOException
	{		
		
		//backHome_btn.click();
		elementClick(backHome_btn, "Back Home");
		
		

	}


}
