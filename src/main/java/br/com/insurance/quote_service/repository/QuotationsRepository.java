package br.com.insurance.quote_service.repository;

import br.com.insurance.quote_service.entity.Policies;
import br.com.insurance.quote_service.entity.Quotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationsRepository extends JpaRepository<Quotations, Integer> {


}


