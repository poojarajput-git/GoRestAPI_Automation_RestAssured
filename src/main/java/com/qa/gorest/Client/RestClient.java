package com.qa.gorest.Client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.FrameworkExceptions.APIFrameworkExceptions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class RestClient {
    private RequestSpecBuilder specBuilder;
    private final Properties prop;
    private final String baseURI;

    public RestClient(Properties prop, String baseURI) {
        this.prop = prop;
        this.baseURI = baseURI;
        specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(baseURI);
    }

    private void setRequestContentType(RequestSpecBuilder builder, String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                builder.setContentType(ContentType.JSON);
                break;
            case "xml":
                builder.setContentType(ContentType.XML);
                break;
            case "text":
                builder.setContentType(ContentType.TEXT);
                break;
            default:
                throw new APIFrameworkExceptions("INVALID CONTENT TYPE");
        }
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseURI);

        if (headersMap != null) {
            builder.addHeaders(headersMap);
        }
        if (requestBody != null) {
            builder.setBody(requestBody);
        }
        setRequestContentType(builder, contentType);

        return builder.build();
    }

    private RequestSpecification createRequestSpecWithQuery(Map<String, String> headersMap, Map<String, String> queryParams) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseURI);

        if (headersMap != null) {
            builder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            builder.addQueryParams(queryParams);
        }

        return builder.build();
    }

    // HTTP Methods with includeAuth flag applied directly within methods

    public Response get(String serviceURL, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().get(serviceURL);
        }
        return given(requestSpec).when().get(serviceURL);
    }

    public Response getWithHeaders(String serviceURL, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).addHeaders(headersMap);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().get(serviceURL);
        }
        return given(requestSpec).when().get(serviceURL);
    }

    public Response getWithQuery(String serviceURL, Map<String, String> queryParams, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParams(queryParams);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().get(serviceURL);
        }
        return given(requestSpec).when().get(serviceURL);
    }

    public Response getWithQueryAndHeader(String serviceURL, Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).addHeaders(headersMap).addQueryParams(queryParams);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().get(serviceURL);
        }
        return given(requestSpec).when().get(serviceURL);
    }

    public Response post(String serviceURL, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).setBody(requestBody);
        setRequestContentType(requestSpecBuilder, contentType);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().post(serviceURL);
        }
        return given(requestSpec).when().post(serviceURL);
    }

    public Response postWithHeaders(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).setBody(requestBody).addHeaders(headersMap);
        setRequestContentType(requestSpecBuilder, contentType);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().post(serviceURL);
        }
        return given(requestSpec).when().post(serviceURL);
    }

    public Response put(String serviceURL, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).setBody(requestBody);
        setRequestContentType(requestSpecBuilder, contentType);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().put(serviceURL);
        }
        return given(requestSpec).when().put(serviceURL);
    }

    public Response putWithHeaders(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).setBody(requestBody).addHeaders(headersMap);
        setRequestContentType(requestSpecBuilder, contentType);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().put(serviceURL);
        }
        return given(requestSpec).when().put(serviceURL);
    }

    public Response patch(String serviceURL, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).setBody(requestBody);
        setRequestContentType(requestSpecBuilder, contentType);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().patch(serviceURL);
        }
        return given(requestSpec).when().patch(serviceURL);
    }

    public Response patchWithHeaders(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI).setBody(requestBody).addHeaders(headersMap);
        setRequestContentType(requestSpecBuilder, contentType);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().patch(serviceURL);
        }
        return given(requestSpec).when().patch(serviceURL);
    }

    public Response delete(String serviceURL, boolean includeAuth, boolean log) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseURI);
        if (includeAuth) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
        }
        RequestSpecification requestSpec = requestSpecBuilder.build();
        if (log) {
            return given(requestSpec).log().all().when().delete(serviceURL);
        }
        return given(requestSpec).when().delete(serviceURL);
    }

    /// Setup for OAuth 2 Token
   /* public String getAccessToken(String serviceURL, String grantType, String clientID, String clientSecret){
        RestAssured.baseURI = "https://gorest.co.in";
        String accessToken =
                given()
                        .contentType(ContentType.URLENC)
                        .formParam("grant_type",grantType)
                        .formParam("client_id",clientID)
                        .formParam("client_secret",clientSecret)
                        .when()
                        .post(serviceURL)
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("access_token");
        System.out.println("access token: " + accessToken);
        return accessToken;
    }*/



}
