package br.com.insurance.quote_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable=false)
    public Integer productId;

    @Column(name = "name")
    public String name;

    @Column(name = "created_at")
    public String createdAt;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "active")
    public Boolean active;

}
