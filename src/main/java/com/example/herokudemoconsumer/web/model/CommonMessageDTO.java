package com.example.herokudemoconsumer.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonMessageDTO {
    private String topic;
    private String desc;
    private MessageDTO message;
    private String dateReceived;
}
