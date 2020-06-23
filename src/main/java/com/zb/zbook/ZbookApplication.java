package com.zb.zbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.zb.zbook"})
@ServletComponentScan
@MapperScan("com.zb.zbook.mapper")
@SpringBootApplication
public class ZbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZbookApplication.class, args);
    }

}
