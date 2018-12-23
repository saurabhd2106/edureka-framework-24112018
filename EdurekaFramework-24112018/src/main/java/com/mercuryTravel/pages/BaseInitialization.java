package com.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;
import commonLibs.implementation.MouseControl;

public class BaseInitialization {

	ElementControl elementControl;
	DropdownControl dropdownControl;
	MouseControl mouseControl;

	public BaseInitialization(WebDriver driver) {
		elementControl = new ElementControl();
		dropdownControl = new DropdownControl();
		mouseControl = new MouseControl(driver);
	}

}
