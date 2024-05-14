package com.crud.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.models.Model;
import com.utils.JsonUtil;

public class CRUDTestNormal extends BaseTest {
	
	@Test(priority=1, description = "Verify create user functionality using POST request", enabled = true)
	public void verifyCreateUserFunctionalityUsingPostRequest() {
		
		logger.createJsonCodeBlock("Request body which user sent", JsonUtil.convertToJson(user));
		logger.info("Sending POST request ...");
		
		String jsonRes =
		given()
			.spec(getReqSpec())
			.body(user)
		.when()
			.post()
		.then()
			.spec(getResSpec(201))
		.extract()
			.body()
			.asPrettyString();
		
		logger.pass("Successfully sent POST request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);
	}
	
	@Test(priority=2, description = "Verify read user functionality using GET request", enabled = true)
	public void verifyReadUserFunctionalityUsingGetRequest() {
		

		logger.createJsonCodeBlock("Known user details (this user is created in earlier POST request)", JsonUtil.convertToJson(user));
		logger.info("Sending GET request where ID = [ " + user.getId() +" ] ...");

		String jsonRes =
		given()
			.spec(getReqSpec())
			.pathParam("id", user.getId())
		.when()
			.get("/{id}")
		.then()
			.spec(getResSpec(200))
		.extract()
			.body()
			.asPrettyString();
		
		logger.pass("Successfully sent GET request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);
	
	}
	
	@Test(priority=3, description = "Verify update functionality using PUT request", enabled = true)
	public void verifyUpdateFunctionalityUsingPutRequest() {
		
		logger.createJsonCodeBlock("User details before sending PUT request : ", JsonUtil.convertToJson(user));
		
		user = Model.updateUser(user);
		
		logger.createJsonCodeBlock("Modified User details as below for sending PUT request: ", JsonUtil.convertToJson(user));
		logger.info("Sending PUT request where ID = [ " + user.getId() +" ] ...");

		String jsonRes =
		given()
			.spec(getReqSpec())
			.pathParam("id", user.getId())
			.body(user)
		.when()
			.put("/{id}")
		.then()
			.spec(getResSpec(200))
		.extract()
			.body()
			.asPrettyString();
		
		logger.pass("Successfully sent PUT request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);
		
	}
	

	@Test(priority=4, description = "Verify delete functionality using DELETE request", enabled = true)
	public void verifyDeleteFunctionalityUsingDeleteRequest() {
		
		logger.createJsonCodeBlock("User details before sending DELETE request : ", JsonUtil.convertToJson(user));
		logger.info("Sending DELETE request where ID = [ " + user.getId() +" ] ...");

		String jsonRes =
		given()
			.spec(getReqSpec())
			.pathParam("id", user.getId())
		.when()
			.delete("/{id}")
		.then()
			.spec(getResSpec(200))
		.extract()
			.body()
			.asPrettyString();
		
		logger.pass("Successfully sent DELETE request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);
		
	}
}
