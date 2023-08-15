package #(packageConfig.mapstructPackage);

#set(dtoGenerateEnable = globalConfig.isDtoGenerateEnable())
#set(voGenerateEnable = globalConfig.isVoGenerateEnable())
#set(entityClassName = table.buildEntityClassName())
#set(entityVarName = firstCharToLowerCase(entityClassName))

import #(packageConfig.entityPackage).#(table.buildEntityClassName());
#if(dtoGenerateEnable)
import #(packageConfig.dtoPackage).#(table.buildDtoClassName());
#end
#if(voGenerateEnable)
import #(packageConfig.voPackage).#(table.buildVoClassName());
#end
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * #(table.getComment()) 转换类。
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */

@Mapper
public interface #(table.buildMapstructClassName()) {

    #(table.buildMapstructClassName()) INSTANCE = Mappers.getMapper(#(table.buildMapstructClassName()).class);

    #if(dtoGenerateEnable)
    #(entityClassName) dtoToEntity(#(table.buildDtoClassName()) #(firstCharToLowerCase(table.buildDtoClassName())));

    #(table.buildDtoClassName()) entityToDto(#(entityClassName) #(entityVarName));

    List<#(table.buildDtoClassName())> entityListToDtoList(List<#(entityClassName)> #(entityVarName)List);

    List<#(entityClassName)> dtoListToEntityList(List<#(table.buildDtoClassName())> #(firstCharToLowerCase(table.buildDtoClassName()))List);
    #end

    #if(dtoGenerateEnable && voGenerateEnable)
    #(table.buildVoClassName()) dtoToVo(#(table.buildDtoClassName()) #(firstCharToLowerCase(table.buildDtoClassName())));

    #(table.buildDtoClassName()) voToDto(#(table.buildVoClassName()) #(firstCharToLowerCase(table.buildVoClassName())));

    List<#(table.buildVoClassName())> dtoListToVoList(List<#(table.buildDtoClassName())> #(firstCharToLowerCase(table.buildDtoClassName()))List);

    List<#(table.buildDtoClassName())> voListToDtoList(List<#(table.buildVoClassName())> #(firstCharToLowerCase(table.buildVoClassName()))List);
    #end

}
