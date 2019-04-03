package com.geeknight.products.repository;

import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import com.geeknight.products.model.Product;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
  private final SimpleJdbcInsert jdbcInsert;
  private DataSource dataSource;

  public ProductRepository(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("products")
        .usingColumns("uuid", "name", "category_uuid");
  }

  public Product save(Product product) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
        .addValue("uuid", product.getUuid())
        .addValue("name", product.getName());

     jdbcInsert.execute(sqlParameterSource);
     return product;
  }

  public List<Product> findAll(List<UUID> productsUuids) {
      return null;
  }
}
