package com.example.herokudemoconsumer.web.services;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;

import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.logging.Logger;

public class ConsumerServiceImpl implements ConsumerService {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ServletContext servletContext;

    public ConsumerServiceImpl(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void save(Source source, CommonMessageDTO commonMessageDTO) {
        logger.info("Add received date.");
        commonMessageDTO.setDateReceived(Calendar.getInstance().getTime());
        logger.info("Save message.");
        this.servletContext.setAttribute(source.toString(), commonMessageDTO);
    }

}
