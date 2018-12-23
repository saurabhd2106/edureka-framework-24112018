package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPOM2Style {

	private WebElement searchBox;
	private WebElement searchCategory;
	private WebElement searchButton;
	private WebElement searchResult;

	private ElementControl elementcontrol;
	private DropdownControl dropownControl;

	public AmazonPOM2Style(WebDriver driver) {

		searchBox = driver.findElement(By.id("twotabsearchtextbox"));

		searchCategory = driver.findElement(By.id("searchDropdownBox"));

		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));

		searchResult = driver.findElement(By.id("s-result-count"));
		
		elementcontrol = new ElementControl();
		dropownControl = new DropdownControl();

	}

	public void searchProduct(String product, String category) throws Exception {
	
		elementcontrol.setText(searchBox, "Rado watch");
		dropownControl.selectViaVisibleText(searchCategory, "Watches");
		elementcontrol.clickElement(searchButton);

		System.out.println(elementcontrol.getText(searchResult));
	}

}
