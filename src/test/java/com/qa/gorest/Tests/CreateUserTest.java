package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import com.qa.gorest.CONSTANTS.APIHttpStatus;
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
		user_pojo =
				new UserPOJO("Arjun KOHLI", StringUtils.generateRandomEmailID(), "male", "active");
		Integer userID = restClient.post(GOREST_ENDPOINT, "JSON", user_pojo, true, true)
		          .then().assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
		          .extract().path("id");
		System.out.println(userID);
		//get
		restClient.get(GOREST_ENDPOINT+"/"+userID, true, true)
		          .then().assertThat().statusCode(APIHttpStatus.OK_200.getCode())
		          .assertThat().body("id", equalTo(userID));
	}



}
