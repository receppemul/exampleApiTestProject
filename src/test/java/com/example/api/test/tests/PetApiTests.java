package com.example.api.test.tests;

import com.example.api.test.base.BaseRequest;
import com.example.api.test.requests.PetApiRequests;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.example.api.test.constants.Constants.petJsonPath;
import static com.example.api.test.data.TestData.*;

public class PetApiTests extends BaseRequest {

  final PetApiRequests petApiRequests = new PetApiRequests();

  @Test
  public void checkPostPet() throws IOException {
    petApiRequests.createPet();
    checkStatusCode(200);
    checkNotNullInDataName("category");
    checkNotNullInDataName("tags");
    checkStringValue("name", petName);
    checkStringValue("status", petStatus);
    checkIntValue("id", petId);
    checkJsonFile(petJsonPath);
  }

  @Test
  public void checkDeletePet() throws IOException {
    petApiRequests.deletePet();
    checkStatusCode(200);
    checkStringValue("message", getJsonFile(petJsonPath).get("id").toString());
  }
}
