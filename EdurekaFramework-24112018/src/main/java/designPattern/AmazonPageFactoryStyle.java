package designPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPageFactoryStyle {


	@CacheLookup
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	
	@CacheLookup
	@FindBy(id="searchDropdownBox")
	private WebElement searchCategory;
	
	@CacheLookup
	@FindBy(xpath="//input[@value='Go']")
	private WebElement searchButton;
	
	@FindBy(id="s-result-count")
	private WebElement searchResult;

	private ElementControl elementcontrol;
	private DropdownControl dropownControl;

	public AmazonPageFactoryStyle(WebDriver driver) {

		PageFactory.initElements(driver, this);
		
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
