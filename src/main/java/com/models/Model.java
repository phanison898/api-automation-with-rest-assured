package com.models;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import com.github.javafaker.Faker;
import com.utils.JsonUtil;

public class Model {

	private static final int MIN_AGE = 18;

	private static final int MAX_AGE = 60;

	public static User createUser() {

		User user = new User();

		Faker faker = new Faker(new Locale("en_IN"));
		Random rand = new Random();

		String id = UUID.randomUUID().toString();
		String username = faker.name().username();
		String email = username + "@gmail.com";
		int age = rand.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE;
		String gender = rand.nextBoolean() ? "male" : "female";

		user.setId(id);
		user.setUsername(username);
		user.setEmail(email);
		user.setAge(age);
		user.setGender(gender);

		return user;
	}
	
	public static User createInvalidUser() {

		User user = new User();

		Faker faker = new Faker(new Locale("en_IN"));
		Random rand = new Random();

		String id = UUID.randomUUID().toString();
		String username = faker.name().username();
		String email = username + "@gmail.com";
		int age = rand.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE;
		String gender = rand.nextBoolean() ? "male" : "female";

		user.setId(id);
		user.setUsername(username);
		user.setEmail(email);
		user.setAge(age);
		user.setGender(gender);

		return user;
	}

	public static User updateUser(User user) {

		Faker faker = new Faker(new Locale("en_IN"));
		Random rand = new Random();

		String username = faker.name().username();
		String email = username + "@gmail.com";
		int age = rand.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE;
		String gender = rand.nextBoolean() ? "male" : "female";

		user.setUsername(username);
		user.setEmail(email);
		user.setAge(age);
		user.setGender(gender);

		return user;
	}

	public static Object[] updateUsersRandomField(User user) {

		Faker faker = new Faker(new Locale("en_IN"));
		Random rand = new Random();

		String username = faker.name().username();

		int randomUserFieldIndex = rand.nextInt(UserFieldType.LIST.length - 1) + 1; // it won't pick ID because ID is at

		String fieldName = UserFieldType.LIST[randomUserFieldIndex];
		String jsonString = "";
		Object fieldValue = null;

		switch (fieldName.toLowerCase()) {
		case "username":
			fieldValue = username;
			jsonString = JsonUtil.convertToJson(UserFieldType.USERNAME, fieldValue.toString());
			break;
		case "email":
			fieldValue = username + "@gmail.com";
			jsonString = JsonUtil.convertToJson(UserFieldType.EMAIL, fieldValue.toString());
			break;
		case "age":
			fieldValue = rand.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE;
			jsonString = JsonUtil.convertToJson(UserFieldType.AGE, (int) fieldValue);
			break;
		case "gender":
			fieldValue = rand.nextBoolean() ? "male" : "female";
			jsonString = JsonUtil.convertToJson(UserFieldType.GENDER, fieldValue.toString());
			break;
		}

		return new Object[] { fieldName, fieldValue, jsonString };
	}

}
