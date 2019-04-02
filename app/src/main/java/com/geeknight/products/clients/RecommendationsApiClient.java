package com.geeknight.products.clients;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class RecommendationsApiClient {

  private final RestTemplate restTemplate;

  List<UUID> getRecommendationsForTheProduct(UUID productUuid) {
    UriComponentsBuilder builder = UriComponentsBuilder
        .fromPath("/recommendations/{uuid}/")
        .queryParam("uuid", productUuid);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    headers.setAccept(asList(APPLICATION_JSON));

    ResponseEntity<UUID[]> recommendedProducts = restTemplate.exchange(
        builder.buildAndExpand().toUri(),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        UUID[].class);

    return asList(recommendedProducts.getBody());
  }

}
