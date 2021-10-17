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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SwagLabs.Pages.SwagLabs_CheckoutComplete;
import com.SwagLabs.Pages.SwagLabs_CheckoutInformation;
import com.SwagLabs.Pages.SwagLabs_CheckoutOverView;
import com.SwagLabs.Pages.SwagLabs_LoginPage;
import com.SwagLabs.Pages.SwagLabs_ProductPage;
import com.SwagLabs.Pages.SwagLabs_YourCartPage;
import com.SwagLabs.Utilities.Base;
import com.SwagLabs.Utilities.DesiredCapabilitiesUtil;
import com.SwagLabs.Utilities.FileReader;
import com.SwagLabs.Utilities.PageObjects;
import com.SwagLabs.Utilities.ThreadLocalInstance;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SwagLab_TC03_MultipleProduct_RemoveAll extends Base{

	PageObjects pageObjectManager;
	ThreadLocalInstance threadLocalInstance = new ThreadLocalInstance();
	

	@Test (enabled = true)
	public void swagLabsTC03() throws IOException, InterruptedException 
	{ 
		threadLocalInstance.setextentTest(reports.createTest("Add Multiple Products, Remove all Products Before CheckOut and Verify Item total amount"));
		pageObjectManager = new PageObjects(threadLocalInstance.getTLDriver());
		pageObjectManager = new PageObjects(threadLocalInstance.getTLDriver());
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();		
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart(FileReader.getInstance().getConfigReader().getProduct_Listitem1());			
		pageObjectManager.get_SwagLabs_YourCartPage_Page().removeAll_Checkout();		
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();			
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();
		pageObjectManager.get_SwagLabs_LoginPage_Page().logout();
		

	}

	

	
}
