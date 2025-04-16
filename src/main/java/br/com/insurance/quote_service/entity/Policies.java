package br.com.insurance.quote_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_POLICIES")
public class Policies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_policy_id", nullable=false)
    public Integer insurancePolicyId;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "quotation_id")
    public Integer quotationId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "quotation_id", insertable = false, updatable = false)
    public Quotations quotations;

}


