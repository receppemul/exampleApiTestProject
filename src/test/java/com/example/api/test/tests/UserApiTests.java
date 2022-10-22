package com.example.api.test.tests;

import com.example.api.test.base.BaseRequest;
import com.example.api.test.requests.UserApiRequests;
import org.testng.annotations.Test;

import static com.example.api.test.constants.Constants.userJsonPath;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;

import static com.example.api.test.data.TestData.*;

public class UserApiTests extends BaseRequest {
  final UserApiRequests userApiRequests = new UserApiRequests();

  @Test
  public void checkPostUser() throws IOException {
    userApiRequests.createUser();
    checkStatusCode(200);
    checkNotNullInDataName("message");
  }

  @Test
  public void checkGetUserLogin() {
    userApiRequests.getUserLogin(username, password);
    checkStatusCode(200);
    checkNotNullInDataName("message");
    assertTrue(response.getBody().jsonPath().getString("message").contains(userLoginResultMessage));
  }

  @Test
  public void checkPutUser() throws IOException {
    userApiRequests.updateUser();
    checkStatusCode(200);
    checkStringValue("message", getJsonFile(userJsonPath).get("id").toString());
  }
}
