package com.geeknight.products.service;

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

}
