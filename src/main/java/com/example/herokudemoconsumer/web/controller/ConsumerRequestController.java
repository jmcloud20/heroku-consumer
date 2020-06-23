package com.example.herokudemoconsumer.web.controller;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;
import com.example.herokudemoconsumer.web.services.ConsumerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerRequestController {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ConsumerService consumerService;

    public ConsumerRequestController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/cust_update_email")
    public void customerUpdateConsumer(@RequestBody CommonMessageDTO commonMessageDTO){
        logger.info("Received by customer update endpoint.");
        consumerService.save(Source.UPDATE_EMAIL, commonMessageDTO);
        logger.info("Saved to servlet context.");
    }
    @PostMapping("/CUST_optOut_optIn")
    public void customerOpt(@RequestBody CommonMessageDTO commonMessageDTO){
        logger.info("Received by customer opt endpoint.");
        consumerService.save(Source.OPT_IN, commonMessageDTO);
        logger.info("Saved to servlet context.");
    }
    @PostMapping("/prod_offer")
    public void productOffer(@RequestBody CommonMessageDTO commonMessageDTO){
        logger.info("Received by product offer endpoint.");
        consumerService.save(Source.PROD_OFFER, commonMessageDTO);
        logger.info("Saved to servlet context.");
    }
}
