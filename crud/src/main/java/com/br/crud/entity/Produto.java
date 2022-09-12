package com.br.crud.entity;

import com.br.crud.data.vo.ProdutoVO;
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
    private static final long serialVersionUID = 9128971700053839317L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 15)
    private Integer estoque;

    @Column(nullable = false, length = 15)
    private Double preco;

    public static Produto create(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
