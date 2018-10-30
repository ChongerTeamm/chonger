package com.chonger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.manage.mapper")
public class StarterManage {
	public static void main(String[] args) {
		SpringApplication.run(StarterManage.class, args);
	}
}
