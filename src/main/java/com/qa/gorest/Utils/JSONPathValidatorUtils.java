package com.qa.gorest.Utils;

import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.FrameworkExceptions.APIFrameworkExceptions;
import io.restassured.response.Response;
import com.jayway.jsonpath.JsonPath;

import java.util.List;
import java.util.Map;

public class JSONPathValidatorUtils {

    APIFrameworkExceptions exception;

    private String getJsonResponseAsString(Response response){
        return response.getBody().asString();
    }

    public Object readJSON(Response response, String jsonPath){
        String jsonResponse = getJsonResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        }catch(PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkExceptions(jsonPath+ ": Json Path not found");
        }
    }

    public List<Object> readJSONList(Response response, String jsonPath){
       String jsonResponse = getJsonResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        }catch(PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkExceptions(jsonPath+ ": Json Path not found");
        }
    }

    public List<Map<String, Object>> readJSONListOfMap(Response response, String jsonPath){
        String jsonResponse = getJsonResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        }catch(PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkExceptions(jsonPath+ ": Json Path not found");
        }
    }





}
