package com.mercuryTravel.utils;

import org.testng.annotations.Test;

public class TestDataProvider {

	@Test(dataProvider="getData", dataProviderClass=DataProviderUtils.class)
	public void printTestData(String username, String password, String empId){
		System.out.println("Username : "+ username);
		System.out.println("Password : "+ password);
		System.out.println("Employee Id : "+ password);
	}

}
