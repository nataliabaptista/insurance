package br.com.insurance.quote_service.service;

import br.com.insurance.quote_service.catalog.MockClientCatalog;
import br.com.insurance.quote_service.dto.CoverageDTO;
import br.com.insurance.quote_service.dto.RequestQuotationDTO;
import br.com.insurance.quote_service.dto.mockDto.ResponseMockCatalogOffersDTO;
import br.com.insurance.quote_service.dto.mockDto.ResponseMockCatalogProductsDTO;
import br.com.insurance.quote_service.entity.Coverages;
import br.com.insurance.quote_service.entity.Offers;
import br.com.insurance.quote_service.entity.Products;
import br.com.insurance.quote_service.repository.CoveragesRepository;
import br.com.insurance.quote_service.repository.OffersRepository;
import br.com.insurance.quote_service.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuotationService {

    @Autowired
    CoveragesRepository coveragesRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OffersRepository offersRepository;

    @Autowired
    private MockClientCatalog mockClientCatalog;

    public Optional<Offers> findOffer(Integer offerId){
        return offersRepository.findById(offerId);
    }

    public Optional<Products> findProduct(Integer productId){
        return productsRepository.findById(productId);
    }




    public Boolean validateOffers() {
        ResponseMockCatalogOffersDTO mockOffers = getOffers();

        List<Coverages> listCoverages = coveragesRepository.findAll();

        return (isThatOfferActive(mockOffers) &&
                isTheCoverageOfThisOfferInTheBaseAndTheValuesAreOk(listCoverages, mockOffers));

    }



    public List<Coverages> findAllCoverages(){
        return coveragesRepository.findAll();
    }

    @Cacheable
    public ResponseMockCatalogProductsDTO getProducts(){
        return mockClientCatalog.getProducts();
    }

    @Cacheable
    public ResponseMockCatalogOffersDTO getOffers(){
        return mockClientCatalog.getOffers();
    }


    public Boolean isThatOfferActive(ResponseMockCatalogOffersDTO offer){
        return offer.active;
    }

    public Boolean isTheCoverageOfQuotationInTheBaseAndTheValuesAreOk(List<Coverages> coveragesBase, RequestQuotationDTO request) {
        CoverageDTO coveragesFromRequest =  request.getCoverages();

        List<Coverages> confirmedCoverages = new ArrayList<>(0);

        confirmedCoverages.addAll(coveragesBase.stream().filter(coverage -> coverage.getType().equals(coveragesFromRequest)).collect(Collectors.toList()));

        return !confirmedCoverages.isEmpty();
    }

    public Boolean isTheValuesOfRequestCovarageOk(CoverageDTO requestCoverage, List<Coverages> coveragesFromBase){
        Boolean s = Boolean.TRUE;
        for (Coverages coverage : coveragesFromBase){
            s = coveragesFromBase.stream().filter(cov -> cov.getType()
                    .equals(requestCoverage.getType())).map(cov -> cov.getAmount().compareTo(requestCoverage.getAmount())<0);
        }
        return s;
    }


    public Boolean isTheCoverageOfThisOfferInTheBaseAndTheValuesAreOk(List<Coverages> coveragesBase, ResponseMockCatalogOffersDTO coveragesOffer) {
        LinkedHashMap<String, Double> coveragesFromOffer = (LinkedHashMap<String, Double>) coveragesOffer.getCoverages();
        Set keysOfOffer = coveragesFromOffer.keySet();

        List<Coverages> confirmedCoverages = new ArrayList<>(0);

        for (Object key : keysOfOffer) {
            confirmedCoverages.addAll(coveragesBase.stream().filter(coverage -> coverage.getType().equals(key.toString())).collect(Collectors.toList()));
        }

        return !confirmedCoverages.isEmpty() && isTheValuesOk(coveragesFromOffer, confirmedCoverages);
    }

    public Boolean isTheValuesOk(LinkedHashMap<String, Double> coveragesFromOffer, List<Coverages> coveragesFromBase){
        Boolean s = Boolean.TRUE;
        for (Coverages coverage : coveragesFromBase){
            s = coveragesFromOffer.get(coverage.getType()).compareTo(coverage.getAmount())<0;
        }
        return s;
    }
}
