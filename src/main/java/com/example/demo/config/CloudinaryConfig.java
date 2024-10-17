package com.example.demo.config;
import com.cloudinary.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary();
        cloudinary.config.cloudName = "dewzmlmhw";
        cloudinary.config.apiKey = "366434297486653";
        cloudinary.config.apiSecret = "ww1wOKgvj1J_wzTiHKE2BCfiZ5s";
        return cloudinary;
    }
}