package com.br.pagamento.entity;

import com.br.pagamento.data.vo.VendaVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda implements Serializable {
    private static final long serialVersionUID = 4403809922654395044L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyy")
    @Column(nullable = false)
    private Date data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ProdutoVenda> produtos;

    @Column(nullable = false, length = 15)
    private Double valorTotal;

    public static Venda create(VendaVO vo){
        return new ModelMapper().map(vo, Venda.class);
    }
}
