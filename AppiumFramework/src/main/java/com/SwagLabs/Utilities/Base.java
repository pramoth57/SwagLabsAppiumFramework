package com.SwagLabs.Utilities;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base
{
	public static AppiumDriverLocalService appium_Service;
	public static AndroidDriver<AndroidElement>  driver;
	public static ExtentTest extentTest;
	public static HashMap<String, String> productDetails = new HashMap<String, String>();
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports reports;

	
	
	//Initiate Extent Report instance
	public static void intitate_extentReport()
	{
		htmlreporter = new ExtentHtmlReporter("C:\\Users\\USER\\git\\SwagLabsAppiumFramework\\SwagLabsAppiumFramework\\AppiumFramework\\Reports\\SwagLabsTestReport.html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("SwagLabs Automation Reports");
		htmlreporter.config().setReportName("Swaglabs Automtion Execution reports");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setAutoCreateRelativePathMedia(true);
		
		reports = new ExtentReports();
		reports.setSystemInfo("Device Type", "Android");
		reports.setSystemInfo("Device", "Android Device");
		reports.attachReporter(htmlreporter);


	}

	//Write Extent Report Logs to External File
	public static void extentReport_flush()
	{


		reports.flush();
	}


	// Initiate Appium Server
	public AppiumDriverLocalService initiate_AppiumService()
	{
		try
		{
			boolean flag= checkIfServerIsRunnning(4723);
			if(!flag)
			{		
				appium_Service=AppiumDriverLocalService.buildDefaultService();
				appium_Service.start();
			}

			extentTest.log(Status.PASS, "Appium Server Started Successfully");
		}
		catch (Exception e) {
			// TODO: handle exception
			extentTest.log(Status.FAIL, "Appium Server Not Started Successfully");
		}

		return appium_Service;


	}

	// Check if Appium Server is in Active State or Not
	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}


	//Initiate the Emulator
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");

		Thread.sleep(6000);
	}

	//Initiate Android Driver with corresponding Capabilities
	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{

		String driverDevice = "Android";
		//String driverDevice = "emulator";
		String deviceType = null;
		String device = null;
		File appDir = new File("src");
		File app = new File(appDir, FileReaderManager.getInstance().getConfigReader().getswagLagsappApp());

		DesiredCapabilities capabilities = new DesiredCapabilities();

		if(driverDevice.equals("Android"))
		{
			deviceType= FileReaderManager.getInstance().getConfigReader().getdeviceType();
			device=(String) FileReaderManager.getInstance().getConfigReader().getdevice();
		}
		else
		{
			deviceType= FileReaderManager.getInstance().getConfigReader().getemulatorType();
			device=(String) FileReaderManager.getInstance().getConfigReader().get_EmulatorDevice();
		}

		if(deviceType.contains("emulator"))
		{
			try 
			{
				startEmulator();
				extentTest.log(Status.PASS, "Emulator Started Successfully");
			}
			catch (Exception e) 
			{
				extentTest.log(Status.FAIL, "Emulator not Started Successfully");
				// TODO: handle exception
			}
		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
		capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);	

		try 
		{
			driver = new  AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			extentTest.log(Status.PASS, "Android Driver Started Successfully");

		}
		catch (Exception e) 
		{
			// TODO: handle exception
			extentTest.log(Status.FAIL, "Android Drive not Started Successfully");
			
		}

		return driver;
	}

	//Capture Screen Shot
	public static String getScreenshot() throws IOException
	{
		File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\ScreenShot\\"+System.currentTimeMillis()+".png";
		FileUtils.copyFile(scrfile,new File(path));
		return path;

	}
	//Scroll Element into View using Visible Text
	public static void elementScrollByText(String oText) throws IOException
	{
		try
		{
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+oText+"\"));");
			extentTest.log(Status.PASS, oText+" Text Scrolled Into View Successfully");
		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, oText+" Text Scrolled Not Into View Successfully",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	// Verify Element displayed in View
	public static boolean elementDispayed(String oText)
	{
		try
		{
			driver.findElement(By.xpath("//*[@text='"+oText+"']")).isDisplayed();
			extentTest.log(Status.PASS, oText+" is Displayed In View");
			return true;
		}
		catch (Exception e) 
		{
			//extentTest.log(Status.FAIL, oText+" is not Displayed In View");
			return false;
		}

	}

	//Scroll Element into View using Description and Visible Text
	public static void elementScrollBy_DescriptionAndText(String oDescription, String oText) throws IOException
	{
		try
		{
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\""+oDescription+"\")).scrollIntoView(text(\""+oText+"\"));");
			extentTest.log(Status.PASS, oText+" Text Scrolled Into View Successfully");
		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, oText+" Text Scrolled Not Into View Successfully",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Perform Click Action on WebElement
	public static void elementClick(WebElement oElement, String oElementName) throws IOException
	{
		try
		{

			oElement.click();
			extentTest.log(Status.PASS, oElementName+" Clicked Successfully");
		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, oElementName+" Click Unsuccessfully",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Perform Send Keys Action on Webelement
	public static void elementSendKeys(WebElement oElement, String oElementName, String oData) throws IOException
	{
		try
		{

			oElement.sendKeys(oData);;
			extentTest.log(Status.PASS, "Data *"+oData+"* Entered Successfully for "+oElementName+" Field");
		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, "Data *"+oData+"* Not Entered Successfully for "+oElementName+" Field",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Add Product in Product Home Page Using Product Name
	public static void addProduct(String oProductList) throws IOException
	{
		String productAmount = null;
		String productname = null;
		
		try
		{

			for(String product: oProductList.split(","))
			{	
				System.out.println(product);
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-PRODUCTS\")).scrollIntoView(text(\""+product+"\"));");
				extentTest.log(Status.PASS, product+" Scrolled into View");
				productname = product;
				productAmount = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@content-desc='test-Price']")).getAttribute("text");
				Base.productDetails.put(product, productAmount);		
				extentTest.log(Status.PASS, product+" Amount "+productAmount+" retrived Successfully");
				driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@text='ADD TO CART']")).click();
				//assertTrue(driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@text='REMOVE']")).isDisplayed());
				extentTest.log(Status.PASS, product+" Added to Cart Successfully");

			}
		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//assertTrue(false);
		}
	}
	
	//Validate Product informaion in Your and Invertory Page
	public static void validateProductInfo(String oProductList) throws IOException
	{
		String productAmount = null;
		String productname = null;
		
		String inventoryproductAmount = null;
		String inventoryproductname = null;
		
		try
		{

			for(String product: oProductList.split(","))
			{	
				System.out.println(product);
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-PRODUCTS\")).scrollIntoView(text(\""+product+"\"));");
				//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+product+"\"));");
				extentTest.log(Status.PASS, product+" Scrolled into View");
				productname = product;
				productAmount = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@content-desc='test-Price']")).getAttribute("text");
				extentTest.log(Status.PASS, product+" Amount "+productAmount+" retrived Successfully");
				Base.productDetails.put(product, productAmount);							
				driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']")).click();
				//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-Inventory item page\")).scrollIntoView(text(\""+product+"\"));");
				inventoryproductname=driver.findElement(MobileBy.xpath("//*[@content-desc='test-Description']/android.widget.TextView")).getAttribute("text");
				if(productname.equals(inventoryproductname))
				{
					extentTest.log(Status.PASS, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page ");
					
				}
				else 
				{
					extentTest.log(Status.FAIL, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page ");
					//extentTest.log(Status.FAIL, "Added Product "+product+" is not available in YourCart",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				}
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-Inventory item page\")).scrollIntoView(text(\"ADD TO CART\"));");
				//extentTest.log(Status.PASS, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());				
				inventoryproductAmount=driver.findElement(MobileBy.xpath("//*[@content-desc='test-Price']")).getAttribute("text");
				
				
				
				if(productAmount.equals(inventoryproductAmount))
				{
					extentTest.log(Status.PASS, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page ");
					
				}
				else 
				{
					extentTest.log(Status.FAIL, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page ");
				}
				//extentTest.log(Status.PASS, product+" Added to Cart Successfully");
				driver.findElement(MobileBy.xpath("//*[@text='BACK TO PRODUCTS']")).click();
				

			}
		}
		catch (Exception e) 
		{
			//extentTest.log(Status.FAIL, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//assertTrue(false);
		}
	}

	//Validate Product informaion in Your and Invertory Page and Add the Product To Cart from Inventory Page 
	public static void validateProductInfo_addCart(String oProductList) throws IOException
	{
		String productAmount = null;
		String productname = null;
		
		String inventoryproductAmount = null;
		String inventoryproductname = null;
		
		try
		{

			for(String product: oProductList.split(","))
			{	
				System.out.println(product);
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-PRODUCTS\")).scrollIntoView(text(\""+product+"\"));");
				//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+product+"\"));");
				extentTest.log(Status.PASS, product+" Scrolled into View");
				productname = product;
				productAmount = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@content-desc='test-Price']")).getAttribute("text");
				extentTest.log(Status.PASS, product+" Amount "+productAmount+" retrived Successfully");
				Base.productDetails.put(product, productAmount);							
				driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']")).click();
				//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-Inventory item page\")).scrollIntoView(text(\""+product+"\"));");
				inventoryproductname=driver.findElement(MobileBy.xpath("//*[@content-desc='test-Description']/android.widget.TextView")).getAttribute("text");
				if(productname.equals(inventoryproductname))
				{
					extentTest.log(Status.PASS, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page ");
					
				}
				else 
				{
					extentTest.log(Status.FAIL, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page ");
					//extentTest.log(Status.FAIL, "Added Product "+product+" is not available in YourCart",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				}
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-Inventory item page\")).scrollIntoView(text(\"ADD TO CART\"));");
				//extentTest.log(Status.PASS, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());				
				inventoryproductAmount=driver.findElement(MobileBy.xpath("//*[@content-desc='test-Price']")).getAttribute("text");
				
				
				
				if(productAmount.equals(inventoryproductAmount))
				{
					extentTest.log(Status.PASS, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page ");
					
				}
				else 
				{
					extentTest.log(Status.FAIL, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page ");
				}
				driver.findElement(By.xpath("//*[@text='ADD TO CART']")).click();
				//extentTest.log(Status.PASS, product+" Added to Cart Successfully");
				driver.findElement(MobileBy.xpath("//*[@text='BACK TO PRODUCTS']")).click();
				

			}
		}
		catch (Exception e) 
		{
			//extentTest.log(Status.FAIL, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//assertTrue(false);
		}
	}

	
	//Verify Product Added from Product Home Page is Visible in Your Cart Page
	public static void yourCart_ProductValidation() throws IOException
	{
		String productName = null;
		try
		{

			for(Map.Entry m : Base.productDetails.entrySet())
			{  
				productName=m.getKey().toString();
				Thread.sleep(2000);
				elementScrollBy_DescriptionAndText("test-Cart Content", productName);
				//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"\")).scrollIntoView(text(\""+m.getKey().toString()+"\"));");
				//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+m.getKey().toString()+"\"));");
				extentTest.log(Status.PASS, m.getKey().toString()+"Added Product is available");
				//assertTrue(driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+m.getKey().toString()+"']")).isDisplayed());	

			} 
		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, "Added Product "+productName+" is not available in YourCart",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Verify Product Added from Product Home Page is Visible in Checkout OverView Page
	//Validate Cart Amount Total With Item Total
	public static void checkoutOverView_ProductValidation() throws IOException
	{
		try
		{
			double productAmt= 0.0;

			for(Map.Entry m : Base.productDetails.entrySet())
			{ 

				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-CHECKOUT: OVERVIEW\")).scrollIntoView(text(\""+m.getKey().toString()+"\"));");
				extentTest.log(Status.PASS, m.getKey().toString()+"Added Product is available");
				assertTrue(driver.findElement(MobileBy.xpath("//*[@text='"+m.getKey().toString()+"']/../..//*[@text='"+m.getValue().toString()+"']")).isDisplayed());			
				productAmt = productAmt + getAmount(m.getValue().toString());
			} 		

			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-CHECKOUT: OVERVIEW\")).scrollIntoView(textStartsWith(\"Item total:\"));");		
			double producttotal = getAmount(driver.findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"Item total:\");").getText().split(":")[1].trim());

			if(producttotal==0)
			{
				extentTest.log(Status.FAIL, "Cart Total amount is **$0.00**",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				//extentTest.log(Status.FAIL, "Added Product is not available in Checkout Overview", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				assertTrue(false);
			}
			else 
			{
				if(productAmt==producttotal)
				{
					extentTest.log(Status.PASS, "Cart Total "+productAmt+" and Item Total "+producttotal+" are Equal");
				}
				else
				{
					//extentTest.log(Status.FAIL, "Cart Total "+productAmt+" and Item Total "+producttotal+" are Not Equal",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
					
				}
				assertEquals(true, productAmt==producttotal);
			}
			//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-CHECKOUT: OVERVIEW\")).scrollIntoView(text(\"FINISH\"));");
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"FINISH\"));");


		}
		catch (Exception e) 
		{
			
			extentTest.log(Status.FAIL, "Added Product is not available in Checkout Overview");
			//extentTest.log(Status.FAIL, "Added Product is not available in Checkout Overview"+ extentTest.addScreenCaptureFromPath(getScreenshot()));
			//extentTest.fail("Added Product is not available in Checkout Overview",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//extentTest.log(Status.FAIL, "Added Product is not available in Checkout Overview", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Remove Product From Your Cart Page uing Product Name
	public static void removeProduct_YourCart(String productName) throws IOException
	{
		try
		{

			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-Cart Content\")).scrollIntoView(text(\""+productName+"\"));");
			driver.findElement(MobileBy.xpath("//*[@text='"+productName+"']/../..//*[@text='REMOVE']")).click();

			Base.productDetails.remove(productName);
			extentTest.log(Status.PASS, productName+" removed from cart");


		}
		catch (Exception e) 
		{
			extentTest.log(Status.FAIL, productName+" not removed from cart",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Convert Amount from String Format to Double
	public static double getAmount(String value)

	{

		value= value.substring(1);

		double amount2value=Double.parseDouble(value);

		return amount2value;

	}

}
