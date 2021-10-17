package com.SwagLabs.Utilities;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ThreadLocalInstance 
{

	 private static ThreadLocal<AppiumDriver<MobileElement>> tlDriver = new ThreadLocal<>();
	 private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 private static  ThreadLocal<HashMap<String, String>> productDetails = new ThreadLocal<>();

	    public synchronized  void setTLDriver(AppiumDriver<MobileElement> driver) 
	    { 
	    	tlDriver.set(driver); 
	    }

	    public synchronized  AppiumDriver<MobileElement> getTLDriver() 
	    {
	        return tlDriver.get();
	    }
	    
	    public synchronized  void unloadTLDriver() 
	    {
	        tlDriver.remove();
	    }
	    public synchronized  void setextentTest(ExtentTest test) 
	    { 
	    	extentTest.set(test); 
	    }

	    public synchronized  ExtentTest getextentTest() 
	    {
	        return extentTest.get();
	    }

	    public  void setproductDetails(HashMap<String, String> product) 
	    { 
	    	productDetails.set(product);
	    }

	    public  HashMap<String, String> getproductDetails() 
	    {
	        return productDetails.get();
	    }
	    
	    public  void unloadProductDetails() 
	    { 
	    		productDetails.remove();
	    }

}
