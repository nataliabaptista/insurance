package br.com.insurance.quote_service.repository;

import br.com.insurance.quote_service.entity.Assistances;
import br.com.insurance.quote_service.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistancesRepository extends JpaRepository<Assistances, Integer> {


}
