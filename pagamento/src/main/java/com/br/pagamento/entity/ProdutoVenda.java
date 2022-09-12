package com.br.pagamento.entity;

import com.br.pagamento.data.vo.ProdutoVO;
import com.br.pagamento.data.vo.ProdutoVendaVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto_venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdutoVenda implements Serializable {
    private static final long serialVersionUID = 9195916440984917359L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 15)
    private Long idProduto;

    @Column(nullable = false, length = 15)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id")
    private Venda venda;

    public static ProdutoVenda create(ProdutoVendaVO vo){
        return new ModelMapper().map(vo, ProdutoVenda.class);
    }
}

