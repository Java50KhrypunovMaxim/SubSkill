package com.subskill;

import com.subskill.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;


@SpringBootApplication
public class SubSkillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubSkillApplication.class, args);

    }
}