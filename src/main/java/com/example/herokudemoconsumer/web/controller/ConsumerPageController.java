package com.example.herokudemoconsumer.web.controller;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;
import com.example.herokudemoconsumer.web.services.ConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/page")
public class ConsumerPageController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ConsumerService consumerService;

    public ConsumerPageController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/monitor")
    public String viewMonitorPage(Model model){
        logger.info("Get all objects needed by page.");
        List<CommonMessageDTO> updateEmailTransactions = this.consumerService.retrieveSavedMessage(Source.UPDATE_EMAIL);
        List<CommonMessageDTO> customerOptTransactions = this.consumerService.retrieveSavedMessage(Source.OPT_IN);
        List<CommonMessageDTO> productOffer = this.consumerService.retrieveSavedMessage(Source.PROD_OFFER);

        logger.info("Add all objects needed for display inside model.");
        model.addAttribute(Source.UPDATE_EMAIL.toString(), updateEmailTransactions);
        model.addAttribute(Source.OPT_IN.toString(), customerOptTransactions);
        model.addAttribute(Source.PROD_OFFER.toString(), productOffer);

        return "monitor";
    }

    @GetMapping("/update_email")
    public ResponseEntity<List<CommonMessageDTO>> getUpdateEmail(){
        logger.info("Get latest customer update email message.");
        return new ResponseEntity<List<CommonMessageDTO>>(this.consumerService.retrieveSavedMessage(Source.UPDATE_EMAIL), HttpStatus.OK);
    }

    @GetMapping("/customer_opt")
    public ResponseEntity<List<CommonMessageDTO>> getCustomerOpt(){
        logger.info("Get latest customer opt message.");
        return new ResponseEntity<List<CommonMessageDTO>>(this.consumerService.retrieveSavedMessage(Source.OPT_IN), HttpStatus.OK);
    }

    @GetMapping("product_offer")
    public ResponseEntity<List<CommonMessageDTO>> getProductOffer(){
        logger.info("Get latest product offer message.");
        return new ResponseEntity<List<CommonMessageDTO>>(this.consumerService.retrieveSavedMessage(Source.PROD_OFFER), HttpStatus.OK);
    }
}
