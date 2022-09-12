package com.br.pagamento.entity;

import com.br.pagamento.data.vo.ProdutoVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {
    private static final long serialVersionUID = -715288064277809672L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, length = 15)
    private Integer estoque;

    public static Produto create(ProdutoVO vo){
        return new ModelMapper().map(vo, Produto.class);
    }
}
