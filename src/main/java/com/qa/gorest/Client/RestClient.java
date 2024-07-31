package com.qa.gorest.Client;

import java.util.Map;

import com.qa.gorest.FrameworkExceptions.APIFrameworkExceptions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    private static final String BASE_URI = "https://gorest.co.in";
    private static final String BEARER_TOKEN = "62ee3b876b7c7fb374f91daad3ae5a41ed38753972c196b129d433849a5864d5";

    private RequestSpecBuilder specBuilder;

    public RestClient() {
        specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(BASE_URI);
        // Set Authorization header once
        specBuilder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);
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
        builder.setBaseUri(BASE_URI);
        builder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);

        if (headersMap != null) {
            builder.addHeaders(headersMap);
        }
        if (requestBody != null) {
            builder.setBody(requestBody);
        }
        setRequestContentType(builder, contentType);

        return builder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(BASE_URI);
        builder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);

        if (headersMap != null) {
            builder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            builder.addQueryParams(queryParams);
        }

        return builder.build();
    }

    // HTTP Methods

    // GET Methods
    public Response get(String serviceURL, boolean log) {
        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(BASE_URI)
                                                                  .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                                                                  .build();
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .get(serviceURL);
        }
        return RestAssured.given(requestSpec).when().get(serviceURL);
    }

    public Response get(String serviceURL, Map<String, String> headersMap, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(headersMap, null);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .get(serviceURL);
        }
        return RestAssured.given(requestSpec).when().get(serviceURL);
    }

    public Response get(String serviceURL, Map<String, String> headersMap, Map<String, String> queryParams, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(headersMap, queryParams);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .get(serviceURL);
        }
        return RestAssured.given(requestSpec).when().get(serviceURL);
    }

    // POST Methods
    public Response post(String serviceURL, String contentType, Object requestBody, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(requestBody, contentType, null);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .post(serviceURL);
        }
        return RestAssured.given(requestSpec).when().post(serviceURL);
    }

    public Response post(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(requestBody, contentType, headersMap);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .post(serviceURL);
        }
        return RestAssured.given(requestSpec).when().post(serviceURL);
    }

    // PUT Methods
    public Response put(String serviceURL, String contentType, Object requestBody, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(requestBody, contentType, null);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .put(serviceURL);
        }
        return RestAssured.given(requestSpec).when().put(serviceURL);
    }

    public Response put(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(requestBody, contentType, headersMap);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .put(serviceURL);
        }
        return RestAssured.given(requestSpec).when().put(serviceURL);
    }

    // PATCH Methods
    public Response patch(String serviceURL, String contentType, Object requestBody, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(requestBody, contentType, null);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .patch(serviceURL);
        }
        return RestAssured.given(requestSpec).when().patch(serviceURL);
    }

    public Response patch(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap, boolean log) {
        RequestSpecification requestSpec = createRequestSpec(requestBody, contentType, headersMap);
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .patch(serviceURL);
        }
        return RestAssured.given(requestSpec).when().patch(serviceURL);
    }

    // DELETE Methods
    public Response delete(String serviceURL, boolean log) {
        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(BASE_URI)
                                                                  .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                                                                  .build();
        if (log) {
            return RestAssured.given(requestSpec).log().all()
                    .when()
                    .delete(serviceURL);
        }
        return RestAssured.given(requestSpec).when().delete(serviceURL);
    }
}
