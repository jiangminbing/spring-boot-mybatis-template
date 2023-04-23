package generate.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
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

    String author = "jiangmb";

    /**
     * @param dataSourceurl
     * @param dataSourcename
     * @param dataSourcepassword
     * @param dataSourcedriver
     * @param tables
     * @param packageParent
     * @param isNormalize
     * @description:
     * @return: void
     * @author: liyh
     * @time: 2020/12/21 21:21
     */
    public void generate(String dataSourceurl, String dataSourcename, String dataSourcepassword, String dataSourcedriver, String tables, String packageParent, boolean isNormalize) {

        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");// 当前项目的路径
        gc.setOutputDir(projectPath + "/src/main/java");// 生成文件输出根目录
        gc.setAuthor(author);// 作者
        gc.setOpen(false); // 生成完成后不弹出文件框
        gc.setFileOverride(true); // 文件是否覆盖
        gc.setIdType(IdType.ASSIGN_UUID); //主键策略 实体类主键ID类型
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true); // 是否开启swagger
        gc.setActiveRecord(true); //【不懂】 活动记录 不需要ActiveRecord特性的请改为false 是否支持AR模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);//【不懂】 XML ResultMap xml映射文件的配置
        gc.setBaseColumnList(false);//【不懂】 XML columList xml映射文件的配置

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.CLICK_HOUSE);
        dsc.setUrl(dataSourceurl);
        dsc.setDriverName(dataSourcedriver);
        dsc.setUsername(dataSourcename);
        dsc.setPassword(dataSourcepassword);
        mpg.setDataSource(dsc);

        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageParent);
        pc.setController("controller"); // 可以不用设置，默认是这个
        pc.setService("service"); // 同上
        pc.setServiceImpl("service.impl"); // 同上
        pc.setMapper("mapper"); // 默认是mapper
        pc.setEntity("entity.domain"); // 默认是entity
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tables.split(",")); // 需要生成的表 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setCapitalMode(false); //【不懂】 开启全局大写命名
        strategy.setSuperMapperClass(null); //【不懂】
        // 是否需要开启特定规范字段
        if (true == isNormalize) {
            strategy.setLogicDeleteFieldName("deleted");
            // 自动填充配置
            TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
            TableFill gmtModified = new TableFill("gmt_modified",
                    FieldFill.INSERT_UPDATE);
            ArrayList<TableFill> tableFills = new ArrayList<>();
            tableFills.add(gmtCreate);
            tableFills.add(gmtModified);
            strategy.setTableFillList(tableFills);
            // 乐观锁
            strategy.setVersionFieldName("version");
        }
        strategy.setRestControllerStyle(true); // 控制：true——生成@RsetController false——生成@Controller
        strategy.setControllerMappingHyphenStyle(true); // 【不知道是啥】
        strategy.setEntityTableFieldAnnotationEnable(true); // 表字段注释启动 启动模板中的这个 <#if table.convert>
        strategy.setEntityBooleanColumnRemoveIsPrefix(true); // 是否删除实体类字段的前缀
        strategy.setTablePrefix("mdm_"); // 去掉表名mdm_inf_rec_data中的 mdm_ 类名为InfRecData
        strategy.setControllerMappingHyphenStyle(false); // 控制层mapping的映射地址 false：infRecData true：inf_rec_data
        mpg.setStrategy(strategy);

        //模板生成器
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        TemplateConfig templateConfig = GeneratorBuilder.templateConfigBuilder().controller("/templatesFreemaker/controller.java")
                .service("/templatesFreemaker/service.java","/templatesFreemaker/serviceImpl.java")
                .entity("/templatesFreemaker/entity.java")
                .mapper("/templatesFreemaker/mapper.java")
                .mapperXml("/templatesFreemaker/mapper.xml.ftl")
                .build();


        new InjectionConfig.Builder()
                .beforeOutputFile((tableInfo, objectMap) -> {
                    reprojectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                })
                .customMap(Collections.singletonMap("test", "baomidou"))
                .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                .build();



        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        String xmlTemp = "/templatesFreemaker/mapper.xml.ftl";

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(xmlTemp) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        mpg.execute(); //执行
    }

}