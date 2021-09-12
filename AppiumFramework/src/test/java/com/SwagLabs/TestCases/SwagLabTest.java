package com.SwagLabs.TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.SwagLabs.Pages.SwagLabs_CheckoutComplete;
import com.SwagLabs.Pages.SwagLabs_CheckoutInformation;
import com.SwagLabs.Pages.SwagLabs_CheckoutOverView;
import com.SwagLabs.Pages.SwagLabs_LoginPage;
import com.SwagLabs.Pages.SwagLabs_ProductPage;
import com.SwagLabs.Pages.SwagLabs_YourCartPage;
import com.SwagLabs.Utilities.PageObjectManager;
import com.SwagLabs.Utilities.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SwagLabTest extends base{
	AndroidDriver<AndroidElement> driver;

	SwagLabs_CheckoutComplete checkOutComplete;

	SwagLabs_CheckoutInformation checkoutInfo;

	SwagLabs_CheckoutOverView checkoutOverview;

	SwagLabs_LoginPage loginPage;

	SwagLabs_ProductPage productPage;
	
	SwagLabs_YourCartPage yourcart;
	
	PageObjectManager pageObjectManager;
	
	Logger logger = LogManager.getLogger(SwagLabTest.class);

	
	@BeforeTest
	public void preCondition() throws IOException, InterruptedException
	{
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		logger.info("Kill Existing Appium Instance");
		Thread.sleep(3000);
		logger.info("********Test Suit Started**********");
		
		
	}
	
	@AfterTest
	public void postCondition() throws IOException, InterruptedException
	{
		
		logger.info("********Test Suit Ended**********");
	}
	
	@BeforeMethod
	public void preTestCondition() throws IOException, InterruptedException
	{
		
		
		
		
		logger.info("********Test Case Execution Started**********");
		
		
	}
	
	@AfterMethod
	public void postTestCondition() throws IOException, InterruptedException
	{
		
		
		logger.info("********Test Case Execution Ended**********");
		
		
	}
	
	@Test (enabled = true)
	public void SwagLabs_SanityScenario() throws IOException, InterruptedException 
	{
		
		appium_Service = initiate_AppiumService();
		
		driver=capabilities("swagLagsapp");		
		logger.info("App Started");				
		pageObjectManager = new PageObjectManager(driver);
		
		pageObjectManager.get_SwagLabs_LoginPage_Page().Login();	
		logger.info("Login Successfull");
		pageObjectManager.get_SwagLabs_ProductPage_Page().addSingleProduct();		
		logger.info("Product Added to Cart");
		pageObjectManager.get_SwagLabs_YourCartPage_Page().Checkout();		
		logger.info("Product Checked out of Cart");
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().Checkout();
		logger.info("Customer Details Added Successfully");		
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().Checkoutoverview_finish();	
		logger.info("Order Confirmed");		
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().CheckoutComplete();
		
		appium_Service.stop();
		



	}
	
	@Test (enabled = false)
	public void SwagLabs_MultipleProductCheckout() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		
		
		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();
		

		

		

		
		loginPage.Login();
		
		productPage.addMultipleProduct();	
		logger.info("Multiple Product Added to Cart");
		yourcart.Checkout();
		logger.info("Product Checked out of Cart");
		checkoutInfo.Checkout();	
		logger.info("Customer Details Added Successfully");		
		checkoutOverview.Checkoutoverview_finish();		
		logger.info("Order Confirmed");	
		checkOutComplete.CheckoutComplete();
		appium_Service.stop();
		



	}
	
	@Test (enabled = false)
	public void SwagLabs_MultipleProductAdd_RemoveAll_Checkout() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		
		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();
		
		

		

		
		loginPage.Login();	
		logger.info("Login Successfull");
		productPage.addMultipleProduct();	
		logger.info("Multiple Product Added to Cart");
		yourcart.removeAll_Checkout();	
		logger.info("All Product removed Checked out of Cart");
		checkoutInfo.Checkout();
		logger.info("Customer Details Added Successfully");		
		checkoutOverview.Checkoutoverview_finish();	
		logger.info("Order Confirmed");	
		checkOutComplete.CheckoutComplete();
		appium_Service.stop();
		



	}
	
	@Test (enabled = false)
	public void SwagLabs_AddProduct_Remove_Checkout() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		
		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();
		
		
		
		loginPage.Login();		
		logger.info("Login Successfull");
		productPage.addSingleProduct();	
		logger.info("Product Added to Cart");
		yourcart.remove_Checkout();		
		logger.info("Product removed Checked out of Cart");
		checkoutInfo.Checkout();		
		logger.info("Customer Details Added Successfully");		
		checkoutOverview.Checkoutoverview_finish();		
		logger.info("Order Confirmed");	
		checkOutComplete.CheckoutComplete();
		appium_Service.stop();
		



	}
	
	@Test (enabled = false)
	public void SwagLabs_AddProduct_ContinueShopping_Checkout() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		

		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();

		

		
		loginPage.Login();		
		logger.info("Login Successfull");
		productPage.addSingleProduct();	
		logger.info("Product Added to Cart");
		yourcart.Click_Continue();	
		logger.info("Continue for Shooping is Clicked on Checkout Page");
		productPage.addSingleProduct();	
		logger.info("Product Added to Cart");
		yourcart.Checkout();
		logger.info("Product Checked out of Cart");
		checkoutInfo.Checkout();
		logger.info("Customer Details Added Successfully");		
		checkoutOverview.Checkoutoverview_finish();	
		logger.info("Order Confirmed");	
		checkOutComplete.CheckoutComplete();
		appium_Service.stop();		



	}
	
	
	@Test (enabled = false)
	public void SwagLabs_AddProduct_Checkout_Cancel() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		

		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();

	
		loginPage.Login();
		logger.info("Login Successfull");
		productPage.addSingleProduct();	
		logger.info("Product Added to Cart");
		yourcart.Checkout();		
		logger.info("Product Checked out of Cart");
		checkoutInfo.cancel();	
		logger.info("Order Cancelled on Checkout Information Screen");		
				
		appium_Service.stop();		

	}
	
	@Test (enabled = false)
	public void SwagLabs_AddProduct_Checkout_FinalCancel() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		

		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();
		
		

		
		loginPage.Login();		
		logger.info("Login Successfull");
		productPage.addSingleProduct();	
		logger.info("Product Added to Cart");
		yourcart.Checkout();	
		logger.info("Product Checked out of Cart");
		checkoutInfo.Checkout();	
		logger.info("Customer Details Added Successfully");		
		checkoutOverview.Checkoutoverview_cancel();	
		logger.info("Order Cancelled");	
			
		
		appium_Service.stop();		



	}
	
	@Test (enabled = false)
	public void SwagLabs_AddProduct_Checkout_AmountValidation() throws IOException, InterruptedException 
	{
		appium_Service = initiate_AppiumService();
		

		driver=capabilities("swagLagsapp");		
		logger.info("App Started");		
		
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.get_SwagLabs_LoginPage_Page();
		productPage = pageObjectManager.get_SwagLabs_ProductPage_Page();
		yourcart = pageObjectManager.get_SwagLabs_YourCartPage_Page();
		checkoutInfo = pageObjectManager.get_SwagLabs_CheckoutInformation_Page();
		checkoutOverview = pageObjectManager.get_SwagLabs_CheckoutOverView_Page();
		checkOutComplete = pageObjectManager.getSwagLabs_CheckoutComplete_Page();
		
		

		
		loginPage.Login();		
		logger.info("Login Successfull");
		productPage.addSingleProduct();		
		logger.info("Product Added to Cart");
		yourcart.Checkout();	
		logger.info("Product Checked out of Cart");
		checkoutInfo.Checkout();	
		logger.info("Customer Details Added Successfully");		
		checkoutOverview.Checkoutoverview_amountvalidation();	
		logger.info("Order Confirmed and Both Product Total and Cart Total is Validated");	
		checkOutComplete.CheckoutComplete();
		
		appium_Service.stop();		



	}
}
