package br.com.insurance.quote_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CUSTOMERS")
public class Customers {

    @Id
    @Column(name = "document_number", nullable=false)
    public String documentNumber;

    @Column(name = "name")
    public String name;

    @Column(name = "customer_type")
    public String customerType;

    @Column(name = "gender")
    public String gender;

    @Column(name = "date_of_birth")
    public LocalDate dateOfBirth;

    @Column(name = "email")
    public String email;

    @Column(name = "phone_number")
    public String phoneNumber;

}
