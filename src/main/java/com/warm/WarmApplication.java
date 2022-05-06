package com.warm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class WarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarmApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

}
