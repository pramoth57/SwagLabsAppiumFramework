package com.SwagLabs.Utilities;

import org.openqa.selenium.WebDriver;

import com.SwagLabs.Pages.SwagLabs_CheckoutComplete;
import com.SwagLabs.Pages.SwagLabs_CheckoutInformation;
import com.SwagLabs.Pages.SwagLabs_CheckoutOverView;
import com.SwagLabs.Pages.SwagLabs_LoginPage;
import com.SwagLabs.Pages.SwagLabs_ProductPage;
import com.SwagLabs.Pages.SwagLabs_YourCartPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageObjectManager 
{

	private AndroidDriver<MobileElement> driver;

	private SwagLabs_CheckoutComplete checkoutComplete;

	private SwagLabs_CheckoutInformation checkoutInformation;

	private SwagLabs_CheckoutOverView checkoutOverView;

	private SwagLabs_LoginPage loginPage;

	private SwagLabs_ProductPage productPage;
	
	private SwagLabs_YourCartPage yourCartPage;

	//Manage Page Object as Singleton Pattern
	public PageObjectManager(AndroidDriver<MobileElement> driver)
	{

		this.driver = driver;

	}

	public SwagLabs_LoginPage get_SwagLabs_LoginPage_Page() 
	{

		return (loginPage == null) ? loginPage = new SwagLabs_LoginPage(driver) : loginPage;

	}
	
	public SwagLabs_YourCartPage get_SwagLabs_YourCartPage_Page() 
	{

		return (yourCartPage == null) ? yourCartPage = new SwagLabs_YourCartPage(driver) : yourCartPage;

	}
	public SwagLabs_ProductPage get_SwagLabs_ProductPage_Page() 
	{

		return (productPage == null) ? productPage = new SwagLabs_ProductPage(driver) : productPage;

	}
	public SwagLabs_CheckoutComplete getSwagLabs_CheckoutComplete_Page() 
	{

		return (checkoutComplete == null) ? checkoutComplete = new SwagLabs_CheckoutComplete(driver) : checkoutComplete;

	}
	
	public SwagLabs_CheckoutInformation get_SwagLabs_CheckoutInformation_Page() 
	{

		return (checkoutInformation == null) ? checkoutInformation = new SwagLabs_CheckoutInformation(driver) : checkoutInformation;

	}
	
	
	public SwagLabs_CheckoutOverView get_SwagLabs_CheckoutOverView_Page() 
	{

		return (checkoutOverView == null) ? checkoutOverView = new SwagLabs_CheckoutOverView(driver) : checkoutOverView;

	}
	
	
}
