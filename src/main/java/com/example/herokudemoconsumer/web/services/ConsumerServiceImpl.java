package com.example.herokudemoconsumer.web.services;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
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

        String formattedDate = formatHKTDate();

        logger.info("Add received date.");
        commonMessageDTO.setDateReceived(formattedDate);

        List<CommonMessageDTO> messages = validateMessage(source, commonMessageDTO);

        logger.info("Save message.");
        this.servletContext.setAttribute(source.toString(), messages);
    }

    private String formatHKTDate() {
        logger.info("Set format for date.");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz YYYY");

        logger.info("Set timezone to Asia/Hong Kong");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Hong_Kong"));

        logger.info("Generate formatted date.");
        return sdf.format(Calendar.getInstance().getTime());
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
