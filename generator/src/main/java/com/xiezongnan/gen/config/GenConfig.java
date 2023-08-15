package com.xiezongnan.gen.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * GenConfig
 * @author petrus
 */
@Getter
@Setter
public class GenConfig {

    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPwd;


    private String author;

    private boolean withLombok;

    private String globalSchema;

    private String globalBasePackage;

    private String globalEntityPackage;
    private String globalEntityClassPrefix;
    private String globalEntityClassSuffix;

    private String globalServicePackage;
    private String globalServiceClassPrefix;
    private String globalServiceClassSuffix;

    private String globalServiceImplPackage;
    private String globalServiceImplClassPrefix;
    private String globalServiceImplClassSuffix;

    private String globalMapperPackage;
    private String globalMapperClassPrefix;
    private String globalMapperClassSuffix;

    private String globalXmlPath;
    private String globalXmlFilePrefix;
    private String globalXmlFileSuffix;

    private String globalControllerPackage;
    private String globalControllerClassPrefix;
    private String globalControllerClassSuffix;

    private String globalDtoPackage;
    private String globalDtoClassPrefix;
    private String globalDtoClassSuffix;

    private String globalMapstructPackage;
    private String globalMapstructClassPrefix;
    private String globalMapstructClassSuffix;

    private String globalTableDefPackage;
    private String globalTableDefClassPrefix;
    private String globalTableDefClassSuffix;

    private String globalVoPackage;
    private String globalVoClassPrefix;
    private String globalVoClassSuffix;

    private String globalSourceModuleName;
    private String sourceEntityModuleName;
    private String sourceDtoModuleName;
    private String sourceVoModuleName;
    private String sourceMapstructModuleName;
    private String sourceMapperModuleName;
    private String sourceTableDefModuleName;
    private String sourceXmlModuleName;
    private String sourceServiceModuleName;
    private String sourceServiceImplModuleName;
    private String sourceControllerModuleName;

    private List<GenTableConfig> tables;

}
