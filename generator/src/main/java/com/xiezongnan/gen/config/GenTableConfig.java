package com.xiezongnan.gen.config;

import lombok.Getter;
import lombok.Setter;

/**
 * GenTableConfig
 * @author petrus
 */
@Getter
@Setter
public class GenTableConfig {

    private String tableName;
    private String schema;

    private boolean needGenerateEntity;
    private boolean needGenerateDto;
    private boolean needGenerateVo;

    private boolean needGenerateMapper;
    private boolean needGenerateXml;

    private boolean needGenerateService;

    private boolean needGenerateMapstruct;

    private boolean needGenerateController;

    private boolean needGenerateTableDef;

    private String author;

    private String basePackage;

    private String entityPackage;
    private String dtoPackage;
    private String voPackage;

    private String servicePackage;
    private String serviceImplPackage;

    private String mapperPackage;
    private String xmlPath;

    private String controllerPackage;

    private String mapstructPackage;

    private String tableDefPackage;

}
