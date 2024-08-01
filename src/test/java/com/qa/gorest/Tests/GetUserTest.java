package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest {

	@Test
	public void getAllUsers() {
		restClient.get("/public/v2/users", true)
		          .then().log().all()
		             .assertThat()
		               .statusCode(200);
	}

	@Test
	public void getUser() {
		restClient.get("/public/v2/users/7264335", true)
		          .then().log().all()
		             .assertThat().statusCode(200)
		                .and().body("id", equalTo(7264335));
	}


	@Test
	public void getUserWithQueryParam() {
		Map<String, String> queryParams =  new HashMap<String, String>();
		queryParams.put("name", "Dharmraj");
		queryParams.put("status", "active");

		restClient.get("/public/v2/users/", null, queryParams, true)
		          .then().log().all()
		             .assertThat().statusCode(200);
	}




}
