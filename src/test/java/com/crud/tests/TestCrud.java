package com.crud.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.config.Constants;
import com.google.gson.Gson;
import com.models.User;

import io.restassured.response.Response;

public class TestCrud extends BaseTest {

	@Test(priority = 1)
	public void getRequest() {

		// Valid user details already available in db
		User validUser = new User("ecb83418-e830-4448-8d8b-8caa7d05efa1", "phanison", "phani@gmail.com", 26, "male");

		Response rs=given()
			.param("id", validUser.id)
		.when()
			.get(Constants.USERS_ENDPOINT);
		rs.then()
			.statusCode(200)
			.body("[0].id", equalTo(validUser.id))
			.body("[0].username", equalTo(validUser.username))
			.body("[0].email", equalTo(validUser.email))
			.body("[0].age", equalTo(validUser.age))
			.body("[0].gender", equalTo(validUser.gender));

	}

	@Test(priority = 2)
	public void postRequest() {

		Gson gson = new Gson();

		User newUser = User.create();

		String jsonBody = gson.toJson(newUser);

		Response res = given().contentType("application/json").body(jsonBody).when().post(Constants.USERS_ENDPOINT);

		User createdUser = gson.fromJson(res.body().asString(), User.class);

		res.then().statusCode(201);

		Assert.assertEquals(createdUser.id, newUser.id);
		Assert.assertEquals(createdUser.username, newUser.username);
		Assert.assertEquals(createdUser.email, newUser.email);
		Assert.assertEquals(createdUser.age, newUser.age);
		Assert.assertEquals(createdUser.gender, newUser.gender);

	}

	@Test(priority = 3)
	public void putRequest() {

		Gson gson = new Gson();

		User user = User.create("a3fea2c6-b0e4-4c35-b9e7-b9de451e9b0b");

		Response res = given().body(gson.toJson(user)).put(Constants.USERS_ENDPOINT + "/{id}", user.id);

		res.then().statusCode(200);

		User updatedUser = gson.fromJson(res.body().asString(), User.class);

		Assert.assertEquals(updatedUser.id, user.id);
		Assert.assertEquals(updatedUser.username, user.username);
		Assert.assertEquals(updatedUser.email, user.email);
		Assert.assertEquals(updatedUser.age, user.age);
		Assert.assertEquals(updatedUser.gender, user.gender);

	}

	@Test(priority = 4)
	public void deleteRequest() {

		Random random = new Random();

		Gson gson = new Gson();

		// Get all available users
		Response res = given().get("/users");

		User[] users = gson.fromJson(res.body().asString(), User[].class);

		User userGoingToBeDeleted = users[random.nextInt(users.length)];

		// Delete
		res = given().delete(Constants.USERS_ENDPOINT + "/{id}", userGoingToBeDeleted.id);

		User deletedUser = gson.fromJson(res.body().asString(), User.class);

		Assert.assertEquals(deletedUser.id, userGoingToBeDeleted.id);
		Assert.assertEquals(deletedUser.username, userGoingToBeDeleted.username);
		Assert.assertEquals(deletedUser.email, userGoingToBeDeleted.email);
		Assert.assertEquals(deletedUser.age, userGoingToBeDeleted.age);
		Assert.assertEquals(deletedUser.gender, userGoingToBeDeleted.gender);

	}

}
