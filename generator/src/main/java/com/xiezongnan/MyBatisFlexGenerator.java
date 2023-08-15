package com.xiezongnan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.xiezongnan.gen.codegen.Generator;
import com.xiezongnan.gen.codegen.config.GlobalConfig;
import com.xiezongnan.gen.codegen.config.TableDefConfig;
import com.xiezongnan.gen.config.GenConfig;
import com.xiezongnan.gen.config.GenTableConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Mojo(name = "mybatis-flex:gen")
@Slf4j
public class MyBatisFlexGenerator extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter(property = "mybatis-flex.generator.config", defaultValue = "${project.basedir}/src/main/resources/gen-config.yml", required = true)
    private File config;

    @Override
    public void execute() {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            GenConfig genConfig = objectMapper.readValue(config, GenConfig.class);

            if (Objects.isNull(genConfig)) {
                log.error("Read config file error!");
                return;
            }

            List<GenTableConfig> tables = genConfig.getTables();
            if (CollectionUtils.isEmpty(tables)) {
                log.error("Table config empty!");
                return;
            }

            // 数据源配置
            String jdbcUrl = genConfig.getJdbcUrl();
            String jdbcUsername = genConfig.getJdbcUsername();
            String jdbcPwd = genConfig.getJdbcPwd();

            //配置数据源
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUsername(jdbcUsername);
            dataSource.setPassword(jdbcPwd);

            String parentProjectPath = project.getParent().getBasedir().getPath();
            // 父级项目根目录
            System.setProperty("parent.project.path", parentProjectPath);

            for (GenTableConfig genTableConfig : tables) {
                log.info("Table: {}", genTableConfig.getTableName());
                GlobalConfig globalConfig = handleTable(genConfig, genTableConfig);
                //通过 datasource 和 globalConfig 创建代码生成器
                Generator generator = new Generator(dataSource, globalConfig);
                //生成代码
                generator.generate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void processTableDef(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateTableDef()) {
            gc.setTableDefGenerateEnable(true);
            // 覆盖
            gc.setTableDefOverwriteEnable(true);

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceTableDefModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceTableDefModuleName())) {
                gc.setSourceTableDefModuleDir(genConfig.getSourceTableDefModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalTableDefPackage())) {
                gc.setTableDefPackage(genConfig.getGlobalTableDefPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getTableDefPackage())) {
                gc.setTableDefPackage(genTableConfig.getTableDefPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalTableDefClassPrefix())) {
                gc.setTableDefClassPrefix(genConfig.getGlobalTableDefClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalTableDefClassSuffix())) {
                gc.setTableDefClassSuffix(genConfig.getGlobalTableDefClassSuffix());
            }

            // 风格
            gc.setTableDefPropertiesNameStyle(TableDefConfig.NameStyle.LOWER_CAMEL_CASE);

        }
    }

    private static void processController(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateController()) {
            gc.setControllerGenerateEnable(true);
            // 覆盖
            gc.setControllerOverwriteEnable(true);

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceControllerModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceControllerModuleName())) {
                gc.setSourceControllerModuleDir(genConfig.getSourceControllerModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalControllerPackage())) {
                gc.setControllerPackage(genConfig.getGlobalControllerPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getControllerPackage())) {
                gc.setControllerPackage(genTableConfig.getControllerPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalControllerClassPrefix())) {
                gc.setControllerClassPrefix(genConfig.getGlobalControllerClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalControllerClassSuffix())) {
                gc.setControllerClassSuffix(genConfig.getGlobalControllerClassSuffix());
            }

        }
    }

    private static void processMapstruct(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateMapstruct()) {
            gc.setMapstructGenerateEnable(true);
            gc.setMapstructOverwriteEnable(true);

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceMapstructModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceMapstructModuleName())) {
                gc.setSourceMapstructModuleDir(genConfig.getSourceMapstructModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalMapstructPackage())) {
                gc.setMapstructPackage(genConfig.getGlobalMapstructPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getMapstructPackage())) {
                gc.setMapstructPackage(genTableConfig.getMapstructPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalMapstructClassPrefix())) {
                gc.setMapstructClassPrefix(genConfig.getGlobalMapstructClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalMapstructClassSuffix())) {
                gc.setMapstructClassSuffix(genConfig.getGlobalMapstructClassSuffix());
            }

        }
    }

    private static void processService(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateService()) {
            // service
            gc.setServiceGenerateEnable(true);
            gc.setServiceOverwriteEnable(true);

            gc.setServiceImplGenerateEnable(true);
            gc.setServiceImplOverwriteEnable(true);

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceServiceModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceServiceModuleName())) {
                gc.setSourceServiceModuleDir(genConfig.getSourceServiceModuleName());
            }

            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceServiceImplModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceServiceImplModuleName())) {
                gc.setSourceServiceImplModuleDir(genConfig.getSourceServiceImplModuleName());
            }

            // service 包
            if (StringUtils.isNotBlank(genConfig.getGlobalServicePackage())) {
                gc.setServicePackage(genConfig.getGlobalServicePackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getServicePackage())) {
                gc.setServicePackage(genTableConfig.getServicePackage());
            }

            // serviceImpl 包
            if (StringUtils.isNotBlank(genConfig.getGlobalServiceImplPackage())) {
                gc.setServiceImplPackage(genConfig.getGlobalServiceImplPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getServiceImplPackage())) {
                gc.setServiceImplPackage(genTableConfig.getServiceImplPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalServiceClassPrefix())) {
                gc.setServiceClassPrefix(genConfig.getGlobalServiceClassPrefix());
            }
            if (StringUtils.isNotBlank(genConfig.getGlobalServiceImplClassPrefix())) {
                gc.setServiceImplClassPrefix(genConfig.getGlobalServiceImplClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalServiceClassSuffix())) {
                gc.setServiceClassSuffix(genConfig.getGlobalServiceClassSuffix());
            }
            if (StringUtils.isNotBlank(genConfig.getGlobalServiceImplClassSuffix())) {
                gc.setServiceImplClassSuffix(genConfig.getGlobalServiceImplClassSuffix());
            }
        }
    }

    private static void processMapper(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateMapper()) {
            // mapper生成
            gc.setMapperGenerateEnable(true);
            gc.setMapperOverwriteEnable(true);

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceMapperModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceMapperModuleName())) {
                gc.setSourceMapperModuleDir(genConfig.getSourceMapperModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalMapperPackage())) {
                gc.setMapperPackage(genConfig.getGlobalMapperPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getMapperPackage())) {
                gc.setMapperPackage(genTableConfig.getMapperPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalMapperClassPrefix())) {
                gc.setMapperClassPrefix(genConfig.getGlobalMapperClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalMapperClassSuffix())) {
                gc.setMapperClassSuffix(genConfig.getGlobalMapperClassSuffix());
            }
        }
    }

    private static void processXml(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateMapper()) {
            // xml
            gc.setMapperXmlGenerateEnable(true);
            gc.setMapperXmlOverwriteEnable(true);

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceXmlModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceXmlModuleName())) {
                gc.setSourceXmlModuleDir(genConfig.getSourceXmlModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalXmlPath())) {
                gc.setMapperXmlPath(genConfig.getGlobalXmlPath());
            }
            if (StringUtils.isNotBlank(genTableConfig.getXmlPath())) {
                gc.setMapperXmlPath(genTableConfig.getXmlPath());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalXmlFilePrefix())) {
                gc.setMapperXmlFilePrefix(genConfig.getGlobalXmlFilePrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalXmlFileSuffix())) {
                gc.setMapperXmlFileSuffix(genConfig.getGlobalXmlFileSuffix());
            }
        }
    }

    private static void processDto(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateDto()) {
            // dto生成
            gc.setDtoGenerateEnable(true);
            // 覆盖
            gc.setDtoOverwriteEnable(true);
            // lombok
            gc.setDtoWithLombok(genConfig.isWithLombok());

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceDtoModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceDtoModuleName())) {
                gc.setSourceDtoModuleDir(genConfig.getSourceDtoModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalDtoPackage())) {
                gc.setDtoPackage(genConfig.getGlobalDtoPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getDtoPackage())) {
                gc.setDtoPackage(genTableConfig.getDtoPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalDtoClassPrefix())) {
                gc.setDtoClassPrefix(genConfig.getGlobalDtoClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalDtoClassSuffix())) {
                gc.setDtoClassSuffix(genConfig.getGlobalDtoClassSuffix());
            }
        }
    }


    private static void processVo(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateVo()) {
            // vo生成
            gc.setVoGenerateEnable(true);
            // 覆盖
            gc.setVoOverwriteEnable(true);
            // lombok
            gc.setVoWithLombok(genConfig.isWithLombok());

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceVoModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceVoModuleName())) {
                gc.setSourceVoModuleDir(genConfig.getSourceVoModuleName());
            }

            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalVoPackage())) {
                gc.setVoPackage(genConfig.getGlobalVoPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getVoPackage())) {
                gc.setVoPackage(genTableConfig.getVoPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalVoClassPrefix())) {
                gc.setVoClassPrefix(genConfig.getGlobalVoClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalVoClassSuffix())) {
                gc.setVoClassSuffix(genConfig.getGlobalVoClassSuffix());
            }
        }
    }


    private static void processEntity(GenConfig genConfig, GenTableConfig genTableConfig, GlobalConfig gc) {
        if (genTableConfig.isNeedGenerateEntity()) {
            // 生成entity
            gc.setEntityGenerateEnable(true);
            // 覆盖
            gc.setEntityOverwriteEnable(true);
            // lombok
            gc.setEntityWithLombok(genConfig.isWithLombok());

            // 目标Model
            if (StringUtils.isNotBlank(genConfig.getGlobalSourceModuleName())) {
                gc.setSourceEntityModuleDir(genConfig.getGlobalSourceModuleName());
            }
            if (StringUtils.isNotBlank(genConfig.getSourceEntityModuleName())) {
                gc.setSourceEntityModuleDir(genConfig.getSourceEntityModuleName());
            }


            // 包
            if (StringUtils.isNotBlank(genConfig.getGlobalEntityPackage())) {
                gc.setEntityPackage(genConfig.getGlobalEntityPackage());
            }
            if (StringUtils.isNotBlank(genTableConfig.getEntityPackage())) {
                gc.setEntityPackage(genTableConfig.getEntityPackage());
            }

            // 前缀
            if (StringUtils.isNotBlank(genConfig.getGlobalEntityClassPrefix())) {
                gc.setEntityClassPrefix(genConfig.getGlobalEntityClassPrefix());
            }

            // 后缀
            if (StringUtils.isNotBlank(genConfig.getGlobalEntityClassSuffix())) {
                gc.setEntityClassSuffix(genConfig.getGlobalEntityClassSuffix());
            }
        }
    }

    public GlobalConfig handleTable(GenConfig genConfig, GenTableConfig genTableConfig) {

        GlobalConfig gc = new GlobalConfig();

        /*
            库配置
         */
        if (StringUtils.isNotBlank(genConfig.getGlobalSchema())) {
            gc.setGenerateSchema(genConfig.getGlobalSchema());
        }
        if (StringUtils.isNotBlank(genTableConfig.getSchema())) {
            gc.setGenerateSchema(genTableConfig.getSchema());
        }

        // 表配置
        gc.setGenerateTable(genTableConfig.getTableName());

        /*
            根包配置
         */
        if (StringUtils.isNotBlank(genConfig.getGlobalBasePackage())) {
            gc.setBasePackage(genConfig.getGlobalBasePackage());
        }
        if (StringUtils.isNotBlank(genTableConfig.getBasePackage())) {
            gc.setBasePackage(genTableConfig.getBasePackage());
        }

        // entity
        processEntity(genConfig, genTableConfig, gc);

        // dto
        processDto(genConfig, genTableConfig, gc);

        // mapper
        processMapper(genConfig, genTableConfig, gc);

        // xml
        processXml(genConfig, genTableConfig, gc);

        // service
        processService(genConfig, genTableConfig, gc);

        // controller
        processController(genConfig, genTableConfig, gc);

        // tableDef
        processTableDef(genConfig, genTableConfig, gc);

        // vo
        processVo(genConfig, genTableConfig, gc);

        // mapstruct
        processMapstruct(genConfig, genTableConfig, gc);


        return gc;
    }
}
