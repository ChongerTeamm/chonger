package com.chonger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chonger.sso.mapper")

public class SsoStarter {
public static void main(String[] args) {
	SpringApplication.run(SsoStarter.class, args);
}
}
