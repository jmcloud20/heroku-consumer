package com.example.herokudemoconsumer.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonMessageDTO {
    private String topic;
    private String desc;
    private MessageDTO message;
    private Date dateReceived;
}
