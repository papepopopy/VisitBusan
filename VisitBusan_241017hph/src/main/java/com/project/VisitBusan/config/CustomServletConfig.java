//package com.project.VisitBusan.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.http.MediaType;
//
//
//// swagger 쓸 때 사용으로 설정
//// Swagger UI 라이브러리로 인하여 정적 자원 경로가 변경됨
//@Configuration
//@EnableWebMvc  // resource위치를 따로 지정. 때문에 simple sidebar가 작동 안함
//public class CustomServletConfig  implements WebMvcConfigurer {
//    // WebMvcconfigurer.super.addResourceHandler(registry);
//
//    // utf-8 인코딩 설정 필요 일부 경우 한글 깨짐
//
//    // 경로 재설정
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("classpath:/static/js/");  // classpath:/static/js/ 여기에 있는걸 /js/** 하위로 재설정
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("classpath:/static/css/");
//        registry.addResourceHandler("/")
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/javaStudy/upload/**")
//                .addResourceLocations("file:///C:/javaStudy/upload/");
//    }
//
//}
