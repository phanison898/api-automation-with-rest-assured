package com.crud.tests;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.base.Base;
import com.config.Config;
import com.models.Model;
import com.models.User;
import com.utils.LoggerUtil;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest extends Base {

	public static LoggerUtil logger = new LoggerUtil();
	public User user;

	@BeforeSuite
	public void beforeSuite() {

		baseURI = getApiUrl();
	}

	@BeforeClass
	public void beforeClass() {

		user = Model.createUser();
	}

	@AfterSuite()
	public void afterSuite() {

	}

	public RequestSpecification getReqSpec() {

		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri(Config.getApiUrl()).setBasePath("/users")
				.addHeader("Content-Type", "application/json").build();

		return reqSpec;
	}

	public ResponseSpecification getResSpec(int statusCode) {

		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectContentType(ContentType.JSON).expectBody("id", equalTo(user.getId()))
				.expectBody("username", equalTo(user.getUsername())).expectBody("email", equalTo(user.getEmail()))
				.expectBody("age", equalTo(user.getAge())).expectBody("gender", equalTo(user.getGender())).build();

		return resSpec;
	}

}
