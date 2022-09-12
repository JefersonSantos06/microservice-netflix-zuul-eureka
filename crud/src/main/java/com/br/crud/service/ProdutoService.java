package com.br.crud.service;

import com.br.crud.data.vo.ProdutoVO;
import com.br.crud.entity.Produto;
import com.br.crud.exception.ResourceNotFoundException;
import com.br.crud.message.ProdutoSendMessage;
import com.br.crud.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoSendMessage sendMessage;

    public ProdutoVO create(ProdutoVO produtoVO){
        ProdutoVO proVoRetorno = ProdutoVO.create(repository.save(Produto.create(produtoVO)));
        sendMessage.sendMessage(produtoVO);
        return proVoRetorno;
    }

    public Page<ProdutoVO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(this::convertToProdutoVO);
    }

    public ProdutoVO findById(Long id){
        return ProdutoVO.create(checkById(id));
    }

    public ProdutoVO update(ProdutoVO produtoVO){
        checkById(produtoVO.getId());
        ProdutoVO savedProdutoVO = ProdutoVO.create(repository.save(Produto.create(produtoVO)));
        sendMessage.sendMessage(savedProdutoVO);
        return savedProdutoVO;
    }

    public void delete(Long id){
        repository.delete(checkById(id));
    }

    private Produto checkById(Long id){
        return  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com o ID: " + id));
    }

    private ProdutoVO convertToProdutoVO(Produto produto){
        return ProdutoVO.create(produto);
    }
}
