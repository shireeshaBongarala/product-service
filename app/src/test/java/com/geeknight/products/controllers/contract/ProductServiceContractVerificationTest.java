package com.geeknight.products.controllers.contract;

import java.util.UUID;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.geeknight.products.model.Product;
import com.geeknight.products.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Provider("products")
@PactFolder("pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8080")
@ExtendWith(SpringExtension.class)
class ProductServiceContractVerificationTest {

  @BeforeEach
  void setupTestTarget(PactVerificationContext context) {
    context.setTarget((new HttpTestTarget("localhost", 8080)));
  }

  @MockBean
  ProductService productService;

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }


  @State("the product with given uuid exists")
  void shouldReturnOkayForTheGivenUuid() {
    when(productService.fetchProductByUuid(UUID.fromString("b1bdc023-17cf-4a61-9a15-ea669943c212")))
        .thenReturn(new Product(UUID.fromString("b1bdc023-17cf-4a61-9a15-ea669943c212"),
            "A merchant's guide to galaxy",
            UUID.fromString("b1bdc023-17cf-4a61-9a15-ea669943c212")));
  }

}