package com.example.demo.convert;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class MyMvcSupport extends WebMvcConfigurationSupport {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println(">>>>>>>>>>>>>>>>>>> MyMvcSupport <<<<<<<<<<<<<<<<<<<<<");
        registry.addConverter(new CustomDateConverter());
    }
}
