package com.xiezongnan.api.mapstruct;


import com.xiezongnan.api.dto.TbAccountDto;
import com.xiezongnan.api.entity.TbAccountEntity;
import com.xiezongnan.api.vo.TbAccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 账号表 转换类。
 *
 * @author petrus
 * @since 2023-08-07
 */

@Mapper
public interface TbAccountConverter {

    TbAccountConverter INSTANCE = Mappers.getMapper(TbAccountConverter.class);

    TbAccountEntity dtoToEntity(TbAccountDto tbAccountDto);

    TbAccountDto entityToDto(TbAccountEntity tbAccountEntity);

    List<TbAccountDto> entityListToDtoList(List<TbAccountEntity> tbAccountEntityList);

    List<TbAccountEntity> dtoListToEntityList(List<TbAccountDto> tbAccountDtoList);

    TbAccountVo dtoToVo(TbAccountDto tbAccountDto);

    TbAccountDto voToDto(TbAccountVo tbAccountVo);

    List<TbAccountVo> dtoListToVoList(List<TbAccountDto> tbAccountDtoList);

    List<TbAccountDto> voListToDtoList(List<TbAccountVo> tbAccountVoList);

}
