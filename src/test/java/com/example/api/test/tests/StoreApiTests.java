package com.example.api.test.tests;

import com.example.api.test.base.BaseRequest;
import com.example.api.test.requests.StoreApiRequests;
import org.testng.annotations.Test;
import java.io.IOException;

import static com.example.api.test.data.TestData.storeOrderQuantity;
import static com.example.api.test.data.TestData.storeOrderStatus;

public class StoreApiTests extends BaseRequest {

  final StoreApiRequests storeApiRequests = new StoreApiRequests();

  @Test
  public void checkPostStoreOrder() throws IOException {
    storeApiRequests.createStoreOrder();
    checkStatusCode(200);
    checkNotNullInDataName("id");
    checkNotNullInDataName("petId");
    checkIntValue("quantity", storeOrderQuantity);
    checkStringValue("status", storeOrderStatus);
    checkBooleanValue("complete");
  }
}
