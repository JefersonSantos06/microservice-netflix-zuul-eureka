package com.br.pagamento.message;

import com.br.pagamento.data.vo.ProdutoVO;
import com.br.pagamento.entity.Produto;
import com.br.pagamento.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoReceiveMessage {

    private final ProdutoRepository repository;

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoVO produtoVO){
        repository.save(Produto.create(produtoVO));
    }

}
