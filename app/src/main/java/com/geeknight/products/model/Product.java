package com.geeknight.products.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {

  @JsonProperty("uuid")
  private UUID uuid;

  @JsonProperty("name")
  private String name;

  @JsonProperty("category_uuid")
  private UUID  categoryUuid;

  public Product(UUID uuid, String name, UUID categoryUuid) {
    this.uuid = uuid;
    this.name = name;
    this.categoryUuid = categoryUuid;
  }
}
