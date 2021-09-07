package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabs_LoginPage {
	
	AndroidDriver<AndroidElement> driver;
	public SwagLabs_LoginPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}

	
	@AndroidFindBy(xpath = "//*[@text='standard_user']")
	private WebElement standardUser;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
	private WebElement login_btn;
	
	
	
	public void Login()
	{
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"standard_user\"));");
		standardUser.click();
		login_btn.click();
		
	}
	
	
}
