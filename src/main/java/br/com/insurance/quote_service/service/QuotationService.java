package br.com.insurance.quote_service.service;

import br.com.insurance.quote_service.catalog.MockClientCatalog;
import br.com.insurance.quote_service.dto.CoverageDTO;
import br.com.insurance.quote_service.dto.RequestQuotationDTO;
import br.com.insurance.quote_service.dto.mockDto.ResponseMockCatalogOffersDTO;
import br.com.insurance.quote_service.dto.mockDto.ResponseMockCatalogProductsDTO;
import br.com.insurance.quote_service.entity.Assistances;
import br.com.insurance.quote_service.entity.Coverages;
import br.com.insurance.quote_service.entity.Offers;
import br.com.insurance.quote_service.entity.Products;
import br.com.insurance.quote_service.repository.AssistancesRepository;
import br.com.insurance.quote_service.repository.CoveragesRepository;
import br.com.insurance.quote_service.repository.OffersRepository;
import br.com.insurance.quote_service.repository.ProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    AssistancesRepository assistancesRepository;

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

    public Boolean validateQuotationAndSave(Offers offer, Products product, RequestQuotationDTO quotation) throws Exception {

        List<String> assistancesByQuotation = quotation.getAssistances();

        Optional<Assistances> offerAssistance1 = assistancesRepository.findById(offer.getAssistanceId());
        Optional<Assistances> offerAssistance2 = Objects.nonNull(offer.getAssistanceId2()) ? assistancesRepository.findById(offer.getAssistanceId2()) : Optional.empty();

        List<Assistances> allOfferAssistances = new ArrayList<>(0);

        allOfferAssistances.add(offerAssistance1.isPresent() ? offerAssistance1.get() : null);
        allOfferAssistances.add(offerAssistance2.isPresent() ? offerAssistance2.get() : null);

        allOfferAssistances.removeIf(Objects::isNull);

        List<Boolean> finalList = allOfferAssistances.stream().map(assist -> assist.getType().contains(assistancesByQuotation.get(0))).collect(Collectors.toList());

        if (!finalList.isEmpty()){
            //TESTAR ISSO
            List<CoverageDTO> listRequestCoverages = buildCoverageDTOFromRequest(quotation.getCoverages());

            List<Coverages> coveragesFromBase = coveragesRepository.findAll();

            if (isTheCoverageOfThisOfferInTheBaseAndTheValuesAreOk(coveragesFromBase, quotation.getCoverages())){

                //PEGAR O MOCK DA OFFERTA E VALIDAR SE O VALOR INFORMADO ESTÁ ENTRE O MAXIMO E O MINIMO

                List<Double> coverageAmounts = listRequestCoverages.stream().map(cov -> cov.getAmount()).collect(Collectors.toList());

                Optional<Double> totalCoverageAmount = coverageAmounts.stream().reduce(Double::sum);

                if (totalCoverageAmount.isPresent() && totalCoverageAmount.get().compareTo(quotation.getTotalCoverageAmount())==0){

                    //ENTRANDO, SALVAR CLIENTE E COTAÇÃO -criar atualização para cotação tbm
                }

            } else {

                throw new Exception("Verifique as coberturas informadas.");

            }



        } else {

            throw new Exception("Verifique as assistencias informadas.");
        }

        return true;
    }

    private List<CoverageDTO> buildCoverageDTOFromRequest(HashMap<String, Double> coverages){
        List<CoverageDTO> listRequestCoverages = new ArrayList(0);

        for (String key : coverages.keySet()) {
            CoverageDTO cov = new CoverageDTO();
            cov.setType(key);
            cov.setAmount(coverages.get(key));
            listRequestCoverages.add(cov);
        }
        return listRequestCoverages;
    }

    Boolean isTheAssistancePresent(Integer idAssistanceBase, Integer idsAssistanceOffer){
        return idsAssistanceOffer==idAssistanceBase;
    }



    public Boolean validateOffers() {
        ResponseMockCatalogOffersDTO mockOffers = getOffers();

        List<Coverages> listCoverages = coveragesRepository.findAll();

        return (isThatOfferActive(mockOffers) &&
                isTheCoverageOfThisOfferInTheBaseAndTheValuesAreOk(listCoverages, (LinkedHashMap<String, Double>) mockOffers.getCoverages()));

    }



    public List<Coverages> findAllCoverages(){
        return coveragesRepository.findAll();
    }

    public List<Assistances> findAllAssistances(){
        return assistancesRepository.findAll();
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
       // CoverageDTO coveragesFromRequest =  request.getCoverages();

        List<Coverages> confirmedCoverages = new ArrayList<>(0);

    //    confirmedCoverages.addAll(coveragesBase.stream().filter(coverage -> coverage.getType().equals(coveragesFromRequest)).collect(Collectors.toList()));

        return !confirmedCoverages.isEmpty();
    }

    public Boolean isTheValuesOfRequestCovarageOk(List<CoverageDTO> coverages, List<Coverages> coveragesFromBase){

        Boolean s = Boolean.TRUE;
        List<Boolean> finalCoverages = new ArrayList<>(0);
        for (Coverages coverage : coveragesFromBase){
            finalCoverages.addAll(coverages.stream().filter(cov -> cov.getType()
                    .equals(coverage.getType())).map(covReq -> covReq.getAmount().compareTo(coverage.getAmount())<0).collect(Collectors.toList()));
        }
        return s;
    }


    public Boolean isTheCoverageOfThisOfferInTheBaseAndTheValuesAreOk(List<Coverages> coveragesBase, LinkedHashMap<String, Double> coveragesFromOffer) {
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
