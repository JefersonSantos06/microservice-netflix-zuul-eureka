package com.br.pagamento.services;

import com.br.pagamento.data.vo.VendaVO;
import com.br.pagamento.entity.ProdutoVenda;
import com.br.pagamento.entity.Venda;
import com.br.pagamento.exception.ResourceNotFoundException;
import com.br.pagamento.repository.ProdutoRepository;
import com.br.pagamento.repository.ProdutoVendaRepository;
import com.br.pagamento.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {


    private final VendaRepository repository;
    private final ProdutoVendaRepository produtoVendaRepository;

    public VendaVO create(VendaVO vendaVO) {
        Venda venda = repository.save(Venda.create(vendaVO));

        List<ProdutoVenda> produtosSalvos =  new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);

        return VendaVO.create(venda);
    }


    public Page<VendaVO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(this::convertToVendaVO);
    }

    public VendaVO findById(Long id){
        return VendaVO.create(checkById(id));
    }

    public VendaVO update(VendaVO vo){
        checkById(vo.getId());
        return VendaVO.create(repository.save(Venda.create(vo)));
    }

    public void delete(Long id){
        repository.delete(checkById(id));
    }

    private Venda checkById(Long id){
        return  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda nao encontrado com o ID: " + id));
    }

    private VendaVO convertToVendaVO(Venda entity){
        return VendaVO.create(entity);
    }
    
}
