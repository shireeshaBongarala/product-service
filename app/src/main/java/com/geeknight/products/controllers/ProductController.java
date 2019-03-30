package com.geeknight.products.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import com.geeknight.products.model.Product;
import com.geeknight.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) throws URISyntaxException {
    URI locationUri = new URI(String.format("/v1/products/%s", product.getUuid().toString()));
    product = productService.createProduct(product);

    return ResponseEntity.created(locationUri).body(product);
  }
}