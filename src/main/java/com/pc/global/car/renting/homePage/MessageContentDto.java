package com.pc.global.car.renting.homePage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageContentDto
{
    private String msisdn;
    private String message;
    private String productType;
    private String customerTransactionId;
    private String attachmentId;
    private String conversationId;
    private Long campaignId;


}
