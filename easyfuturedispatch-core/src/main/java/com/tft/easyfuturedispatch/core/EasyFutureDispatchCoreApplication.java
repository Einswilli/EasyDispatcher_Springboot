package com.tft.easyfuturedispatch.core;

//import com.tft.easyfuturedispatch.core.security.securityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class EasyFutureDispatchCoreApplication {
    public static  void main(String[] args){

        SpringApplication.run(EasyFutureDispatchCoreApplication.class);

    }

}
