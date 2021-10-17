package com.SwagLabs.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_LoginPage extends Base{
	
	AppiumDriver<MobileElement> driver;
	public SwagLabs_LoginPage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	private String standard_user = "";
	
	@AndroidFindBy(xpath = "//*[@text='standard_user']")
	private WebElement standardUser;
	
	By standarduser1 = By.xpath("//*[@text='standard_user']");
	@AndroidFindBy(xpath = "//*[@text='problem_user']")
	private WebElement problemUser;
	By problemUser1 = By.xpath("//*[@text='problem_user']");
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
	private WebElement login_btn;
	By login_btn1 = By.xpath("//android.widget.TextView[@text='LOGIN']");
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']")
	private WebElement menu;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGOUT']")
	private WebElement logout;
	
	
	public void login_StandardUser() throws IOException, InterruptedException
	{ 
		//((FindsByAndroidUIAutomator<MobileElement>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"standard_user\"));");
		//standardUser.click();
		//((FindsByAndroidUIAutomator<MobileElement>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"LOGIN\"));");
		//standardUser.click();
		//login_btn.click();
		
		//elementScrollByText1(driver,"standard_user");
		//elementClick1(driver,standarduser1,"Standard User");
		//elementScrollByText1(driver,"LOGIN");
		//elementClick1(driver,login_btn1,"Login Button");
		//driver.hideKeyboard();
		Thread.sleep(3000);
		elementScrollByText("standard_user");
		elementClick(standardUser,"Standard User");
		elementScrollByText("LOGIN");
		elementClick(login_btn,"Login Button");
	}
	
	public void logout() throws IOException, InterruptedException
	{ 		
		
		elementClick(menu,"Menu");
		//elementScrollByText("LOGIN");
		elementClick(logout,"Logout Button");
	}
	public void login_problemUser() throws IOException
	{ 
		elementScrollByText("problem_user");
		elementClick(problemUser,"Problem User");
		elementScrollByText("LOGIN");
		elementClick(login_btn,"Login Button");
		
	}
	
	
}
