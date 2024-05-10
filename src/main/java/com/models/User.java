package com.models;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import com.github.javafaker.Faker;

public class User {

	public String id;
	public String username;
	public String email;
	public int age;
	public String gender;

	public User(String username, String email, int age, String gender) {
		this.username = username;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}

	public User(String id, String username, String email, int age, String gender) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}

	public static User create() {
		Faker faker = new Faker(new Locale("en-ZA"));
		Random rand = new Random();

		String id = UUID.randomUUID().toString();
		String username = faker.name().username();
		String email = username + "@gmail.com";
		int age = rand.nextInt(30);
		String gender = rand.nextBoolean() ? "male" : "female";

		return new User(id, username, email, age, gender);
	}
	
	public static User create(String id) {
		Faker faker = new Faker(new Locale("en-ZA"));
		Random rand = new Random();

		String username = faker.name().username();
		String email = username + "@gmail.com";
		int age = rand.nextInt(30);
		String gender = rand.nextBoolean() ? "male" : "female";

		return new User(id, username, email, age, gender);
	}

}
