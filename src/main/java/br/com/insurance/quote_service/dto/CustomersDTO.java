package br.com.insurance.quote_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {

    private String document_number;

    private String name;

    private String type;

    private String gender;

    private String date_of_birth;

    private String email;

    private String phone_number;

}
