package com.qa.gorest.Base;

import com.qa.gorest.Client.RestClient;
import com.qa.gorest.Configuration.ConfigurationManager;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    protected ConfigurationManager config;
    protected Properties prop;
    protected RestClient restClient;

	@BeforeTest
    public void setUp(){
        config  = new ConfigurationManager();
        prop = config.initProp();
        String baseURI = prop.getProperty("baseURI");
        restClient = new RestClient(prop, baseURI);
    }


}
