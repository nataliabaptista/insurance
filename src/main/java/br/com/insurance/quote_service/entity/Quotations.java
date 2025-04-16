package br.com.insurance.quote_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "TB_QUOTATIONS")
public class Quotations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id", nullable=false)
    public Integer quotation_id;

    @Column(name = "category")
    public String category;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "updated_at")
    public LocalDate updatedAt;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "total_monthly_premium_amount")
    public BigDecimal totalMonthlyPremiumAmount;

    @Column(name = "total_coverage_amount")
    public BigDecimal totalCoverageAmount;

    @Column(name = "coverage_id")
    public Integer coverageId;

    @Column(name =  "assistance_id")
    public Integer assistanceId;

    @Column(name = "document_number")
    public String documentNumber;
/*
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "document_number", insertable = false, updatable = false)
    private*/



}
