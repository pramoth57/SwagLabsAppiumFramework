package com.SwagLabs.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;
import com.SwagLabs.Utilities.FileReaderManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_CheckoutInformation extends Base {

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

	public void checkout() throws IOException
	{		
		/*firstName.sendKeys(FileReaderManager.getInstance().getConfigReader().getFirstName());
		lasttName.sendKeys(FileReaderManager.getInstance().getConfigReader().getLastName());
		postalCode.sendKeys(FileReaderManager.getInstance().getConfigReader().getPincode());
		//driver.hideKeyboard();
		continue_btn.click();*/
		elementSendKeys(firstName, "First Name", FileReaderManager.getInstance().getConfigReader().getFirstName());
		elementSendKeys(lasttName, "Last Name", FileReaderManager.getInstance().getConfigReader().getLastName());
		elementSendKeys(postalCode, "PostalCode", FileReaderManager.getInstance().getConfigReader().getPincode());
		elementClick(continue_btn, "Continue");
		

	}
	
	public void cancel() throws IOException
	{		
		elementSendKeys(firstName, "First Name", FileReaderManager.getInstance().getConfigReader().getFirstName());
		elementSendKeys(lasttName, "Last Name", FileReaderManager.getInstance().getConfigReader().getLastName());
		elementSendKeys(postalCode, "PostalCode", FileReaderManager.getInstance().getConfigReader().getPincode());
		elementClick(cancel_btn, "Cancel");
		
		

	}


}
