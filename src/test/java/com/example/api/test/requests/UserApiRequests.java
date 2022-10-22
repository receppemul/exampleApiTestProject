package com.example.api.test.requests;

import com.example.api.test.base.BaseRequest;

import java.io.IOException;
import java.util.UUID;

import static com.example.api.test.constants.Constants.contentType;
import static com.example.api.test.constants.Constants.userJsonPath;
import static com.example.api.test.constants.Constants.userUrl;
import static io.restassured.RestAssured.given;

public class UserApiRequests extends BaseRequest {

  public void createUser() throws IOException {
    response = given()
        .contentType(contentType)
        .body(getJsonFile(userJsonPath))
        .when()
        .post(userUrl)
        .prettyPeek();
  }

  public void getUserLogin(String username, String password) {
    response = given()
        .contentType(contentType)
        .queryParam("username", username)
        .queryParam("password", password)
        .when()
        .get(userUrl + "/login")
        .prettyPeek();
  }

  public void updateUser() throws IOException {
    generateJsonObject(userJsonPath);
    String username = "TestAutomationUser" + generateRandomText();

    json.put("username", username);
    json.put("firstName", "Firstname" + generateRandomText());
    json.put("lastName", "Lastname" + generateRandomText());
    json.put("userStatus", 2);

    response = given()
        .contentType(contentType)
        .body(json.toString())
        .when()
        .put(userUrl + "/{username}", username)
        .prettyPeek();
  }
}
