package designPattern;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;

public class DemoAmazonPOM2Style {

	CommonDriver cmnDriver;
	AmazonPOM2Style homePage;
	
	@BeforeClass
	public void invokeBrowser() throws Exception {
		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(40);
		cmnDriver.setElementDetectionTimeout(10);

		cmnDriver.navigateToFirstUrl("https://www.amazon.in/");
		WebDriver driver = cmnDriver.getDriver();

		homePage = new AmazonPOM2Style(driver);
	}

	@Test
	public void verifySearchProduct() throws Exception {
		homePage.searchProduct("Rado watch", "Watches");
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

}
