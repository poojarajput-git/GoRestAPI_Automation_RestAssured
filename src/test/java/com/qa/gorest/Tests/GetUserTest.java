package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import com.qa.gorest.CONSTANTS.APIHttpStatus;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest {

	@Test(enabled = true, priority = 3)
	public void getAllUsers() {
		restClient.get(GOREST_ENDPOINT, true, true)
		          .then().log().all()
		             .assertThat()
		               .statusCode(APIHttpStatus.OK_200.getCode());
	}

	/*@Test(enabled = true,priority = 2)
	public void getUser() {
		restClient.get(GOREST_ENDPOINT+"/"+"7264335", true, true)
		          .then().log().all()
		             .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
		                .and().body("id", equalTo(7264335));
	}*/

	@Test(enabled = true,priority = 1)
	public void getUserWithQueryParam() {
		Map<String, String> queryParams =  new HashMap<String, String>();
		queryParams.put("name", "Dharmraj");
		queryParams.put("status", "active");

		restClient.getWithQuery(GOREST_ENDPOINT, queryParams, true, true)
		          .then().log().all()
		             .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}


}
