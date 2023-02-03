package com.team15.bookstoreapp;

import com.team15.bookstoreapp.model.CodeGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.team15.bookstoreapp.*","com.team15.commonmybatis.*"})
@MapperScan({"com.team15.commonmybatis.mapper"})
public class BookstoreappApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(BookstoreappApplication.class, args);
//           new CodeGenerator().Generator();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
