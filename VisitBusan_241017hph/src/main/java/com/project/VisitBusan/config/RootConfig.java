package com.project.VisitBusan.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링을 실행할 때 어노테이션을 다 체크해서 먼저 인식함
@Configuration  // 환경설정
public class RootConfig {

    @Bean
    public ModelMapper getMapperMapper () {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);  // LOOSE  STRICT

        return modelMapper;
    }
}
