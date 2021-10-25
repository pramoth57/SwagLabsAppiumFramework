package com.SwagLabs.Utilities;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base
{
	public  AppiumDriverLocalService appium_Service;
	public  AppiumDriver<MobileElement>  driver;
	ThreadLocalInstance threadLocalInstance = new ThreadLocalInstance();
	public static String uuidVal;
	public HashMap<String, String> productDetails = new HashMap<String, String>();
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports reports;
	ThreadLocalInstance threadLocalDriver = new ThreadLocalInstance();
	protected WebDriverWait wait;


	@BeforeSuite
	public void SetupExtentReport() throws IOException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		appium_Service = initiate_AppiumService();
		intitate_extentReport();

	}

	@BeforeTest
	@Parameters ({"uuid"})
	public void preTestCondition(String uuid) throws IOException, InterruptedException
	{
		threadLocalInstance.setTLDriver(intiDriver(uuid));

	}

	@AfterTest
	public void postTestCondition() throws IOException
	{
		threadLocalInstance.unloadTLDriver();
	}

	@AfterSuite
	public void closeExtentReport()
	{
		extentReport_flush();
		appium_Service.stop();
	}



	//Initiate Extent Report instance
	public static void intitate_extentReport()
	{
		//htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\SwagLabsTesExecutiontReport_"+System.currentTimeMillis()+".html");
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\SwagLabsTesExecutiontReport.html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setDocumentTitle("SwagLabs Automation Reports");
		htmlreporter.config().setReportName("Swaglabs Automtion Execution reports");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setCSS(".r-img { width: 15%; height: auto; }");
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


		}
		catch (Exception e) 
		{

			System.exit(0);

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

	/*public static void startGridConfig() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("C:\\SeleniumGridConfig\\SeleniumHub.bat");
		Thread.sleep(6000);
		Runtime.getRuntime().exec("C:\\SeleniumGridConfig\\SeleniumNode1.bat");
		Thread.sleep(6000);
		Runtime.getRuntime().exec("C:\\SeleniumGridConfig\\SeleniumNode2.bat");
		Thread.sleep(6000);
	}*/
	//Initiate Android Driver with corresponding Capabilities
	public AppiumDriver<MobileElement> capabilities(String appName) throws IOException, InterruptedException
	{


		String driverDevice = "Android";
		String deviceType = null;
		String device = null;
		File appDir = new File("resource");
		File app = new File(appDir, FileReader.getInstance().getConfigReader().getswagLagsappApp());
		String os = FileReader.getInstance().getConfigReader().getdevice_os();

		if(driverDevice.equals("Android"))
		{
			deviceType= FileReader.getInstance().getConfigReader().getdeviceType();
			device= FileReader.getInstance().getConfigReader().getdevice();
		}
		else
		{
			deviceType= FileReader.getInstance().getConfigReader().getemulatorType();
			device=(String) FileReader.getInstance().getConfigReader().get_EmulatorDevice();
		}

		if(deviceType.contains("emulator"))
		{
			try 
			{
				startEmulator();
				threadLocalInstance.getextentTest().log(Status.PASS, "Emulator Started Successfully");
			}
			catch (Exception e) 
			{
				threadLocalInstance.getextentTest().log(Status.FAIL, "Emulator not Started Successfully");

			}
		}
		System.out.println();

		if(os.equalsIgnoreCase("Android"))
		{

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
			capabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"\\resources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");		
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);

			/*DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browserstack.user", "mooorthiadvocate_m6qFen");
			caps.setCapability("browserstack.key", "wbr8sabPGdxCYSYytf5f");
			caps.setCapability("app", "bs://29802c7b7cdcc8e367ecb01b9ceaa181c7545788");
			caps.setCapability("device", "Google Pixel 3");
			caps.setCapability("os_version", "9.0");
			caps.setCapability("project", "First Java Project");
			caps.setCapability("build", "Java Android");
			caps.setCapability("name", "first_test");*/


			try 
			{
				//driver = new AndroidDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);					
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				threadLocalInstance.getextentTest().log(Status.PASS, "Android Driver Started Successfully");

			}
			catch (Exception e) 
			{
				threadLocalInstance.getextentTest().log(Status.FAIL, "Android Drive not Started Successfully \n"+getLogCat_logss(driver.manage().logs().get("logcat")));
			}
		}
		else if(os.equalsIgnoreCase("IOS"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
			capabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"\\resources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");		
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);

			try 
			{
				//driver = new AndroidDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);

				driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				threadLocalInstance.getextentTest().log(Status.PASS, "IOS Driver Started Successfully");

			}
			catch (Exception e) 
			{
				// TODO: handle exception
				threadLocalInstance.getextentTest().log(Status.FAIL, "IOS Drive not Started Successfully");

			}
		}

		return driver;
	}


	public static String getLogCat_logss(LogEntries logs)
	{
		String log = "";

		for(LogEntry logEntry: StreamSupport.stream(logs.spliterator(), false).skip(logs.getAll().size() - 5).collect(Collectors.toList()))
		{

			if(log.equals(""))
			{
				log = logEntry.toString();		        	
			}
			else
			{	
				log = log+"\n"+logEntry.toString();
			}

		}

		return log;
	}

	//Capture Screen Shot
	public String getScreenshot() throws IOException
	{
		File scrfile=	((TakesScreenshot)threadLocalInstance.getTLDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\ScreenShot\\"+System.currentTimeMillis()+".png";
		FileUtils.copyFile(scrfile,new File(path));
		return path;

	}




	public boolean waitforElementVisible(WebElement oElement) throws IOException
	{
		wait = new WebDriverWait(threadLocalDriver.getTLDriver(), 5);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(oElement));
		}
		catch (Exception e) {
			return false;
		}

		return true;

	}

	//Scroll Element into View using Visible Text
	public void elementScrollByText(String oText) throws IOException
	{
		try
		{	
			((FindsByAndroidUIAutomator<MobileElement>) threadLocalDriver.getTLDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+oText+"\"));");
			threadLocalInstance.getextentTest().log(Status.PASS, oText+" Text Scrolled Into View Successfully");
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, oText+" Text Scrolled Not Into View Successfully \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}






	// Verify Element displayed in View
	public boolean elementDispayed(String oText)
	{
		try
		{	waitforElementVisible(threadLocalDriver.getTLDriver().findElement(By.xpath("//*[@text='"+oText+"']")));
		threadLocalDriver.getTLDriver().findElement(By.xpath("//*[@text='"+oText+"']")).isDisplayed();
		threadLocalInstance.getextentTest().log(Status.PASS, oText+" is Displayed In View");
		return true;
		}
		catch (Exception e) 
		{
			//extentTest.log(Status.FAIL, oText+" is not Displayed In View");
			return false;
		}

	}

	//Scroll Element into View using Description and Visible Text
	public void elementScrollBy_DescriptionAndText(String oDescription, String oText) throws IOException
	{
		try
		{
			((FindsByAndroidUIAutomator<MobileElement>) threadLocalDriver.getTLDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\""+oDescription+"\")).scrollIntoView(text(\""+oText+"\"));");
			threadLocalInstance.getextentTest().log(Status.PASS, oText+" Text Scrolled Into View Successfully");
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, oText+" Text Scrolled Not Into View Successfully \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	public void elementVisibility(String oElementName) throws IOException
	{
		try
		{	Thread.sleep(3000);
		waitforElementVisible(threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@text='"+oElementName+"']")));
		boolean status = threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@text='"+oElementName+"']")).isDisplayed();
		if(status)
		{	
			threadLocalInstance.getextentTest().log(Status.PASS, oElementName+" Page Is Visible");
		}
		else
		{
			threadLocalInstance.getextentTest().log(Status.PASS, oElementName+" Page not Is Visible"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")));
		}
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, oElementName+" Page Is not Visible"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Perform Click Action on WebElement
	public void elementClick(WebElement oElement, String oElementName) throws IOException
	{
		try
		{
			waitforElementVisible(oElement);
			oElement.click();
			threadLocalInstance.getextentTest().log(Status.PASS, oElementName+" Clicked Successfully");
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, oElementName+" Click Unsuccessfully \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}



	//Perform Send Keys Action on Webelement
	public void elementSendKeys(WebElement oElement, String oElementName, String oData) throws IOException
	{
		try
		{
			waitforElementVisible(oElement);
			oElement.sendKeys(oData);;
			threadLocalInstance.getextentTest().log(Status.PASS, "Data *"+oData+"* Entered Successfully for "+oElementName+" Field");
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, "Data *"+oData+"* Not Entered Successfully for "+oElementName+" Field \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Add Product in Product Home Page Using Product Name
	public void addProduct(String oProductList) throws IOException
	{
		String productAmount = null;
		String productname = null;

		try
		{

			for(String product: oProductList.split(","))
			{	
				System.out.println(product);
				elementScrollBy_DescriptionAndText("test-PRODUCTS", product);
				threadLocalInstance.getextentTest().log(Status.PASS, product+" Scrolled into View");
				productname = product;
				productAmount = threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@content-desc='test-Price']")).getAttribute("text");
				productDetails.put(product, productAmount);		
				threadLocalInstance.getextentTest().log(Status.PASS, product+" Amount "+productAmount+" retrived Successfully");
				threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@text='ADD TO CART']")).click();
				threadLocalInstance.getextentTest().log(Status.PASS, product+" Added to Cart Successfully");

			}

			threadLocalInstance.setproductDetails(productDetails);
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, "Unable to Add "+productname+" Product To Cart \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//assertTrue(false);
		}
	}

	//Validate Product informaion in Your and Invertory Page
	public void validateProductInfo(String oProductList) throws IOException
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
				elementScrollBy_DescriptionAndText("test-PRODUCTS", product);
				threadLocalInstance.getextentTest().log(Status.PASS, product+" Scrolled into View");
				productname = product;
				productAmount = threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@content-desc='test-Price']")).getAttribute("text");
				threadLocalInstance.getextentTest().log(Status.PASS, product+" Amount "+productAmount+" retrived Successfully");
				productDetails.put(product, productAmount);							
				threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']")).click();
				Thread.sleep(3000);
				inventoryproductname=threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@content-desc='test-Description']/android.widget.TextView")).getAttribute("text");
				if(productname.equals(inventoryproductname))
				{
					threadLocalInstance.getextentTest().log(Status.PASS, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page ");

				}
				else 
				{
					threadLocalInstance.getextentTest().log(Status.FAIL, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page \n");//+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")));

				}

				elementScrollBy_DescriptionAndText("test-Inventory item page", "ADD TO CART");							
				inventoryproductAmount=threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@content-desc='test-Price']")).getAttribute("text");
				if(productAmount.equals(inventoryproductAmount))
				{
					threadLocalInstance.getextentTest().log(Status.PASS, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page ");

				}
				else 
				{
					threadLocalInstance.getextentTest().log(Status.FAIL, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page \n");//+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")));
				}

				threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@text='BACK TO PRODUCTS']")).click();


			}
			threadLocalInstance.setproductDetails(productDetails);
		}
		catch (Exception e) 
		{
			//extentTest.log(Status.FAIL, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//assertTrue(false);
		}
	}

	//Validate Product informaion in Your and Invertory Page and Add the Product To Cart from Inventory Page 
	public void validateProductInfo_addCart(String oProductList) throws IOException
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
				elementScrollBy_DescriptionAndText("test-PRODUCTS", product);
				productname = product;
				productAmount = threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']/..//*[@content-desc='test-Price']")).getAttribute("text");
				threadLocalInstance.getextentTest().log(Status.PASS, product+" Amount "+productAmount+" retrived Successfully");
				productDetails.put(product, productAmount);							
				threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//android.widget.TextView[@text='"+product+"']")).click();
				Thread.sleep(3000);
				inventoryproductname=threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@content-desc='test-Description']/android.widget.TextView")).getAttribute("text");
				if(productname.equals(inventoryproductname))
				{
					threadLocalInstance.getextentTest().log(Status.PASS, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page ");

				}
				else 
				{
					threadLocalInstance.getextentTest().log(Status.FAIL, productname+" Product Title in Your Cart Page is displayed as "+inventoryproductname+" in Inventory Page \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")));

				}

				elementScrollBy_DescriptionAndText("test-Inventory item page", "ADD TO CART");
				inventoryproductAmount=threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@content-desc='test-Price']")).getAttribute("text");
				if(productAmount.equals(inventoryproductAmount))
				{
					threadLocalInstance.getextentTest().log(Status.PASS, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page ");

				}
				else 
				{
					threadLocalInstance.getextentTest().log(Status.FAIL, productname+" Product Amount "+productAmount+" in Your Cart Page is displayed as "+inventoryproductAmount+" in Inventory Page \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")));
				}
				threadLocalDriver.getTLDriver().findElement(By.xpath("//*[@text='ADD TO CART']")).click();
				threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@text='BACK TO PRODUCTS']")).click();


			}
			threadLocalInstance.setproductDetails(productDetails);
		}
		catch (Exception e) 
		{
			//extentTest.log(Status.FAIL, "Unable to Add "+productname+" Product To Cart ",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			//assertTrue(false);
		}
	}

	public void removeAll (By oLocator) throws IOException, InterruptedException
	{
		elementVisibility("YOUR CART");

		while(elementDispayed("REMOVE"))	
		{
			Thread.sleep(2000);
			try
			{
				threadLocalInstance.getTLDriver().findElements(oLocator).get(0).click();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		threadLocalInstance.getproductDetails().clear();

	}


	//Verify Product Added from Product Home Page is Visible in Your Cart Page
	public void yourCart_ProductValidation() throws IOException
	{
		String productName = null;
		try
		{
			System.out.println(threadLocalInstance.getproductDetails());
			for(Map.Entry m : threadLocalInstance.getproductDetails().entrySet())
			{  
				productName=m.getKey().toString();
				Thread.sleep(2000);
				elementScrollBy_DescriptionAndText("test-Cart Content", productName);
				threadLocalInstance.getextentTest().log(Status.PASS, m.getKey().toString()+"Added Product is available");

			} 
		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, "Added Product "+productName+" is not available in YourCart \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Verify Product Added from Product Home Page is Visible in Checkout OverView Page
	//Validate Cart Amount Total With Item Total
	public void checkoutOverView_ProductValidation() throws IOException
	{
		try
		{
			double productAmt= 0.0;


			for(Map.Entry m : threadLocalInstance.getproductDetails().entrySet())
			{ 
				System.out.println(m.getKey().toString());

				elementScrollBy_DescriptionAndText("test-CHECKOUT: OVERVIEW", m.getKey().toString());
				threadLocalInstance.getextentTest().log(Status.PASS, m.getKey().toString()+"Added Product is available");
				assertTrue(threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@text='"+m.getKey().toString()+"']/../..//*[@text='"+m.getValue().toString()+"']")).isDisplayed());			
				productAmt = productAmt + getAmount(m.getValue().toString());
			} 		

			((FindsByAndroidUIAutomator<MobileElement>) threadLocalDriver.getTLDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().description(\"test-CHECKOUT: OVERVIEW\")).scrollIntoView(textStartsWith(\"Item total:\"));");	
			double producttotal = getAmount(((FindsByAndroidUIAutomator<MobileElement>) threadLocalDriver.getTLDriver()).findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"Item total:\");").getText().split(":")[1].trim());

			if(producttotal==0)
			{
				threadLocalInstance.getextentTest().log(Status.FAIL, "Cart Total amount is **$0.00**  \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				assertTrue(false);
			}
			else 
			{
				if(productAmt==producttotal)
				{
					threadLocalInstance.getextentTest().log(Status.PASS, "Cart Total "+productAmt+" and Item Total "+producttotal+" are Equal");
				}
				else
				{
					threadLocalInstance.getextentTest().log(Status.FAIL, "Cart Total "+productAmt+" and Item Total "+producttotal+" are Not Equal \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

				}
				assertEquals(true, productAmt==producttotal);
			}
			elementScrollByText("FINISH");

		}
		catch (Exception e) 
		{

			threadLocalInstance.getextentTest().log(Status.FAIL, "Added Product is not available in Checkout Overview");

		}
	}

	//Remove Product From Your Cart Page uing Product Name
	public void removeProduct_YourCart(String productName) throws IOException
	{
		try
		{			
			elementScrollBy_DescriptionAndText("test-Cart Content", productName);
			threadLocalDriver.getTLDriver().findElement(MobileBy.xpath("//*[@text='"+productName+"']/../..//*[@text='REMOVE']")).click();
			threadLocalInstance.getproductDetails().remove(productName);
			threadLocalInstance.getextentTest().log(Status.PASS, productName+" removed from cart");

		}
		catch (Exception e) 
		{
			threadLocalInstance.getextentTest().log(Status.FAIL, productName+" not removed from cart \n"+getLogCat_logss(threadLocalDriver.getTLDriver().manage().logs().get("logcat")),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
	}

	//Convert Amount from String Format to Double
	public static double getAmount(String value)

	{

		value= value.substring(1);

		double amount2value=Double.parseDouble(value);

		return amount2value;

	}

	public AppiumDriver<MobileElement> intiDriver(String udid) throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();		
		capabilities.setCapability(MobileCapabilityType.UDID, udid);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, 15000);
		capabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"\\resources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);		
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(3000);
		return driver;



	}

}
