package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.FileReaderManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutInformation {

	AndroidDriver<AndroidElement> driver;
	public SwagLabs_CheckoutInformation(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	
	@AndroidFindBy(xpath = "//*[@text='First Name']")
	private WebElement firstName;
	
	@AndroidFindBy(xpath = "//*[@text='Last Name']")
	private WebElement lasttName;
	
	@AndroidFindBy(xpath = "//*[@text='Zip/Postal Code']")
	private WebElement postalCode;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CONTINUE']")
	private WebElement continue_btn;
	
	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	private WebElement cancel_btn;
	

	//FileReaderManager.getInstance().getConfigReader().getdeviceType()

	public void Checkout()
	{		
		firstName.sendKeys(FileReaderManager.getInstance().getConfigReader().getFirstName());
		lasttName.sendKeys(FileReaderManager.getInstance().getConfigReader().getLastName());
		postalCode.sendKeys(FileReaderManager.getInstance().getConfigReader().getPincode());
		driver.hideKeyboard();
		continue_btn.click();
		

	}
	
	public void cancel()
	{		
		firstName.sendKeys(FileReaderManager.getInstance().getConfigReader().getFirstName());
		lasttName.sendKeys(FileReaderManager.getInstance().getConfigReader().getLastName());
		postalCode.sendKeys(FileReaderManager.getInstance().getConfigReader().getPincode());
		driver.hideKeyboard();
		cancel_btn.click();
		

	}


}
