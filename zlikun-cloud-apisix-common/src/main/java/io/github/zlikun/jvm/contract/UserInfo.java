package io.github.zlikun.jvm.contract;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {

    private Long id;
    private String nickname;

}
