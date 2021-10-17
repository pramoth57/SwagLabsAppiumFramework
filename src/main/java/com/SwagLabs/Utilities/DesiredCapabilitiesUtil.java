package com.SwagLabs.Utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DesiredCapabilitiesUtil 
{
	AppiumDriver<MobileElement> driver;

	public DesiredCapabilities getDesiredCapabilities(String udid) {
		/* DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	        desiredCapabilities.setCapability("udid", udid);
	        //desiredCapabilities.setCapability("platformVersion", platformVersion);
	        desiredCapabilities.setCapability("platformName", "Android");
	        desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"\\resources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");		
	        desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp");
	        desiredCapabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
	        desiredCapabilities.setCapability("skipUnlock", "true");
	        desiredCapabilities.setCapability("noReset", "false");*/

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.UDID, udid);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
		capabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"\\resources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);



		return capabilities;
	}

	public AppiumDriver<MobileElement> intiDriver(String udid) throws MalformedURLException, InterruptedException {
			DesiredCapabilities capabilities = new DesiredCapabilities();		
			capabilities.setCapability(MobileCapabilityType.UDID, udid);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
			capabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"\\resources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");		
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			Thread.sleep(3000);
			return driver;
		
		
		
	}
}
