package com.example.api.test.requests;

import com.example.api.test.base.BaseRequest;

import java.io.IOException;

import static com.example.api.test.constants.Constants.contentType;
import static com.example.api.test.constants.Constants.storeOrderJsonPath;
import static com.example.api.test.constants.Constants.storeOrderUrl;
import static com.example.api.test.data.TestData.storeOrderQuantity;
import static io.restassured.RestAssured.given;

public class StoreApiRequests extends BaseRequest {

  public void createStoreOrder() throws IOException {
    generateJsonObject(storeOrderJsonPath);
    json.put("quantity", storeOrderQuantity);

    response = given()
        .contentType(contentType)
        .body(json.toString())
        .when()
        .post(storeOrderUrl)
        .prettyPeek();
  }
}
