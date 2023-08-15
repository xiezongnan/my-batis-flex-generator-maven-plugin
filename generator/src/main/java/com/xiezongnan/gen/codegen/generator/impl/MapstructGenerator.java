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
package com.xiezongnan.gen.codegen.generator.impl;

import com.xiezongnan.gen.codegen.config.*;
import com.xiezongnan.gen.codegen.constant.TemplateConst;
import com.xiezongnan.gen.codegen.entity.Table;
import com.xiezongnan.gen.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapstruct 生成器。
 *
 * @author petrus
 * @since 2023-08-07
 */
public class MapstructGenerator implements IGenerator {

    private String templatePath;

    public MapstructGenerator() {
        this(TemplateConst.MAPSTRUCT);
    }

    public MapstructGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isServiceGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        MapstructConfig mapstructConfig = globalConfig.getMapstructConfig();

        String mapstructPackagePath = packageConfig.getMapstructPackage().replace(".", "/");
        File mapstructJavaFile = new File(packageConfig.getSourceMapstructModuleDir(), mapstructPackagePath + "/" +
            table.buildMapstructClassName() + ".java");


        if (mapstructJavaFile.exists() && !mapstructConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(5);
        params.put("table", table);
        params.put("globalConfig", globalConfig);
        params.put("mapstructConfig", mapstructConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, mapstructJavaFile);

        System.out.println("Mapstruct ---> " + mapstructJavaFile);
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
