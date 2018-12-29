package com.mercuryTravel.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mercuryTravel.pages.Holidayspage;
import com.mercuryTravel.pages.Homepage;
import com.mercuryTravel.utils.ConfigReader;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utils.DateUtils;

public class TestSetup {

	CommonDriver cmnDriver;
	WebDriver driver;
	Homepage hompage;
	Holidayspage holidayPage;

	Properties configProperty;
	String configFilePath;
	String currentWorkingDirectory;

	String executionStartTime;

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest extentTest;

	ScreenshotControl screenshot;

	@BeforeSuite
	public void presetup() throws Exception {

		startExecutionTime();

		initializeCurrentWorkingDirectoryPath();

		readConfigurationPropertiesFile();

		initializeReport();
	}

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		extentTest = extent.createTest("Setup : Invoking Browser");

		extentTest.log(Status.INFO, "Invoking Browser");
		invokeBrowser();

		initializeScreenshotInstance();

		extentTest.log(Status.INFO, "Initializing pages");
		initializePages();
	}

	private void initializeScreenshotInstance() {
		screenshot = new ScreenshotControl(driver);

	}

	@AfterClass(alwaysRun = true)
	public void cleanup() throws Exception {
		extentTest = extent.createTest("Clean up :Closing Browser");
		cmnDriver.closeAllBrowsers();
	}

	@AfterSuite
	public void postCleanup() {
		extent.flush();
	}

	@AfterMethod
	public void afterAMethod(ITestResult result) throws Exception{
		String methodName = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test case pass - " + methodName);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test case pass - " + methodName);

			captureTheScreenshotAndSaveItInReport(methodName);
		
		} else {
			extentTest.log(Status.SKIP, "Test case pass - " + methodName);
		}

	}

	private void captureTheScreenshotAndSaveItInReport(String methodName) throws Exception{
		String screenshotFileName = String.format("%s/screenshots/%s_%s.jpeg", currentWorkingDirectory, methodName,
				executionStartTime);

		screenshot.captureAndSaveScreenshot(screenshotFileName);
		
		extentTest.addScreenCaptureFromPath(screenshotFileName);
		
	}

	private void initializePages() {
		extentTest.log(Status.INFO, "Initialized homepage instance");
		hompage = new Homepage(driver);
		extentTest.log(Status.INFO, "Initialized holiday page instance");
		holidayPage = new Holidayspage(driver);
	}

	private void startExecutionTime() {
		executionStartTime = DateUtils.getDate();
	}

	private void initializeReport() {
		String reportFilename = String.format("%s/reports/MercuryTravel_%s.html", currentWorkingDirectory,
				executionStartTime);

		htmlReporter = new ExtentHtmlReporter(reportFilename);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
	}

	private void readConfigurationPropertiesFile() throws Exception {
		configFilePath = String.format("%s/config/config.properties", currentWorkingDirectory);

		configProperty = ConfigReader.readConfigProperties(configFilePath);
	}

	private void invokeBrowser() throws Exception {
		extentTest.log(Status.INFO, "Initializing common Driver instance");
		cmnDriver = new CommonDriver(configProperty.getProperty("browserType"));

		int pageLoadTime = Integer.parseInt(configProperty.getProperty("pageLoadTimeout"));
		cmnDriver.setPageloadTimeout(pageLoadTime);
		extentTest.log(Status.INFO, "Initailized page load time out");

		int elementDetectionTimeout = Integer.parseInt(configProperty.getProperty("elementDetectionTimeout"));
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeout);
		cmnDriver.navigateToFirstUrl(configProperty.getProperty("baseUrl"));

		driver = cmnDriver.getDriver();

	}

	private void initializeCurrentWorkingDirectoryPath() {
		currentWorkingDirectory = System.getProperty("user.dir");
	}

}
