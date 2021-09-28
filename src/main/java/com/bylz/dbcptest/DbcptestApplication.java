package com.bylz.dbcptest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("com.bylz.dbcptest.mapper")
@ComponentScan("com.bylz.dbcptest.**.**")
@SpringBootApplication
public class DbcptestApplication {

    public static void main(String[] args) {
            ConfigurableApplicationContext run = SpringApplication.run(DbcptestApplication.class, args);
    }

}
