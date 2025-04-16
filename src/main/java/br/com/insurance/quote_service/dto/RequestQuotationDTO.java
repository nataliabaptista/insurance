package br.com.insurance.quote_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestQuotationDTO {

    private Integer insurancePolicyId;

    private Integer productId;

    private Integer offerId;

    private String category;

    private BigDecimal totalMonthlyPremiumAmount;

    private BigDecimal totalCoverageAmount;

    private CoverageDTO coverages;

    private AssistancesDTO assistances;

    private CustomersDTO customer;

}
