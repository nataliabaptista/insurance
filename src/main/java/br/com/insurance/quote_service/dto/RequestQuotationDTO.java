package br.com.insurance.quote_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
public class RequestQuotationDTO {

    private Integer insurancePolicyId;

    private Integer productId;

    private Integer offerId;

    private String category;

    private Double totalMonthlyPremiumAmount;

    private Double totalCoverageAmount;

    private LinkedHashMap<String, Double> coverages;

    private List<String> assistances;

    private CustomersDTO customer;

}
