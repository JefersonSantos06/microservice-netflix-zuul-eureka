package com.br.crud.message;

import com.br.crud.data.vo.ProdutoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProdutoSendMessage {

    @Value("${crud.rabbitmq.exchange}")
    private String exchange;

    @Value("${crud.rabbitmq.routingkey}")
    private String routingkey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProdutoSendMessage(RabbitTemplate template){
        this.rabbitTemplate = template;
    }

    @RabbitListener()
    public void sendMessage(ProdutoVO produtoVO){
        rabbitTemplate.convertAndSend(exchange, routingkey, produtoVO);
    }
}
