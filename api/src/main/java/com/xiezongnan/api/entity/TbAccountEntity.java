package com.xiezongnan.api.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账号表 实体类。
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
@Table(value = "tb_account", schema = "petrus_study_plus")
public class TbAccountEntity implements Serializable {
    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
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
