package com.geeknight.products.service;

import java.util.UUID;

import com.geeknight.products.model.Product;
import com.geeknight.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoSettings;

@MockitoSettings
public class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductService productService;


  @BeforeEach
  void setUp () {
    productService = new ProductService(productRepository);
  }

  @Test
  @DisplayName("should save the product")
  public void test() {
    //When
    final Product product = new Product(UUID.randomUUID(), "A merchant's guide to galaxy");
    productService.createProduct(product);

    //Then
    verify(productRepository, times(1)).save(product);
  }

}