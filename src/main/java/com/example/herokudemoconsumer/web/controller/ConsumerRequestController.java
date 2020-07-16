package com.example.herokudemoconsumer.web.controller;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;
import com.example.herokudemoconsumer.web.services.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/consumer")
@Slf4j
public class ConsumerRequestController {
    private ConsumerService consumerService;
    private long transportTime;

    public ConsumerRequestController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/cust_update_email")
    public void customerUpdateConsumer(@RequestBody CommonMessageDTO commonMessageDTO) {
        log.info("Received by customer update endpoint.");
        this.computeTransportTime(commonMessageDTO.getMessage().getPublishTime());
        consumerService.save(Source.UPDATE_EMAIL, commonMessageDTO);
        log.info("Saved to servlet context.");
    }
    @PostMapping("/CUST_optOut_optIn")
    public void customerOpt(@RequestBody CommonMessageDTO commonMessageDTO) {
        log.info("Received by customer opt endpoint.");
        this.computeTransportTime(commonMessageDTO.getMessage().getPublishTime());
        consumerService.save(Source.OPT_IN, commonMessageDTO);
        log.info("Saved to servlet context.");
    }

    @PostMapping("/prod_offer")
    public void productOffer(@RequestBody CommonMessageDTO commonMessageDTO) {
        log.info("Received by product offer endpoint.");
        this.computeTransportTime(commonMessageDTO.getMessage().getPublishTime());
        consumerService.save(Source.PROD_OFFER, commonMessageDTO);
        log.info("Saved to servlet context.");
    }

    private void computeTransportTime(long publishTime) {
        if (publishTime == 0) {
            log.info("Publish time is not set.");
            return;
        }
        this.transportTime = new Date().getTime() - publishTime;
        log.info("Time to transport message: " + transportTime + " milliseconds.");
    }
}
