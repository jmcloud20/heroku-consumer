package com.example.herokudemoconsumer.web.services;

import com.example.herokudemoconsumer.web.model.CommonMessageDTO;
import com.example.herokudemoconsumer.web.model.Source;

import java.util.List;

public interface ConsumerService {
    public void save(Source source, CommonMessageDTO commonMessageDTO);
    public List<CommonMessageDTO> retrieveSavedMessage(Source source);
}
