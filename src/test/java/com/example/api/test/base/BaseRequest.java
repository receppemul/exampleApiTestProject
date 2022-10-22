package com.example.api.test.base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.example.api.test.constants.Constants.*;

public class BaseRequest extends CommonMethods {

  @BeforeTest
  public void setUp() {
    init();
  }

  public void init() {
    RestAssured.baseURI = apiUrl;
  }

  @AfterTest
  public void tearDown() {
    System.out.println("Test completed!");
  }
}
