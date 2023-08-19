package io.github.zlikun.jvm.controller;

import io.github.zlikun.jvm.contract.OrderInfo;
import io.github.zlikun.jvm.contract.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

import static io.github.zlikun.jvm.constant.HeaderConstants.TRACE_HEADER_NAME;


@Slf4j
@RestController
public class OrderController {

    @Autowired
    private RestTemplate template;

    @RequestMapping(path = "/api/v1/order/details")
    public OrderInfo details(HttpServletRequest request,
                             String orderNo,
                             @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) throws IOException {
        log.info("Query Order: {}, token: {}", orderNo, token);

        // Mock Logic
        final UserInfo user = this.findUserInfo(128L);
        return new OrderInfo()
                .setOrderNo(orderNo)
                .setUserId(user.getId())
                .setNickname(user.getNickname())
                .setCreateTime(new Date());
    }

    private UserInfo findUserInfo(long userId) {
        final HttpHeaders headers = new HttpHeaders();
        final String xRequestId = MDC.get(TRACE_HEADER_NAME);
        if (xRequestId != null) {
            headers.set(TRACE_HEADER_NAME, xRequestId);
        }

        final String url = "http://zlikun-cloud-apisix-user/api/v1/user/details?userId=" + userId;
        final ResponseEntity<UserInfo> response = template.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), UserInfo.class);
        return response.getBody();
    }

}
