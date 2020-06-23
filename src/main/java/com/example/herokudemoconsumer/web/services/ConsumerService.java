package com.example.herokudemoconsumer.web.services;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;

public interface ConsumerService {
    public void save(Source source, CommonMessageDTO commonMessageDTO);
    public CommonMessageDTO retrieveSavedMessage(Source source);
}
