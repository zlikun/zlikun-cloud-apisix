package io.github.zlikun.jvm.controller;

import io.github.zlikun.jvm.contract.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
public class UserController {

    @RequestMapping(path = "/api/v1/user/details")
    public UserInfo details(HttpServletRequest request,
                            long userId,
                            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) throws IOException {
        log.info("Query User: {}, token: {}", userId, token);
        return new UserInfo().setId(userId).setNickname("游客-" + userId);
    }

}
