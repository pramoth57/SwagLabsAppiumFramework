package com.SwagLabs.Utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= System.getProperty("user.dir")+"\\src\\main\\java\\\\resources\\global.properties";


	public ConfigFileReader()
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try 
			{
				properties.load(reader);
				reader.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public String getswagLagsappApp()
	{
		String swagLagsappAppPath = properties.getProperty("swagLagsapp");
		if(swagLagsappAppPath!= null) 
		{	
			return swagLagsappAppPath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}
	
	
	public String getdeviceType()
	{
		String deviceTypePath = properties.getProperty("deviceType");
		if(deviceTypePath!= null) 
		{	
			return deviceTypePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}
	
	public String getemulatorType()
	{
		String deviceTypePath = properties.getProperty("emulator");
		if(deviceTypePath!= null) 
		{	
			return deviceTypePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}

	public String getdevice()
	{
		String devicePath = properties.getProperty("device");
		if(devicePath!= null) 
		{	
			return devicePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}
	
	public String get_EmulatorDevice()
	{
		String devicePath = properties.getProperty("emulator_Device");
		if(devicePath!= null) 
		{	
			return devicePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}
	
	public String getFirstName()
	{
		String FirstNamePath = properties.getProperty("FirstName");
		if(FirstNamePath!= null) 
		{	
			return FirstNamePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}

	
	public String getLastName()
	{
		String LastNamePath = properties.getProperty("LastName");
		if(LastNamePath!= null) 
		{	
			return LastNamePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}

	public String getPincode()
	{
		String PincodePath = properties.getProperty("Pincode");
		if(PincodePath!= null) 
		{	
			return PincodePath;
		}
		else 
		{
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
		}
	}


}


