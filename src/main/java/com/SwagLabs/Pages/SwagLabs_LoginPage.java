package com.SwagLabs.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.SwagLabs.Utilities.Base;

import io.appium.java_client.AppiumDriver;
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
	
	@AndroidFindBy(xpath = "//*[@text='problem_user']")
	private WebElement problemUser;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
	private WebElement login_btn;
	
	
	
	public void login_StandardUser() throws IOException
	{ 
		elementScrollByText("standard_user");
		elementClick(standardUser,"Standard User");
		elementScrollByText("LOGIN");
		elementClick(login_btn,"Login Button");
		
	}
	
	public void login_problemUser() throws IOException
	{ 
		elementScrollByText("problem_user");
		elementClick(problemUser,"Problem User");
		elementScrollByText("LOGIN");
		elementClick(login_btn,"Login Button");
		
	}
	
	
}
