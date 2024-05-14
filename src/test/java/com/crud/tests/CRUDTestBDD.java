package com.crud.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.config.Constants;
import com.models.Model;
import com.models.User;
import com.utils.JsonUtil;

public class CRUDTestBDD extends BaseTest {

	@Test(priority = 1, enabled = true)
	public void createUserUsingPostRequest() {

		String jsonReq = JsonUtil.convertToJson(user);
		logger.createJsonCodeBlock("Request body which user sent", jsonReq);

		logger.info("Sending POST request ...");
		
		String jsonRes = 
			given()
				.contentType("application/json")
				.body(user)
			.when()
				.post(Constants.USERS_ENDPOINT)
			.then()
				.statusCode(201)
				.body("id", equalTo(user.getId()))
				.body("username", equalTo(user.getUsername()))
				.body("email", equalTo(user.getEmail()))
				.body("age", equalTo(user.getAge()))
				.body("gender", equalTo(user.getGender()))
			.extract()
				.body()
				.asPrettyString();
		
		
		logger.pass("Successfully sent POST request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);

	}

	@Test(priority = 2, enabled = true)
	public void readUserUsingGetRequest() {

		String jsonReq = JsonUtil.convertToJson(user);
		logger.createJsonCodeBlock("Known user details (this user is created in earlier POST request)", jsonReq);

		logger.info("Sending GET request where ID = [ " + user.getId() +" ] ...");
		
		String jsonRes = 
				given()
					.contentType("application/json")
					.param("id", user.getId())
				.when()
					.get(Constants.USERS_ENDPOINT)
				.then()
					.statusCode(200)
					.body("[0].id", equalTo(user.getId()))
					.body("[0].username", equalTo(user.getUsername()))
					.body("[0].email", equalTo(user.getEmail()))
					.body("[0].age", equalTo(user.getAge()))
					.body("[0].gender", equalTo(user.getGender()))
				.extract()
					.body()
					.asPrettyString();
			
		
		logger.pass("Successfully sent GET request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);

	}

	@Test(priority = 3, enabled = true)
	public void updateUserUsingPutRequest() {

		// Create a new user with different details but with same id
		User updatedUser = Model.updateUser(user);
		
		String userJson = JsonUtil.convertToJson(user);
		String updatedUserJson = JsonUtil.convertToJson(updatedUser);
		
		logger.createJsonCodeBlock("User details before sending PUT request : ", userJson);
		logger.createJsonCodeBlock("Modified User details as below for sending PUT request: ", updatedUserJson);
		
		logger.info("Sending PUT request where ID = [ " + user.getId() +" ] ...");

		// reseting user details to modified details
		user = updatedUser;
		
		String jsonRes = 
				given()
					.contentType("application/json")
					.body(updatedUser)
				.when()
					.put(Constants.USERS_ENDPOINT + "/{id}", user.getId())
				.then()
					.statusCode(200)
					.body("id", equalTo(user.getId()))
					.body("username", equalTo(user.getUsername()))
					.body("email", equalTo(user.getEmail()))
					.body("age", equalTo(user.getAge()))
					.body("gender", equalTo(user.getGender()))
				.extract()
					.body()
					.asPrettyString();
			
		logger.pass("Successfully sent PUT request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);
	}
	
	@Test(priority = 4, enabled = true)
	public void updateUserUsingPatchRequest() {
		

		String userJson = JsonUtil.convertToJson(user);
		logger.createJsonCodeBlock("User details before sending PATCH request : ", userJson);

		// Create a new user with different details but with same id
		Object[] object = Model.updateUsersRandomField(user);
		
		String fieldName = object[0].toString();
		Object filedValue = object[1];
		String jsonString = object[2].toString();
		
		logger.createJsonCodeBlock(String.format("Modified %s property as below", fieldName), jsonString);
		
		logger.info("Sending PATCH request where ID = [ " + user.getId() +" ] ...");
		
		switch (fieldName.toLowerCase()) {
		
			case "username":
				user.setUsername(filedValue.toString());
				break;
			case "email":
				user.setEmail(filedValue.toString());
				break;
			case "age":
				user.setAge((int)filedValue);
				break;
			case "gender":
				user.setGender(filedValue.toString());
				break;
				
		}
		
		String jsonRes = 
				given()
					.contentType("application/json")
					.body(jsonString)
				.when()
					.patch(Constants.USERS_ENDPOINT + "/{id}", user.getId())
				.then()
					.statusCode(200)
					.body("id", equalTo(user.getId()))
					.body("username", equalTo(user.getUsername()))
					.body("email", equalTo(user.getEmail()))
					.body("age", equalTo(user.getAge()))
					.body("gender", equalTo(user.getGender()))
				.extract()
					.body()
					.asPrettyString();
			
		logger.pass("Successfully sent PATCH request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);
	}

	@Test(priority = 5, enabled = true)
	public void deleteUserUsingDeleteRequest() {

		String jsonReq = JsonUtil.convertToJson(user);
		logger.createJsonCodeBlock("Request body which user sent", jsonReq);

		logger.info("Sending DELETE request where ID = [ " + user.getId() +" ] ...");
		
		String jsonRes = 
				given()
					.contentType("application/json")
					.param("id", user.getId())
				.when()
					.delete(Constants.USERS_ENDPOINT + "/{id}", user.getId())
				.then()
					.statusCode(200)
					.body("id", equalTo(user.getId()))
					.body("username", equalTo(user.getUsername()))
					.body("email", equalTo(user.getEmail()))
					.body("age", equalTo(user.getAge()))
					.body("gender", equalTo(user.getGender()))
				.extract()
					.body()
					.asPrettyString();
			
		
		logger.pass("Successfully sent DELETE request");
		logger.createJsonCodeBlock("Recieved Json response body = ", jsonRes);

	}

}
