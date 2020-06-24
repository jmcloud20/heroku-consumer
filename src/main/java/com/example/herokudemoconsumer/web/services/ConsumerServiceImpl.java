package com.example.herokudemoconsumer.web.services;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ServletContext servletContext;

    public ConsumerServiceImpl(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     *
     * @param source
     * @param commonMessageDTO
     */
    @Override
    public void save(Source source, CommonMessageDTO commonMessageDTO) {
        logger.info("Add received date.");
        commonMessageDTO.setDateReceived(Calendar.getInstance().getTime());

        List<CommonMessageDTO> messages = validateMessage(source, commonMessageDTO);

        logger.info("Save message.");
        this.servletContext.setAttribute(source.toString(), messages);
    }

    /**
     *
     * @param source
     * @param commonMessageDTO
     * @return
     */
    private List<CommonMessageDTO> validateMessage(Source source, CommonMessageDTO commonMessageDTO) {
        final int limit = 5;

        logger.info("Check if there is an existing list in context.");
        List<CommonMessageDTO> messages = (List<CommonMessageDTO>)this.servletContext.getAttribute(source.toString());
        messages = messages == null ? new LinkedList<CommonMessageDTO>() : messages;

        logger.info("Makes sure that size does not exceed limit.");
        if(messages.size() < 5)
            messages.add(commonMessageDTO);
        else{
            messages.remove(0);
            messages.add(commonMessageDTO);
        }

        return messages;
    }

    @Override
    public List<CommonMessageDTO> retrieveSavedMessage(Source source) {
        logger.info("Retrieving data for source: "+ source.toString());
        return (List<CommonMessageDTO>) this.servletContext.getAttribute(source.toString());
    }

}
