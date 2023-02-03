package com.team15.bookstoreapp.model;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;


import java.util.Collections;

public class CodeGenerator {
    public void Generator() {
        FastAutoGenerator
                .create("jdbc:mysql://localhost:3306/usedbooksystem?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf-8", "root", "")
                .globalConfig(builder -> {
                    builder.author("lixia")
                            // .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")

                            .outputDir( "d:\\ttt\\src\\main\\java")
                            // .outputDir("F:\\ideawork\\publicvehiclesystem\\common-mybatis\\src\\main\\java")
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.team15")
                            .moduleName("commonmybatis")
                            .entity("model") // pojo
                            .other("utils")
                            // .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper1"));
                            .pathInfo(Collections.singletonMap(OutputFile.xml,"d:\\ttt\\src\\main\\resources\\mapper"));
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("bookimage")
//                            .addTablePrefix("t_", "c_")

                            // entity
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("isDeleted")
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
//                            .addTableFills(
//                                    new TableStat.Column("create_time", FieldFill.INSERT),
//                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
//                            )
                            .enableTableFieldAnnotation()

                            // mapper
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%s")

                            // service
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // UserService
                            .formatServiceImplFileName("%sImpl") // UserServiceImpl

                            // controller
                            .controllerBuilder()
                            .formatFileName("%sController") //  UserController
                            .enableRestStyle();

                })

                .templateEngine(new VelocityTemplateEngine())

                .execute();
    }

}
