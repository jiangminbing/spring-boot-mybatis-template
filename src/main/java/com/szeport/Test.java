package com.szeport;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.querys.ClickHouseQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author jiangmb
 * @version 1.0.0
 * @date 2023-04-23 17:41
 */
public class Test {
    static final String AUTHOR = "jiangmb";
    private static final String JAVA_PATH = "/src/main/java";
    public static void main(String[] args) {

        // 获取项目路径
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = GeneratorBuilder.globalConfigBuilder()
                .fileOverride()
                .outputDir(projectPath + JAVA_PATH)
                .author(AUTHOR)
                .enableSwagger()
                .commentDate("yyyy-MM-dd")
                .build();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder("jdbc:clickhouse://192.168.1.171:8123/macro_data_dev",
                "develop", "develop")
                .dbQuery(new ClickHouseQuery()).schema("T_STATISTICAL_GOODS_MONTH_SUM").build();

        // 包配置
        PackageConfig pc = GeneratorBuilder.packageConfigBuilder().parent("com.szeport").build();

        // 策略配置
        StrategyConfig strategy = GeneratorBuilder.strategyConfigBuilder()
                .addInclude("T_STATISTICAL_GOODS_MONTH_SUM")
                .addTablePrefix(pc.getModuleName() + "_")
                .controllerBuilder().enableHyphenStyle()
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .versionColumnName("version").logicDeleteColumnName("isDelete")
                .enableLombok()
                .build();

        strategy.mapperBuilder().mapperBuilder()
                .enableBaseResultMap()
                .enableBaseColumnList();




        TemplateConfig templateConfig = GeneratorBuilder.templateConfigBuilder().build();




        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dsc).global(gc).strategy(strategy).template(templateConfig).packageInfo(pc);

        mpg.execute(new FreemarkerTemplateEngine());

    }
}
