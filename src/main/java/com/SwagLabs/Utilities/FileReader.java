package com.SwagLabs.Utilities;

public class FileReader {

	private static FileReader fileReaderManager = new FileReader();
	private static ConfigFileReader configFileReader;

	private FileReader() 
	{
	
	}
	//Create Singleton Access for reading Properties File
	public static FileReader getInstance( ) 
	{
		return fileReaderManager;
	}

	public ConfigFileReader getConfigReader() 
	{
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

}
