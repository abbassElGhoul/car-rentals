package com.pc.global.car.renting.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FilesConfiguration
{

    @Value("${photos.size}")
    private String MAX_FILE_SIZE;
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Increase the maximum file size (e.g., 10 megabytes)
        factory.setMaxFileSize(DataSize.parse(MAX_FILE_SIZE));
        // Increase the maximum request size if needed
        factory.setMaxRequestSize(DataSize.parse(MAX_FILE_SIZE));
        return factory.createMultipartConfig();
    }

}
