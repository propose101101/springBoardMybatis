package org.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/uploads/**"; // view에서 사용할 경로
    //private String savePath = "file:///Users/codingrecipe/development/intellij_community/spring_upload_files/"; // 실제 파일 저장 경로
    private String savePath = "file:///C:/Temp/uploads/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}