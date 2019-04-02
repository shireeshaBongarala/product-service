package com.geeknight.products.controllers.contract;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(PactConsumerTestExt.class)
class ProductControllerTest {
  private static final String consumer = "orders";
  private static final String providerService = "products";

  @Pact(provider = providerService, consumer = consumer)
  public RequestResponsePact getRecommendationsForProduct(PactDslWithProvider builder) {
    final DslPart recommendedProducts = new PactDslJsonArray()
        .array()
        .integerType()
        .closeArray();

    final Map<String, String> headers = new HashMap<String, String>() {
      {
        put("x-authenticated-userid", "32:abcd");
        put("Content-Type", "application/json");
      }
    };

    return builder
        .given("recommendations for product exist")
        .uponReceiving("a request for recommendation")
        .path("/recommendations/b1bdc023-17cf-4a61-9a15-ea669943c212")
        .headers(headers)
        .method("GET")
        .willRespondWith()
        .status(200)
        .body(recommendedProducts)
        .toPact();
  }

  @Test
  @PactTestFor(pactVersion = PactSpecVersion.V2, pactMethod = "getRecommendationsForProduct")
  public void shouldGetAvailableConsultantForMember(MockServer mockServer) throws URISyntaxException, IOException {
    URIBuilder uriBuilder = new URIBuilder(mockServer.getUrl())
        .setPath("/recommendations/b1bdc023-17cf-4a61-9a15-ea669943c212");
    final HttpResponse httpResponse = Request.Get(uriBuilder.toString())
        .addHeader("x-authenticated-userid", "32:abcd")
        .addHeader("Content-Type", "application/json")
        .execute().returnResponse();
    assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
  }


}