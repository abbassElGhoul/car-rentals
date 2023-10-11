package com.pc.global.car.renting.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver implements MessageListener
{

    public void onMessage(Message message)
    {
        System.out.println("Consuming Message - " + new String(message.getBody()));
    }
}
