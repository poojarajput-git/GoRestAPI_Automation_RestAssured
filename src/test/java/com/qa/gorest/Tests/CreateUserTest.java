package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import com.qa.gorest.CONSTANTS.APIHttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.gorest.Pojo.UserPOJO;
import com.qa.gorest.Utils.StringUtils;

import java.util.Iterator;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {
	UserPOJO user_pojo;

	@DataProvider
	public Object[][] getUserData(){
		return new Object[][]{
			{"Subodh","male","active"},
			{"Sema","female","inactive"},
			{"Madhuri","female","active"}
		};

	}

	@Test(dataProvider = "getUserData")
	public void createUserAllTest(String name, String gender, String status) {
		//post
		user_pojo =
				new UserPOJO(name, StringUtils.generateRandomEmailID(), gender, status);
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
