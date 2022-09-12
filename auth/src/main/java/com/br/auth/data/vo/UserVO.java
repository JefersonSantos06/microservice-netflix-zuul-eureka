package com.br.auth.data.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO implements Serializable {
    private static final long serialVersionUID = 9036819729936471008L;

    private String username;
    private String password;

}
