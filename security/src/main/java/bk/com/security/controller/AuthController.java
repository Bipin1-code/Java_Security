package bk.com.security.controller;

import bk.com.security.model.LoginRequest;
import bk.com.security.model.LoginResponse;
import bk.com.security.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
        return LoginResponse.builder()
                  .accessToken(token)
                  .build();
    }
}
