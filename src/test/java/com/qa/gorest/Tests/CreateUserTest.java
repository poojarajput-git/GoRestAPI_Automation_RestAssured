package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import com.qa.gorest.CONSTANTS.APIConstants;
import com.qa.gorest.CONSTANTS.APIHttpStatus;
import com.qa.gorest.Utils.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.gorest.Pojo.UserPOJO;
import com.qa.gorest.Utils.StringUtils;

import java.util.Iterator;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {
	UserPOJO user_pojo;

	//this is for fetching data from DATA PROVIDER direct 2D object array
	@DataProvider
	public Object[][] getUserData(){
		return new Object[][]{
			{"Subodh","male","active"},
			{"Seema","female","inactive"},
			{"Madhuri","female","active"}
		};
	}

	//this is for fetching data from DATA PROVIDER using excel sheet
	@DataProvider
	public Object[][] getUserTestSheetData(){
		return ExcelUtils.getTestData(APIConstants.USER_SHEET_NAME);
	}

	@Test(dataProvider = "getUserTestSheetData")
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
