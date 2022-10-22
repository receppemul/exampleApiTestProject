package com.example.api.test.base;

import static com.example.api.test.constants.Constants.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CommonMethods {
  protected static Response response;
  protected static JSONObject json;
  final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
  final Random rand = new Random();
  final Set<String> identifiers = new HashSet<String>();

  protected JsonNode readJsonFile(String jsonPathName) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File from = new File(jsonPathName);
    return mapper.readTree(from);
  }

  public JsonNode getJsonFile(String fileName) throws IOException {
    return readJsonFile(requestPath + fileName);
  }

  public void generateJsonObject(String jsonPathName) throws IOException {
    URL file = Resources.getResource(jsonPathName);
    String jsonFile = Resources.toString(file, Charset.defaultCharset());
    json = new JSONObject(jsonFile);
  }

  public String generateRandomText() {
    StringBuilder builder = new StringBuilder();
    while (builder.toString().length() == 0) {
      int length = rand.nextInt(5) + 5;
      for (int i = 0; i < length; i++) {
        builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
      }
      if (identifiers.contains(builder.toString())) {
        builder = new StringBuilder();
      }
    }
    return builder.toString();
  }

  public void checkStatusCode(int statusCode) {
    assertEquals("Status code is not equal!", response.getStatusCode(), statusCode);
  }

  public void checkIntValue(String path, int value) {
    assertEquals("Int value is not equal!", response.getBody().jsonPath().getInt(path), value);
  }

  public void checkStringValue(String path, String value) {
    assertEquals("String value is not equal!", response.getBody().jsonPath().getString(path), value);
  }

  public void checkBooleanValue(String path) {
    assertTrue("Boolean value is not equal!", response.getBody().jsonPath().getBoolean(path));
  }

  public void checkJsonFile(String jsonFile) throws IOException {
    assertEquals("Jsonfile result is not equal!!", response.asString(), getJsonFile(jsonFile).toString());
  }

  public void checkNotNullInDataName(String inDataName) throws JSONException {
    assertNotNull("The value is null!", response.getBody().jsonPath().get(inDataName));
  }
}
