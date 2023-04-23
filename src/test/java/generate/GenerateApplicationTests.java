//package generate;
//
//import com.szeport.Application;
//import generate.mybatisplus.GenerateMybatisPlus;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class GenerateApplicationTests {
//
//    private String dataSourceurl = "jdbc:clickhouse://192.168.1.171:8123/macro_data_dev";
//
//
//    private String dataSourcename ="develop";
//
//
//    private String dataSourcepassword = "develop";
//
//
//    private String dataSourcedriver= "ru.yandex.clickhouse.ClickHouseDriver";
//
//
//    private String tables = "T_STATISTICAL_MAIN_GOODS_SUM";
//
//
//    private String packageParent = "com.szeport";
//
//
//    private boolean isNormalize = false;
//
//    @Test
//    public void generateMybatisPlusTest() {
//        new GenerateMybatisPlus().generate(
//                dataSourceurl,
//                dataSourcename,
//                dataSourcepassword,
//                dataSourcedriver,
//                tables,
//                packageParent,
//                isNormalize);
//    }
//
//}
//
