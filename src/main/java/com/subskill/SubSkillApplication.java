package com.subskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SubSkillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubSkillApplication.class, args);
	}

}
