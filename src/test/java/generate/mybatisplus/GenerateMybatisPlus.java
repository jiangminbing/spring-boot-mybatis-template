package generate.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.ClickHouseQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: liyh
 * @time: 2020/12/21 21:20
 */

//配置文件位置
public class GenerateMybatisPlus {

    String AUTHOR = "jiangmb";

    /**
     * @param dataSourceurl
     * @param dataSourcename
     * @param dataSourcepassword
     * @param dataSourcedriver
     * @param tables
     * @param packageParent
     * @description:
     * @return: void
     * @author: liyh
     * @time: 2020/12/21 21:21
     */
    public void generate(String dataSourceurl, String dataSourcename, String dataSourcepassword, String dataSourcedriver, String tables, String packageParent) {



        // 获取项目路径
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = GeneratorBuilder.globalConfigBuilder()
                .fileOverride()
                .outputDir(projectPath + "/src/main/java")
                .author(AUTHOR)
                .enableSwagger()
                .commentDate("yyyy-MM-dd")
                .build();

        // 数据源配置
        DataSourceConfig dataSource = new DataSourceConfig.Builder(dataSourceurl,
                dataSourcename, dataSourcepassword)
                .dbQuery(new ClickHouseQuery()).schema(tables).build();
        // 包配置信息
        PackageConfig pcf = GeneratorBuilder.packageConfigBuilder().parent(packageParent)
                .entity("entity")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"))
                .build();
        // 策略配置
        StrategyConfig strategy = GeneratorBuilder.strategyConfigBuilder()
                .addInclude(tables.split(","))
                .addTablePrefix(pcf.getModuleName() + "_")
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

        TemplateConfig templateConfig = GeneratorBuilder.templateConfigBuilder().controller("/templatesFreemaker/controller.java")
                .service("/templatesFreemaker/service.java")
                .serviceImpl("/templatesFreemaker/serviceImpl.java")
                .entity("/templatesFreemaker/entity.java")
                .mapper("/templatesFreemaker/mapper.java")
                .mapperXml("/templatesFreemaker/mapper.xml.ftl")
                .build();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dataSource).global(gc).strategy(strategy).template(templateConfig).packageInfo(pcf);

        mpg.execute(new FreemarkerTemplateEngine());

    }

}
