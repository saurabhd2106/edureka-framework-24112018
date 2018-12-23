package com.mercuryTravel.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.mercuryTravel.pages.Holidayspage;
import com.mercuryTravel.pages.Homepage;
import com.mercuryTravel.utils.ConfigReader;

import commonLibs.implementation.CommonDriver;

public class TestSetup {

	CommonDriver cmnDriver;
	WebDriver driver;
	Homepage hompage;
	Holidayspage holidayPage;

	Properties configProperty;
	String configFilePath;
	String currentWorkingDirectory;

	@BeforeSuite
	public void presetup() throws Exception {
		initializeCurrentWorkingDirectoryPath();

		configFilePath = String.format("%s/config/config.properties", currentWorkingDirectory);

		configProperty = ConfigReader.readConfigProperties(configFilePath);
	}

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {

		invokeBrowser();

		initializePages();
	}

	@AfterClass(alwaysRun = true)
	public void cleanup() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

	private void initializePages() {
		hompage = new Homepage(driver);
		holidayPage = new Holidayspage(driver);
	}

	private void invokeBrowser() throws Exception {
		cmnDriver = new CommonDriver(configProperty.getProperty("browserType"));
		
		int pageLoadTime = Integer.parseInt(configProperty.getProperty("pageLoadTimeout"));
		cmnDriver.setPageloadTimeout(pageLoadTime);
		
		int elementDetectionTimeout = Integer.parseInt(configProperty.getProperty("elementDetectionTimeout"));
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeout);
		cmnDriver.navigateToFirstUrl(configProperty.getProperty("baseUrl"));

		driver = cmnDriver.getDriver();

	}

	private void initializeCurrentWorkingDirectoryPath() {
		currentWorkingDirectory = System.getProperty("user.dir");
	}

}
