package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import com.qa.gorest.CONSTANTS.APIHttpStatus;
import org.testng.annotations.Test;

public class CircuitsTest extends BaseTest {


    //code smell :  SonarQube
    @Test
    public void getCircuitData() {
        restClient.get(CIRCUIT_ENDPOINT, false, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
}
