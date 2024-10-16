//package com.project.VisitBusan.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//// 참고: https://velog.io/@gmlstjq123/SpringBoot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90-Swagger-UI-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
//// springboot 3.xx 이후
//// 1. JWT를 사용하지 않는 경우
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .components(new Components())
//                .info(apiInfo());
//    }
//
//    private Info apiInfo() {
//        return new Info()
//                .title("API Test") // API의 제목
//                .description("Let's practice Swagger UI") // API에 대한 설명
//                .version("1.0.0"); // API의 버전
//    }
//}


//    2. JWT를 사용하는 경우

//    @Configuration
//    public class SwaggerConfig {
//        @Bean
//        public OpenAPI openAPI() {
//            String jwt = "JWT";
//            SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
//            Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
//                    .name(jwt)
//                    .type(SecurityScheme.Type.HTTP)
//                    .scheme("bearer")
//                    .bearerFormat("JWT")
//            );
//            return new OpenAPI()
//                    .components(new Components())
//                    .info(apiInfo())
//                    .addSecurityItem(securityRequirement)
//                    .components(components);
//        }
//        private Info apiInfo() {
//            return new Info()
//                    .title("API Test") // API의 제목
//                    .description("Let's practice Swagger UI") // API에 대한 설명
//                    .version("1.0.0"); // API의 버전
//        }
//    }



