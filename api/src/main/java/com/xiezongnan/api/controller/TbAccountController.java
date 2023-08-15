package com.xiezongnan.api.controller;

import com.xiezongnan.api.entity.TbAccountEntity;
import com.xiezongnan.api.service.TbAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号表 控制层。
 *
 * @author petrus
 * @since 2023-08-07
 */
@RestController
@RequestMapping("/tbAccount")
public class TbAccountController {

    @Autowired
    private TbAccountService tbAccountService;

    /**
     * 根据账号表主键获取详细信息。
     *
     * @param id 账号表主键
     * @return 账号表详情
     */
    @GetMapping("getById/{id}")
    public TbAccountEntity getById(@PathVariable Integer id) {
        return tbAccountService.getById(id);
    }

}
