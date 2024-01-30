package com.subskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.subskill"})
public class SubSkillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubSkillApplication.class, args);
	}

}
