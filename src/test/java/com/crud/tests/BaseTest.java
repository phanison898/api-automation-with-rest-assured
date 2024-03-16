package com.crud.tests;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.base.Base;

public class BaseTest extends Base{

	@BeforeSuite
	public void beforeSuite() {

		baseURI = getApiUrl();
	}

	@AfterSuite()
	public void afterSuite() {

	}

}
