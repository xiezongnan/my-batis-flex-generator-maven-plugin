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

import com.xiezongnan.gen.codegen.config.GlobalConfig;
import com.xiezongnan.gen.codegen.config.PackageConfig;
import com.xiezongnan.gen.codegen.config.VoConfig;
import com.xiezongnan.gen.codegen.constant.TemplateConst;
import com.xiezongnan.gen.codegen.entity.Table;
import com.xiezongnan.gen.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Vo 生成器。
 *
 * @author petrus
 */
public class VoGenerator implements IGenerator {

    private String templatePath;

    public VoGenerator() {
        this(TemplateConst.VO);
    }

    public VoGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isVoGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        VoConfig voConfig = globalConfig.getVoConfig();

        String voPackagePath = packageConfig.getVoPackage().replace(".", "/");
        File voJavaFile = new File(packageConfig.getSourceVoModuleDir(), voPackagePath + "/" +
            table.buildVoClassName() + ".java");

        if (voJavaFile.exists() && !voConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("voConfig", voConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, voJavaFile);

        System.out.println("Vo ---> " + voJavaFile);
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
