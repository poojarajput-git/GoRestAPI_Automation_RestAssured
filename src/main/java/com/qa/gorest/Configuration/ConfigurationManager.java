package com.qa.gorest.Configuration;

import java.io.*;
import java.util.*;
public class ConfigurationManager {

    private Properties prop;
    private FileInputStream fis;

    public Properties initProp() {
        prop = new Properties();
        try {
            fis = new FileInputStream("/Users/poojarajput/Desktop/POOJA GIT PROJECTS/repo/Go_Rest_API_Automation_Framework/src/test/resources/Config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

}
