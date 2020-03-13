package com.web.curation;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.curation.jwt.JwtInterceptor;
//http://localhost:9876/ssafyvue/swagger-ui.html
//http://localhost:8888/swagger-ui.html
@SpringBootApplication
@MapperScan(basePackages="com.web.curation.mappers")
public class FinalbootApplication extends SpringBootServletInitializer implements WebMvcConfigurer  {
	@Autowired
	private JwtInterceptor jwtInterceptor;
	 
	public static void main(String[] args) {
		SpringApplication.run(FinalbootApplication.class, args);
	}
 
	 /**
     * JWTInterceptor를 설치한다.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**") // 기본 적용 경로
                .excludePathPatterns(Arrays.asList("/api/account/**"));// 적용 제외 경로
    }

    /**
     * Interceptor를 이용해서 처리하므로 전역의 Corss Origin 처리를 해준다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-token");
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FinalbootApplication.class);
    }


//출처: https://www.reimaginer.me/entry/Spring-Boot-jar-to-war [Reimaginer]
}
