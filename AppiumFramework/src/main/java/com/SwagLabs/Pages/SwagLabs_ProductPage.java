package com.SwagLabs.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_ProductPage extends Base{

	AndroidDriver<AndroidElement> driver;
	public SwagLabs_ProductPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}


	@AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
	private WebElement addToCArt;


	@AndroidFindBy(xpath = "//*[@text()='Sauce Labs Onesie']")
	private WebElement addToCArt1;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
	private WebElement viewCart;

	public void addMultipleProduct() throws InterruptedException
	{		
		addToCArt.click();
		Thread.sleep(1000);
		addToCArt.click();
		viewCart.click();


	}




	public void addProductToCart(String productList) throws InterruptedException, IOException
	{		
		
		addProduct(productList);
		elementClick(viewCart, " View Cart");

		//viewCart.click();


	}
	
	public void validateProductInformation(String productList) throws InterruptedException, IOException
	{		
		
		validateProductInfo(productList);
		//elementClick(viewCart, " View Cart");

		//viewCart.click();


	}
	
	public void validateProductInformation_addtoCart(String productList) throws InterruptedException, IOException
	{		
		
		validateProductInfo_addCart(productList);
		elementClick(viewCart, " View Cart");

		//viewCart.click();


	}


}
