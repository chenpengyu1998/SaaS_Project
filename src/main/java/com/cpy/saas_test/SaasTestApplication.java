package com.cpy.saas_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class SaasTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaasTestApplication.class, args);
    }

//
//    @Bean
//    public ViewResolver MyViewResolver(){
//        return new MyViewResolver();
//    }
//
//    private static class MyViewResolver implements ViewResolver{
//
//        @Override
//        public View resolveViewName(String s, Locale locale) throws Exception {
//            return null;
//        }
//    }



}



//要是想改变访问图标，改名为favicon.ico放入resources下的文件及都可以