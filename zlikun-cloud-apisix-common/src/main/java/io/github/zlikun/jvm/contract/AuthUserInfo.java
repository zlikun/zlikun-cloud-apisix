package io.github.zlikun.jvm.contract;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 认证用户实体
 */
@Data
@Accessors(chain = true)
public class AuthUserInfo {

    private Long id;
    private String username;
    private Integer status;
    private Date created;

}
