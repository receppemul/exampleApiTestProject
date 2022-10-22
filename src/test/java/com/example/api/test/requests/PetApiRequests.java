package com.example.api.test.requests;

import com.example.api.test.base.BaseRequest;

import java.io.IOException;

import static com.example.api.test.constants.Constants.contentType;
import static com.example.api.test.constants.Constants.petJsonPath;
import static com.example.api.test.constants.Constants.petUrl;
import static io.restassured.RestAssured.given;

public class PetApiRequests extends BaseRequest {

  public void createPet() throws IOException {
    response = given()
        .contentType(contentType)
        .body(getJsonFile(petJsonPath))
        .when()
        .post(petUrl)
        .prettyPeek();
  }

  public void deletePet() throws IOException {
    createPet();
    int petId = response.getBody().jsonPath().getInt("id");

    response = given()
        .contentType(contentType)
        .when()
        .delete(petUrl + "/{petId}", petId)
        .prettyPeek();
  }
}
