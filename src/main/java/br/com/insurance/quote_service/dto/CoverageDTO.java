package br.com.insurance.quote_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CoverageDTO {

    private Integer coverage_id;

    private String type;

    private Double amount;


}
