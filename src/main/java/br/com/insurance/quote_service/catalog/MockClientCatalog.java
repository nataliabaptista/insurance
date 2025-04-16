package br.com.insurance.quote_service.catalog;

import br.com.insurance.quote_service.dto.mockDto.ResponseMockCatalogOffersDTO;
import br.com.insurance.quote_service.dto.mockDto.ResponseMockCatalogProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MockClientCatalog {

    @Autowired
    private RestTemplate restTemplate;

    ResponseEntity<ResponseMockCatalogProductsDTO> responseBodyProducts;
    ResponseEntity<ResponseMockCatalogOffersDTO> responseBodyOffers;

    public ResponseMockCatalogProductsDTO getProducts(){
        final var uri = UriComponentsBuilder.fromHttpUrl("https://5f716713-bbeb-41b8-96be-81613f0c2283.mock.pstmn.io").path("/consult_products").encode().build().toUri();
        final var requestHeaders = new HttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        responseBodyProducts = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ResponseMockCatalogProductsDTO.class);

        return responseBodyProducts.getBody();
    }

    public ResponseMockCatalogOffersDTO getOffers(){
        final var uri = UriComponentsBuilder.fromHttpUrl("https://5f716713-bbeb-41b8-96be-81613f0c2283.mock.pstmn.io").path("/consult_offers").encode().build().toUri();
        final var requestHeaders = new HttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        responseBodyOffers = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ResponseMockCatalogOffersDTO.class);

        return responseBodyOffers.getBody();
    }
}
