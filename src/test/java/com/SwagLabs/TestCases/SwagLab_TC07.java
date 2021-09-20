package com.SwagLabs.TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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
import com.SwagLabs.Utilities.FileReaderManager;
import com.SwagLabs.Utilities.PageObjectManager;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SwagLab_TC07 extends Base{
	AppiumDriver<MobileElement> driver;
	PageObjectManager pageObjectManager;

	@BeforeMethod
	public void preTestCondition() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
				Thread.sleep(3000);
	}	
	
	@Test (enabled = true)
	public void swagLabsTC07() throws IOException, InterruptedException 
	{
		extentTest = reports.createTest("Add Single Products and  Cancel the Check out on Checkout Overview Page");
		appium_Service = initiate_AppiumService();		
		driver=capabilities("swagLagsapp");		
		pageObjectManager = new PageObjectManager(driver);	
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();			
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart(FileReaderManager.getInstance().getConfigReader().getProduct_item1());			
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_cancel();			
		appium_Service.stop();		

	}

	@AfterMethod
	public void postTestCondition()
	{
		Base.productDetails.clear();
		driver=null;
	}

	
}
