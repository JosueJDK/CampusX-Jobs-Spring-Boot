package com.joucode.campus_x_jobs.common.config;

import com.joucode.campus_x_jobs.common.mappers.ResponseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ResponseMapper responseMapper(){
        return new ResponseMapper();
    }
}