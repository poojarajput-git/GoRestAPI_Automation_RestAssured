package com.qa.gorest.Tests;

import org.testng.annotations.Test;

import com.qa.gorest.Client.RestClient;
import com.qa.gorest.Pojo.UserPOJO;
import com.qa.gorest.Utils.StringUtils;

import static org.hamcrest.Matchers.*;

public class CreateUserTest {
	
	RestClient restClient = new RestClient();
	
	UserPOJO user_pojo;
	
	@Test
	public void allUserTest() {
	
		//post 
		user_pojo = new UserPOJO("Dharmraj Yudhister", StringUtils.generateRandomEmailID(), "male", "active");
		
		Integer userID = restClient.post("/public/v2/users", "JSON", user_pojo, true)
		          .then().assertThat().statusCode(201)
		          .extract().path("id");
		
		System.out.println(userID);
		
		//get
		restClient.get("/public/v2/users/"+userID, true)
		          .then().assertThat().statusCode(200)
		          .assertThat().body("id", equalTo(userID));
		          
		          
		          
	}

}
