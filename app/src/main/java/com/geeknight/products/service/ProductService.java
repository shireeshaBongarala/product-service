package com.geeknight.products.service;

import java.util.List;
import java.util.UUID;

import com.geeknight.products.clients.RecommendationsApiClient;
import com.geeknight.products.model.Product;
import com.geeknight.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final RecommendationsApiClient apiClient;

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> fetchRecommendedProductListForProduct(UUID productUuid) {
    List<UUID> productsUuids = apiClient.getRecommendationsForTheProduct(productUuid);
    return productRepository.findAll(productsUuids);
  }

  public Product fetchProductByUuid(UUID productUUId) {
    return new Product(productUUId, "some name", UUID.randomUUID());
  }
}
