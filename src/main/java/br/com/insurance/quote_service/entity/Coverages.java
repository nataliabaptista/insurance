package br.com.insurance.quote_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_COVERAGES")
public class Coverages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coverage_id", nullable=false)
    public Integer coverageId;

    @Column(name = "type")
    public String type;

    @Column(name = "amount")
    public Double amount;

}
