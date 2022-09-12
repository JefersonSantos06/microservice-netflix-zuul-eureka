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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"id", "estoque"})
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {
    private static final long serialVersionUID = -6565084102877341175L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("estoque")
    private Integer estoque;

    public static ProdutoVO create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoVO.class);
    }
}
