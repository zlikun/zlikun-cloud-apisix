package io.github.zlikun.jvm.controller;

import io.github.zlikun.jvm.contract.AuthUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@RestController
public class AuthController {

    @RequestMapping("/api/v2/auth")
    public AuthUserInfo auth(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {

        log.info("{} {}, token: {}", request.getMethod(), request.getRequestURI(), token);

        // Mock Response

        // 认证失败
        if (token == null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setHeader(HttpHeaders.LOCATION, "http://dev.zlikun.local:9080/login");
            return null;
        }

        // 认证成功
        final AuthUserInfo user = new AuthUserInfo().setId(128L).setStatus(1).setUsername("somebody").setCreated(new Date());
        response.setHeader("X-User-Id", String.valueOf(user.getId()));
        return user;
    }

}
