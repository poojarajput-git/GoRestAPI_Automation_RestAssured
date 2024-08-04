package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import com.qa.gorest.CONSTANTS.APIHttpStatus;
import com.qa.gorest.Utils.JSONPathValidatorUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CircuitsTest extends BaseTest {


    //code smell :  SonarQube
    @Test
    public void getCircuitData() {
       Response circuitResponse =  restClient.get(CIRCUIT_ENDPOINT, false, true);
        int s_code = circuitResponse.statusCode();
        Assert.assertEquals(s_code,APIHttpStatus.OK_200.getCode());

        JSONPathValidatorUtils json_validator =  new JSONPathValidatorUtils();
        List<Object> country = json_validator.readJSONList(circuitResponse, "$.MRData.CircuitTable.Circuits" +
                "[?(@.circuitId=='albert_park')].Location.country");
        System.out.println(country);
        Assert.assertEquals(country.get(0),"Australia");

    }
}
