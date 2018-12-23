package com.mercuryTravel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomepageTests extends TestSetup {

	@Test(priority = 0)
	public void verifyUserLoginToMercureyTravel() throws Exception {

		hompage.userLogin("saurabh.d2106@gmail.com", "Pro@1234");

		String expectedWelcomeText = "Welcome, Saurabh";
		String actualWelcomeText = hompage.getWelcomeText();

		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);

	}

}
