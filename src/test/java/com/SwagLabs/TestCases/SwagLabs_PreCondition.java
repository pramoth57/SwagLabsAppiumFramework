package com.SwagLabs.TestCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.SwagLabs.Utilities.Base;

public class SwagLabs_PreCondition extends Base{
	
	@BeforeSuite
	public void SetupExtentReport(String uuid) throws IOException
	{
		//System.out.println(uuid);
		//Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		//appium_Service = initiate_AppiumService();
		//intitate_extentReport();
			
	}

}
