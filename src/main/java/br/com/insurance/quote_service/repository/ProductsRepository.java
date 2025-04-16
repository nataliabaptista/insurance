package br.com.insurance.quote_service.repository;

import br.com.insurance.quote_service.entity.Coverages;
import br.com.insurance.quote_service.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {


}
