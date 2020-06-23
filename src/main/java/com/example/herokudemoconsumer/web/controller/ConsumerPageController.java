package com.example.herokudemoconsumer.web.controller;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;
import com.example.herokudemoconsumer.web.services.ConsumerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/page")
public class ConsumerPageController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ConsumerService consumerService;

    public ConsumerPageController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/update_email")
    public ResponseEntity<CommonMessageDTO> getUpdateEmail(){
        logger.info("Get latest customer update email message.");
        return new ResponseEntity<CommonMessageDTO>(this.consumerService.retrieveSavedMessage(Source.UPDATE_EMAIL), HttpStatus.OK);
    }

    @GetMapping("/customer_opt")
    public ResponseEntity<CommonMessageDTO> getCustomerOpt(){
        logger.info("Get latest customer opt message.");
        return new ResponseEntity<CommonMessageDTO>(this.consumerService.retrieveSavedMessage(Source.OPT_IN), HttpStatus.OK);
    }

    @GetMapping("product_offer")
    public ResponseEntity<CommonMessageDTO> getProductOffer(){
        logger.info("Get latest product offer message.");
        return new ResponseEntity<CommonMessageDTO>(this.consumerService.retrieveSavedMessage(Source.PROD_OFFER), HttpStatus.OK);
    }
}
