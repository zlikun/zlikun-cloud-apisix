package io.github.zlikun.jvm.contract;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class OrderInfo {

    private String orderNo;
    private Long userId;
    private String nickname;
    private Date createTime;

}
