package com.SwagLabs.TestCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.SwagLabs.Utilities.Base;

public class SwagLabs_PostCondition extends Base{
	
	@Test
	public void SetupExtentReport()
	{
		extentReport_flush();
	}

}
