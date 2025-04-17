package br.com.insurance.quote_service.controller;

import br.com.insurance.quote_service.dto.RequestQuotationDTO;

import br.com.insurance.quote_service.entity.Coverages;
import br.com.insurance.quote_service.entity.Offers;
import br.com.insurance.quote_service.entity.Products;
import br.com.insurance.quote_service.entity.Quotations;
import br.com.insurance.quote_service.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("quotation/api/v1/")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;






    @PostMapping("/sendQuotation")
    public ResponseEntity<Quotations> validateQuotation(@RequestBody RequestQuotationDTO requestQuotationDTO) throws Exception {
        //quotationService.validateOffers();

        Optional<Offers> optionalOffer = quotationService.findOffer(requestQuotationDTO.getOfferId());

        Optional<Products> optionalProduct = quotationService.findProduct(requestQuotationDTO.getProductId());

        if (optionalOffer.isPresent() && optionalProduct.isPresent()){

            Offers selectedOffer = optionalOffer.get();
            Products selectedProduct = optionalProduct.get();

            if (selectedOffer.active && selectedProduct.active){

                quotationService.validateQuotationAndSave(selectedOffer, selectedProduct, requestQuotationDTO);

            } else {
                throw new Exception("Oferta ou Produto não estão ativos. Favor verificar os dados enviados.");
            }

        } else {

            throw new Exception("Oferta ou Produto não existem. Favor verificar os dados enviados.");


        }


        return ResponseEntity.ok().body(new Quotations());
    }


    @GetMapping("/findQuotations")
    public ResponseEntity<RequestQuotationDTO> findQuotationsByClient() {
        System.out.println("A");

        return ResponseEntity.ok().body(new RequestQuotationDTO());

    }

    @GetMapping("/findCoverages")
    public ResponseEntity<List<Coverages>> findAllCoverages(){


        List<Coverages> coverages = quotationService.findAllCoverages();

        return ResponseEntity.ok().body(coverages);

    }


}
