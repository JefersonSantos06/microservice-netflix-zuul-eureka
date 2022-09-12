package com.br.pagamento.data.vo;

import com.br.pagamento.entity.Produto;
import com.br.pagamento.entity.ProdutoVenda;
import com.br.pagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id","estoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {
    private static final long serialVersionUID = 2381588818700865098L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduto")
    private Long idProduto;

    @JsonProperty("quantidade")
    private Integer quantidade;

    public static ProdutoVendaVO create(ProdutoVenda produtoVenda) {
        return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
    }
}
