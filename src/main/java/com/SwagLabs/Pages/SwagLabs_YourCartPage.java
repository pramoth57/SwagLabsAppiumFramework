package com.SwagLabs.Pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_YourCartPage extends Base {

	AndroidDriver<MobileElement> driver;
	public SwagLabs_YourCartPage(AndroidDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}


	@AndroidFindBy(xpath = "//*[@text='CHECKOUT']")
	private WebElement checkout;

	@AndroidFindBy(xpath = "//*[@text='CONTINUE SHOPPING']")
	private WebElement continueShooping_btn;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
	private List<WebElement> remove;




	public void checkout() throws InterruptedException, IOException
	{	yourCart_ProductValidation();	
	elementScrollBy_DescriptionAndText("test-Cart Content","CHECKOUT");
	elementClick(checkout, "Check out");	


	}

	public void click_Continue() throws IOException
	{		
		elementScrollByText("CONTINUE SHOPPING");
		elementClick(continueShooping_btn, "Continue Shopping");

	}

	public void removeAll_Checkout() throws InterruptedException, IOException
	{		

		while(elementDispayed("REMOVE"))	
		{
			Thread.sleep(2000);
			try
			{
				remove.get(0).click();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		Base.productDetails.clear();
		elementScrollByText("CHECKOUT");
		elementClick(checkout, "CHECKOUT");

	}

	public void remove_Checkout(String productName) throws InterruptedException, IOException
	{		


		Thread.sleep(2000);
		removeProduct_YourCart(productName);
		elementScrollByText("CHECKOUT");
		elementClick(checkout, "CHECKOUT");
		
	}



}
