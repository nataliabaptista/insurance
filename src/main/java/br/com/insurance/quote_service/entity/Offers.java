package br.com.insurance.quote_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_offers")
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id", nullable=false)
    public Integer offerId;

    @Column(name = "name")
    public String name;

    @Column(name = "created_at")
    public String createdAt;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "active")
    public Boolean active;

    @Column(name = "product_id")
    public Integer productId;

    @Column(name = "coverage_id")
    public Integer coverageId;

    @Column(name = "coverage_id2")
    public Integer coverageId2;

    @Column(name = "coverage_id3")
    public Integer coverageId3;

    @Column(name = "coverage_id4")
    public Integer coverageId4;

    @Column(name = "assistance_id")
    public Integer assistanceId;

    @Column(name = "assistance_id2")
    public Integer assistanceId2;

    @Column(name = "amount_id")
    public Integer amountId;

}
