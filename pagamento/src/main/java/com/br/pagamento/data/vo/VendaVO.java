package com.br.pagamento.data.vo;

import com.br.pagamento.entity.ProdutoVenda;
import com.br.pagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {
    private static final long serialVersionUID = 677463480019162422L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
    private Date data;

    @JsonProperty("produtos")
    private List<ProdutoVendaVO> produtos;

    @JsonProperty("valorTotal")
    private Double valorTotal;

    public static VendaVO create(Venda entity){
        return new ModelMapper().map(entity, VendaVO.class);
    }
}
