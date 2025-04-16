package br.com.insurance.quote_service.dto.mockDto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMockCatalogProductsDTO {

    public String id;
    public String name;
    public String created_at;
    public Boolean active;
    public List<String> offers;

}
