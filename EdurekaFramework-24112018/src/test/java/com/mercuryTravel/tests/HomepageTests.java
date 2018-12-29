package com.mercuryTravel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class HomepageTests extends TestSetup {

	@Test(priority = 0)
	public void verifyUserLoginToMercureyTravel() throws Exception {
		
		extentTest = extent.createTest("TC1 - Verify User Login to the application");

		String userEmailId = configProperty.getProperty("userEmailId");
		String userPassword = configProperty.getProperty("userPassword");
		hompage.userLogin(userEmailId, userPassword);
		extentTest.log(Status.INFO, "User login done");
		
		extentTest.log(Status.INFO, "Comparing expected and actual welcome text");
		String expectedWelcomeText = "Welcome, Saurabh";
		String actualWelcomeText = hompage.getWelcomeText();

		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);
		
		extentTest.log(Status.INFO, "Actual and Expected welcome text matched");

	}

}
