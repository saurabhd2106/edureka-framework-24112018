package designPattern;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class DemoAmazonPOM1Style {

	CommonDriver cmnDriver;

	ElementControl elementcontrol;
	DropdownControl dropownControl;
	AmazonPOM1Style homepage;

	@BeforeClass
	public void invokeBrowser() throws Exception {
		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(40);
		cmnDriver.setElementDetectionTimeout(10);

		cmnDriver.navigateToFirstUrl("https://www.amazon.in/");
		WebDriver driver = cmnDriver.getDriver();

		elementcontrol = new ElementControl();
		dropownControl = new DropdownControl();
		homepage = new AmazonPOM1Style(driver);

	}

	@Test
	public void verifySearchProduct() throws Exception {
		elementcontrol.setText(homepage.searchBox, "Rado watch");
		dropownControl.selectViaVisibleText(homepage.searchCategory, "Watches");
		elementcontrol.clickElement(homepage.searchButton);
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

}
