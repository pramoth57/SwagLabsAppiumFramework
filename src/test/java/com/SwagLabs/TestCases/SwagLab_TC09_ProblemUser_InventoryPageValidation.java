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

public class SwagLab_TC09_ProblemUser_InventoryPageValidation extends Base{
	
	PageObjects pageObjectManager;
	ThreadLocalInstance threadLocalInstance = new ThreadLocalInstance();
    

	@Test (enabled = true)
	public void swagLabsTC10() throws IOException, InterruptedException 
	{
		
		threadLocalInstance.setextentTest(reports.createTest("Validate Product Details in Inventory and Your Cart Page for Problem User"));		
		pageObjectManager = new PageObjects(threadLocalInstance.getTLDriver());
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_problemUser();		
		pageObjectManager.get_SwagLabs_ProductPage_Page().validateProductInformation(FileReader.getInstance().getConfigReader().getProduct_Listitem1());	
		pageObjectManager.get_SwagLabs_LoginPage_Page().logout();
		
	}
	
	
	

	
}
