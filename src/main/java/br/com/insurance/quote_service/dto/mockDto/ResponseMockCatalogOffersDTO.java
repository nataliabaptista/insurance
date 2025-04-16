package br.com.insurance.quote_service.dto.mockDto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMockCatalogOffersDTO {

    public String id;
    public String product_id;
    public String name;
    public String created_at;
    public Boolean active;
    public Object coverages;
    public List<String> assistances;
    public Object monthly_premium_amount;

}
