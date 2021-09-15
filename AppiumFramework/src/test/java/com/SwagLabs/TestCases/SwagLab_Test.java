package com.SwagLabs.TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.SwagLabs.Pages.SwagLabs_CheckoutComplete;
import com.SwagLabs.Pages.SwagLabs_CheckoutInformation;
import com.SwagLabs.Pages.SwagLabs_CheckoutOverView;
import com.SwagLabs.Pages.SwagLabs_LoginPage;
import com.SwagLabs.Pages.SwagLabs_ProductPage;
import com.SwagLabs.Pages.SwagLabs_YourCartPage;
import com.SwagLabs.Utilities.Base;
import com.SwagLabs.Utilities.PageObjectManager;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SwagLab_Test extends Base{
	AndroidDriver<AndroidElement> driver;
	PageObjectManager pageObjectManager;

	//Logger logger = LogManager.getLogger(SwagLabTest.class);

	

	@BeforeMethod
	public void preTestCondition() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		//logger.info("Kill Existing Appium Instance");
		Thread.sleep(3000);
	}
	
	
	@Test (enabled = true)
	public void swagLabsTC01() throws IOException, InterruptedException 
	{
		extentTest = reports.createTest("Add Multiple Products and Perform Check out");
		appium_Service = initiate_AppiumService();		
		driver=capabilities("swagLagsapp");						
		pageObjectManager = new PageObjectManager(driver);		
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();			
		//Sauce Labs Bolt T-Shirt,Sauce Labs Backpack,Sauce Labs Onesie
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack,Sauce Labs Bike Light");
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();	
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();		
		appium_Service.stop();
		//extentReport_flush();		
	}

	@Test (enabled = true)
	public void swagLabsTC02() throws IOException, InterruptedException 
	{	
		extentTest = reports.createTest("Add Single Products and Perform Check out");
		appium_Service = initiate_AppiumService();		
		driver=capabilities("swagLagsapp");	
		pageObjectManager = new PageObjectManager(driver);
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack");		
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();		
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();
		appium_Service.stop();
		

	}

	@Test (enabled = true)
	public void swagLabsTC03() throws IOException, InterruptedException 
	{ 
		extentTest = reports.createTest("Add Multiple Products, Remove all Products Before CheckOut and Verify Item total amount");
		appium_Service = initiate_AppiumService();		
		driver=capabilities("swagLagsapp");		
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();		
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack,Sauce Labs Bike Light");			
		pageObjectManager.get_SwagLabs_YourCartPage_Page().removeAll_Checkout();		
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();			
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();
		appium_Service.stop();

	}

	@Test (enabled = true)
	public void swagLabsTC04() throws IOException, InterruptedException 
	{ 
		extentTest = reports.createTest("Add Multiple Products, Remove one Products Before CheckOut and Verify Item total amount");
		appium_Service = initiate_AppiumService();		
		driver=capabilities("swagLagsapp");	
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();		
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack,Sauce Labs Bike Light");		
		pageObjectManager.get_SwagLabs_YourCartPage_Page().remove_Checkout("Sauce Labs Backpack");				
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();			
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();
		appium_Service.stop();
	}

	@Test (enabled = true)
	public void swagLabsTC05() throws IOException, InterruptedException 
	{
		extentTest = reports.createTest("Add Single Products, Click Continue Shopping in YourCart Page and Add Another Product, Perform Checkout");
		appium_Service = initiate_AppiumService();
		driver=capabilities("swagLagsapp");	
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();			
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack");		
		pageObjectManager.get_SwagLabs_YourCartPage_Page().click_Continue();			
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Bike Light");			
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();			
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();
		appium_Service.stop();	

	}


	@Test (enabled = true)
	public void swagLabsTC06() throws IOException, InterruptedException 
	{
		extentTest = reports.createTest("Add Single Products and  Cancel the Check out on Checkout Information Page");
		appium_Service = initiate_AppiumService();
		driver=capabilities("swagLagsapp");	
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();		
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack");		
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();				
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().cancel();					
		appium_Service.stop();		
	}

	@Test (enabled = true)
	public void swagLabsTC07() throws IOException, InterruptedException 
	{
		extentTest = reports.createTest("Add Single Products and  Cancel the Check out on Checkout Overview Page");
		appium_Service = initiate_AppiumService();		
		driver=capabilities("swagLagsapp");		
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();			
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack");			
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_cancel();			
		appium_Service.stop();		

	}

	@Test (enabled = true)
	public void swagLabsTC08() throws IOException, InterruptedException 
	{
		extentTest = reports.createTest("Verify Check out Operation With Problem User");
		appium_Service = initiate_AppiumService();
		driver=capabilities("swagLagsapp");	
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_problemUser();				
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart("Sauce Labs Backpack,Sauce Labs Bike Light");		
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();	
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();		
		appium_Service.stop();	
	}

	@AfterMethod
	public void postTestCondition()
	{
		Base.productDetails.clear();
		driver=null;
	}

	
}
