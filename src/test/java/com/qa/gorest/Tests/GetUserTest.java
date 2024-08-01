package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest {

	@Test(enabled = true, priority = 2)
	public void getAllUsers() {
		restClient.get("/public/v2/users", true, true)
		          .then().log().all()
		             .assertThat()
		               .statusCode(200);
	}

	@Test(enabled = true,priority = 0)
	public void getUser() {
		restClient.get("/public/v2/users/7264335", true, true)
		          .then().log().all()
		             .assertThat().statusCode(200)
		                .and().body("id", equalTo(7264335));
	}


	@Test(enabled = false)
	public void getUserWithQueryParam() {
		Map<String, String> queryParams =  new HashMap<String, String>();
		queryParams.put("name", "Dharmraj");
		queryParams.put("status", "active");

		restClient.getWithQuery("/public/v2/users/", queryParams, true, true)
		          .then().log().all()
		             .assertThat().statusCode(200);
	}




}
