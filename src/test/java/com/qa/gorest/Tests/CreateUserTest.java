package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import org.testng.annotations.Test;
import com.qa.gorest.Client.RestClient;
import com.qa.gorest.Pojo.UserPOJO;
import com.qa.gorest.Utils.StringUtils;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {
	UserPOJO user_pojo;

	@Test
	public void createUserAllTest() {
		//post
		user_pojo = new UserPOJO("Sachin Shukla", StringUtils.generateRandomEmailID(), "male", "active");
		Integer userID = restClient.post("/public/v2/users", "JSON", user_pojo, true, true)
		          .then().assertThat().statusCode(201)
		          .extract().path("id");
		System.out.println(userID);
		//get
		restClient.get("/public/v2/users/"+userID, true, true)
		          .then().assertThat().statusCode(200)
		          .assertThat().body("id", equalTo(userID));
	}



}
