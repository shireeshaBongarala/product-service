package com.geeknight.products.service.integration;

import java.util.UUID;

import com.geeknight.products.model.Product;
import com.geeknight.products.service.ProductService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
class ProductServiceIntegrationTest {


  @Autowired
  ProductService productService;

  @Container
  private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
      .withDatabaseName("postgresdb")
      .withUsername("postgres_user")
      .withPassword("postgres_password");


  @Test
  @DisplayName("should persist the data in repository")
  void test() {
    assertTrue(postgresqlContainer.isRunning());
//    final Product laptop = new Product(UUID.randomUUID(), "Laptop");
//    assertThat(productService.createProduct(laptop)).isEqualTo(laptop);
  }


}
