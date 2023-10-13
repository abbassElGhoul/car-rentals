package com.pc.global.car.renting.homePage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsResponse
{
    private String message_id;
    private String conversation_id;
    private String message_text;
    private String to;
    private int sms_count;
    private String submit_status;
    private LocalDateTime created_date;
    private String customer_transaction_id;
    private Long campaignId;
}

