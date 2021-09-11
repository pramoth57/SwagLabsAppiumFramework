package com.SwagLabs.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_ProductPage {

	AndroidDriver<AndroidElement> driver;
	public SwagLabs_ProductPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}


	@AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
	private WebElement addToCArt;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
	private WebElement viewCart;

	public void addMultipleProduct() throws InterruptedException
	{		
		addToCArt.click();
		Thread.sleep(1000);
		addToCArt.click();
		viewCart.click();


	}
	
	


	public void addSingleProduct()
	{		
		addToCArt.click();
		
		viewCart.click();


	}


}
