package fit.yujing.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import fit.yujing.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/15 22:49
 * @description
 */
@Configuration
@EnableWebMvc
public class MyWebConfigurer implements WebMvcConfigurer {

    // spring配置拦截器: https://blog.csdn.net/qq_40413725/article/details/107626038
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
    // 放行swagger相关资源
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //配置登录拦截器
//        registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    // 在 Spring 中集成 Fastjson2: https://alibaba.github.io/fastjson2/spring_support_cn.html
}