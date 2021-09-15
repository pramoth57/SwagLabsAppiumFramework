package com.SwagLabs.Utilities;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;

	private FileReaderManager() 
	{
	
	}
	//Create Singleton Access for reading Properties File
	public static FileReaderManager getInstance( ) 
	{
		return fileReaderManager;
	}

	public ConfigFileReader getConfigReader() 
	{
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

}
