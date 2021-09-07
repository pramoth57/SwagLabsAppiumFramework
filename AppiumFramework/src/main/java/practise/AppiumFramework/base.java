package practise.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import managers.FileReaderManager;

public class base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement>  driver;

	public AppiumDriverLocalService startServer()
	{

		boolean flag= checkIfServerIsRunnning(4723);
		if(!flag)
		{		
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
		

	}

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
	
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		
		Thread.sleep(6000);
	}

	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{

		
		File appDir = new File("src");
		File app = new File(appDir, FileReaderManager.getInstance().getConfigReader().getswagLagsappApp());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String deviceType= FileReaderManager.getInstance().getConfigReader().getdeviceType();
		String device=(String) FileReaderManager.getInstance().getConfigReader().getdevice();
		
		if(deviceType.contains("emulator"))
		{
			startEmulator();
		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.swaglabsmobileapp.MainActivity");
		capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");				
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);		
		driver = new  AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public static void getScreenshot(String s) throws IOException
	{
		File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\ScreenShot\\"+s+".png"));

	}



}
