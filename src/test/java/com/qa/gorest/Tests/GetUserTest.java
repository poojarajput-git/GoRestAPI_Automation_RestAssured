package com.qa.gorest.Tests;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import com.qa.gorest.Client.RestClient;

public class GetUserTest {
	
	RestClient restClient;
	
	@Test
	public void getAllUsers() {
		restClient = new RestClient();
		restClient.get("/public/v2/users", true)
		          .then().log().all()
		             .assertThat()
		               .statusCode(200);
	}
	
	
	@Test
	public void getUser() {
		restClient = new RestClient();
		restClient.get("/public/v2/users/7253028", true)
		          .then().log().all()
		             .assertThat().statusCode(200)
		                .and().body("id", equalTo(7253028));
	}
	

	@Test
	public void getUserWithQueryParam() {
		restClient = new RestClient();
		
		Map<String, String> queryParams =  new HashMap<String, String>();
		queryParams.put("name", "Draupadi");
		queryParams.put("status", "active");
		
		restClient.get("/public/v2/users/", null, queryParams, true)
		          .then().log().all()
		             .assertThat().statusCode(200);
	}
	
	
	

}
