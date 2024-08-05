package com.qa.gorest.Configuration;

import com.qa.gorest.FrameworkExceptions.APIFrameworkExceptions;

import java.io.*;
import java.util.*;
public class ConfigurationManager {
    private Properties prop;
    private FileInputStream fis;
    public Properties initProp() {
        prop =  new Properties();
        // this is for cmd line argument
        //mvn clean install -Denv='qa'
        String envName = System.getProperty("env");

        if(envName == null){
            System.out.println("No environment given, Hence running on QA environment");
            try {
                fis = new FileInputStream("./src/test/resources/Config/qa.config.properties");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Running test on environment : "+envName);
            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                        fis = new FileInputStream("./src/test/resources/Config/qa.config.properties");
                        break;
                    case "dev":
                        fis = new FileInputStream("./src/test/resources/Config/dev.config.properties");
                        break;
                    case "stage":
                        fis = new FileInputStream("./src/test/resources/Config/stage.config.properties");
                        break;
                    case "prod":
                        fis = new FileInputStream("./src/test/resources/Config/config.properties");
                        break;
                    default:
                        System.out.println("Please pass the right environment");
                        throw new APIFrameworkExceptions("WRONG ENVIRONMENT IS GIVEN");
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }


}
