package com.qa.gorest.Base;

import com.qa.gorest.Client.RestClient;
import com.qa.gorest.Configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    //Service URLs for different APIs
    public static final String GOREST_ENDPOINT= "/public/v2/users";
    public static final String CIRCUIT_ENDPOINT= "/api/f1/2017/circuits.json";

    protected ConfigurationManager config;
    protected Properties prop;
    protected RestClient restClient;

    @Parameters({"baseURI"})
	@BeforeTest
    public void setUp(String baseURI){

        // RestAssured.filters(new AllureRestAssured()); //Allure Report

        config  = new ConfigurationManager();
        prop = config.initProp();
        //String baseURI = prop.getProperty("baseURI");
        restClient = new RestClient(prop, baseURI);
    }


}
