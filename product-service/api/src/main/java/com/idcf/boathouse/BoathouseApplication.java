package com.idcf.boathouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.idcf.boathouse.mapper")
public class BoathouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoathouseApplication.class, args);
    }

}
