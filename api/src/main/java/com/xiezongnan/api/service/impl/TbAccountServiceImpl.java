package com.xiezongnan.api.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.xiezongnan.api.entity.TbAccountEntity;
import com.xiezongnan.api.mapper.TbAccountMapper;
import com.xiezongnan.api.service.TbAccountService;
import org.springframework.stereotype.Service;

/**
 * 账号表 服务层实现。
 *
 * @author petrus
 * @since 2023-08-07
 */
@Service
public class TbAccountServiceImpl extends ServiceImpl<TbAccountMapper, TbAccountEntity> implements TbAccountService {

}
