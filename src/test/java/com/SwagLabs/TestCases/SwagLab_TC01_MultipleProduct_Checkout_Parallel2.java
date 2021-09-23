package com.SwagLabs.TestCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SwagLabs.Utilities.Base;
import com.SwagLabs.Utilities.DesiredCapabilitiesUtil;
import com.SwagLabs.Utilities.FileReader;
import com.SwagLabs.Utilities.PageObjects;
import com.SwagLabs.Utilities.ThreadLocalInstance;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SwagLab_TC01_MultipleProduct_Checkout_Parallel2 extends Base{
	
	public AppiumDriver<MobileElement> driver;	
	PageObjects pageObjectManager;	
   // private final ThreadLocalInstance threadLocalDriver = new ThreadLocalInstance();
    ThreadLocalInstance threadLocalInstance = new ThreadLocalInstance();
    private final DesiredCapabilitiesUtil desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();

	@BeforeMethod
	public void preTestCondition() throws IOException, InterruptedException
	{
		        
		//intitate_extentReport();		
		//Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	

	@Test (enabled = true)
	@Parameters ({"uuid"})
	public void swagLabsTC01(String uuid) throws IOException, InterruptedException 
	{			
		//extentTest = reports.createTest("Add Single Products and Perform Check out");
		threadLocalInstance.setextentTest(reports.createTest("Add Single Products and Perform Check out"));
		//appium_Service = initiate_AppiumService();
		//threadLocalDriver.setuuid(uuid);
		try
		{
			threadLocalInstance.setTLDriver(desiredCapabilitiesUtil.intiDriver(uuid));  
		System.out.println(threadLocalInstance.getTLDriver());		
		/*if(threadLocalDriver.getTLDriver().equals(null))
		{
			threadLocalDriver.setTLDriver(desiredCapabilitiesUtil.intiDriver(uuid));
		}
		
		System.out.println(threadLocalDriver.getTLDriver());	*/	
		pageObjectManager = new PageObjects(threadLocalInstance.getTLDriver());
		pageObjectManager.get_SwagLabs_LoginPage_Page().login_StandardUser();
		pageObjectManager.get_SwagLabs_ProductPage_Page().addProductToCart(FileReader.getInstance().getConfigReader().getProduct_item1());		
		pageObjectManager.get_SwagLabs_YourCartPage_Page().checkout();			
		pageObjectManager.get_SwagLabs_CheckoutInformation_Page().checkout();		
		pageObjectManager.get_SwagLabs_CheckoutOverView_Page().checkoutoverview_finish();		
		pageObjectManager.getSwagLabs_CheckoutComplete_Page().checkoutComplete();
		threadLocalInstance.setTLDriver(null);  
		}
		catch (Exception e) {
			threadLocalInstance.setTLDriver(null);  
		}
		//appium_Service.stop();
		
	}	

	@AfterMethod
	public void postTestCondition()
	{
		//productDetails.clear();
		//driver=null;
		//extentReport_flush();
		//appium_Service.stop();
	}

	
}
