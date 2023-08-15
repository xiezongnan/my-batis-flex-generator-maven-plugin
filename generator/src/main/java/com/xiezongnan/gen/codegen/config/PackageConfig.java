/*
 *  Copyright (c) 2022-2023, Mybatis-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.xiezongnan.gen.codegen.config;

import com.mybatisflex.core.util.StringUtil;

/**
 * 生成软件包的配置。
 *
 * @author 王帅
 * @since 2023-05-15
 */
@SuppressWarnings("unused")
public class PackageConfig {

    /**
     * 代码生成目录。
     */
    private String sourceDir;
    private String globalSourceModuleDir;
    private String sourceEntityModuleDir;
    private String sourceDtoModuleDir;
    private String sourceVoModuleDir;
    private String sourceMapstructModuleDir;
    private String sourceMapperModuleDir;
    private String sourceTableDefModuleDir;
    private String sourceXmlModuleDir;
    private String sourceServiceModuleDir;
    private String sourceServiceImplModuleDir;
    private String sourceControllerModuleDir;

    /**
     * 根包。
     */
    private String basePackage = "com.mybatisflex";

    /**
     * Entity 所在包。
     */
    private String entityPackage;

    /**
     * dto 所在包。
     */
    private String dtoPackage;

    /**
     * Mapper 所在包。
     */
    private String mapperPackage;

    /**
     * Service 所在包。
     */
    private String servicePackage;

    /**
     * ServiceImpl 所在包。
     */
    private String serviceImplPackage;

    /**
     * Controller 所在包。
     */
    private String controllerPackage;

    /**
     * TableDef 所在包。
     */
    private String tableDefPackage;

    /**
     * MapperXml 文件所在位置。
     */
    private String mapperXmlPath;

    /**
     * vo 所在包。
     */
    private String voPackage;

    /**
     * mapstruct 所在包。
     */
    private String mapstructPackage;

    /**
     * 获取生成目录。
     */
    @Deprecated
    public String getSourceDir() {
        if (StringUtil.isBlank(sourceDir)) {
            return System.getProperty("user.dir") + "/src/main/java";
        }
        return sourceDir;
    }

    /**
     * 设置生成目录。
     */
    public PackageConfig setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
        return this;
    }

    /**
     * 获得根包路径。
     */
    public String getBasePackage() {
        return basePackage;
    }

    /**
     * 设置根包路径。
     */
    public PackageConfig setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    /**
     * 获取实体类层包路径。
     */
    public String getEntityPackage() {
        if (StringUtil.isBlank(entityPackage)) {
            return basePackage.concat(".entity");
        }
        return entityPackage;
    }

    /**
     * 设置实体类层包路径。
     */
    public PackageConfig setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
        return this;
    }

    public String getDtoPackage() {
        if (StringUtil.isBlank(dtoPackage)) {
            return basePackage.concat(".dto");
        }
        return dtoPackage;
    }

    public PackageConfig setDtoPackage(String dtoPackage) {
        this.dtoPackage = dtoPackage;
        return this;
    }

    public String getVoPackage() {
        if (StringUtil.isBlank(voPackage)) {
            return basePackage.concat(".vo");
        }
        return voPackage;
    }

    public PackageConfig setVoPackage(String voPackage) {
        this.voPackage = voPackage;
        return this;
    }

    public String getMapstructPackage() {
        if (StringUtil.isBlank(mapstructPackage)) {
            return basePackage.concat(".mapstruct");
        }
        return mapstructPackage;
    }

    public PackageConfig setMapstructPackage(String mapstructPackage) {
        this.mapstructPackage = mapstructPackage;
        return this;
    }

    /**
     * 获取映射层包路径。
     */
    public String getMapperPackage() {
        if (StringUtil.isBlank(mapperPackage)) {
            return basePackage.concat(".mapper");
        }
        return mapperPackage;
    }

    /**
     * 设置映射层包路径。
     */
    public PackageConfig setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
        return this;
    }

    /**
     * 获取服务层包路径。
     */
    public String getServicePackage() {
        if (StringUtil.isBlank(servicePackage)) {
            return basePackage.concat(".service");
        }
        return servicePackage;
    }

    /**
     * 设置服务层包路径。
     */
    public PackageConfig setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
        return this;
    }

    /**
     * 获取服务层实现包路径。
     */
    public String getServiceImplPackage() {
        if (StringUtil.isBlank(serviceImplPackage)) {
            return basePackage.concat(".service.impl");
        }
        return serviceImplPackage;
    }

    /**
     * 设置服务层实现包路径。
     */
    public PackageConfig setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
        return this;
    }

    /**
     * 获取控制层包路径。
     */
    public String getControllerPackage() {
        if (StringUtil.isBlank(controllerPackage)) {
            return basePackage.concat(".controller");
        }
        return controllerPackage;
    }

    /**
     * 设置控制层包路径。
     */
    public PackageConfig setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
        return this;
    }

    /**
     * 获取表定义层包路径。
     */
    public String getTableDefPackage() {
        if (StringUtil.isBlank(tableDefPackage)) {
            return getEntityPackage().concat(".table");
        }
        return tableDefPackage;
    }

    /**
     * 设置表定义层包路径。
     */
    public PackageConfig setTableDefPackage(String tableDefPackage) {
        this.tableDefPackage = tableDefPackage;
        return this;
    }

    /**
     * 获取 Mapper XML 文件路径。
     */
    @Deprecated
    public String getMapperXmlPath() {
        if (StringUtil.isBlank(mapperXmlPath)) {
            return getSourceDir().concat("/resources/mapper");
        }
        return mapperXmlPath;
    }

    /**
     * 设置 Mapper XML 文件路径。
     */
    public PackageConfig setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
        return this;
    }

    /*
     * module 生成路径
     */
    public String getGlobalSourceModuleDir() {
        if (StringUtil.isBlank(globalSourceModuleDir)) {
            globalSourceModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + globalSourceModuleDir + "/src/main/java";
    }

    public String getSourceEntityModuleDir() {
        if (StringUtil.isBlank(sourceEntityModuleDir)) {
            sourceEntityModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceEntityModuleDir + "/src/main/java";
    }

    public String getSourceDtoModuleDir() {
        if (StringUtil.isBlank(sourceDtoModuleDir)) {
            sourceDtoModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceDtoModuleDir + "/src/main/java";
    }

    public String getSourceVoModuleDir() {
        if (StringUtil.isBlank(sourceVoModuleDir)) {
            sourceVoModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceVoModuleDir + "/src/main/java";
    }

    public String getSourceMapstructModuleDir() {
        if (StringUtil.isBlank(sourceMapstructModuleDir)) {
            sourceMapstructModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceMapstructModuleDir + "/src/main/java";
    }

    public String getSourceMapperModuleDir() {
        if (StringUtil.isBlank(sourceMapperModuleDir)) {
            sourceMapperModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceMapperModuleDir + "/src/main/java";
    }

    public String getSourceTableDefModuleDir() {
        if (StringUtil.isBlank(sourceTableDefModuleDir)) {
            sourceTableDefModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceTableDefModuleDir + "/src/main/java";
    }

    public String getSourceXmlModuleDir() {
        if (StringUtil.isBlank(sourceXmlModuleDir)) {
            sourceXmlModuleDir = "auto-generate-directory";
        }
        String dir = System.getProperty("parent.project.path") + "/" + sourceXmlModuleDir + "/src/main/resources";
        if (StringUtil.isNotBlank(mapperXmlPath)) {
            String xmlPath = mapperXmlPath.replace(".", "/");
            if (!mapperXmlPath.startsWith("/")) {
                dir = dir + "/";
            }
            dir = dir + xmlPath;
        }
        return dir;
    }

    public String getSourceServiceModuleDir() {
        if (StringUtil.isBlank(sourceServiceModuleDir)) {
            sourceServiceModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceServiceModuleDir + "/src/main/java";
    }

    public String getSourceServiceImplModuleDir() {
        if (StringUtil.isBlank(sourceServiceImplModuleDir)) {
            sourceServiceImplModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceServiceImplModuleDir + "/src/main/java";
    }

    public String getSourceControllerModuleDir() {
        if (StringUtil.isBlank(sourceControllerModuleDir)) {
            sourceControllerModuleDir = "auto-generate-directory";
        }
        return System.getProperty("parent.project.path") + "/" + sourceControllerModuleDir + "/src/main/java";
    }

    public void setGlobalSourceModuleDir(String globalSourceModuleDir) {
        this.globalSourceModuleDir = globalSourceModuleDir;
    }

    public void setSourceEntityModuleDir(String sourceEntityModuleDir) {
        this.sourceEntityModuleDir = sourceEntityModuleDir;
    }

    public void setSourceDtoModuleDir(String sourceDtoModuleDir) {
        this.sourceDtoModuleDir = sourceDtoModuleDir;
    }

    public void setSourceVoModuleDir(String sourceVoModuleDir) {
        this.sourceVoModuleDir = sourceVoModuleDir;
    }

    public void setSourceMapstructModuleDir(String sourceMapstructModuleDir) {
        this.sourceMapstructModuleDir = sourceMapstructModuleDir;
    }

    public void setSourceMapperModuleDir(String sourceMapperModuleDir) {
        this.sourceMapperModuleDir = sourceMapperModuleDir;
    }

    public void setSourceTableDefModuleDir(String sourceTableDefModuleDir) {
        this.sourceTableDefModuleDir = sourceTableDefModuleDir;
    }

    public void setSourceXmlModuleDir(String sourceXmlModuleDir) {
        this.sourceXmlModuleDir = sourceXmlModuleDir;
    }

    public void setSourceServiceModuleDir(String sourceServiceModuleDir) {
        this.sourceServiceModuleDir = sourceServiceModuleDir;
    }

    public void setSourceServiceImplModuleDir(String sourceServiceImplModuleDir) {
        this.sourceServiceImplModuleDir = sourceServiceImplModuleDir;
    }

    public void setSourceControllerModuleDir(String sourceControllerModuleDir) {
        this.sourceControllerModuleDir = sourceControllerModuleDir;
    }
}
