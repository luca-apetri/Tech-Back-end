package com.Intelligent_Forms.Intelligent_Forms_FCR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableScheduling
@SpringBootApplication
@Controller


public class IntelligentFormsFcrApplication {

    @RequestMapping("/")
    @ResponseBody
    public static void main(String[] args) {

        SpringApplication.run(IntelligentFormsFcrApplication.class, args);
    }

}
