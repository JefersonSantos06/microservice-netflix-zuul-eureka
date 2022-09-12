package com.br.auth.controller;

import com.br.auth.auth.jwt.JwtTokenProvider;
import com.br.auth.data.vo.UserVO;
import com.br.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> login(@RequestBody UserVO userVO){
        var username = userVO.getUsername();
        var password = userVO.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var user = userService.findByUserName(username);
        var token = "";

        if (user != null){
            token = jwtTokenProvider.createToken(username, user.getRoles());
        }else {
            throw new UsernameNotFoundException("User name not found");
        }

        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return ok(model);
    }

}
