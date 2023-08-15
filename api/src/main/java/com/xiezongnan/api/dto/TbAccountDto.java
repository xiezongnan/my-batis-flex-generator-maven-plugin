package com.xiezongnan.api.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账号表 Dto类。
 *
 * @author petrus
 * @since 2023-08-07
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class TbAccountDto implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String account;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 生日
     */
    private LocalDateTime birthday;

}
