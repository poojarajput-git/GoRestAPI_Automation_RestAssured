package com.qa.gorest.Tests;

import com.qa.gorest.Base.BaseTest;
import org.testng.annotations.Test;

public class CircuitsTest extends BaseTest {

    @Test
    public void getCircuitData() {
        restClient.get("/api/f1/2017/circuits.json", true)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
