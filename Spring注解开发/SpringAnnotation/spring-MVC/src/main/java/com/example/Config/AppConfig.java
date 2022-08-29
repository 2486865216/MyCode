package com.example.Config;

import com.example.Controller.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * author ye
 * createDate 2022/3/17  12:36
 */
//useDefaultFilters禁用默认的过滤规则
@ComponentScan(value = "com.example", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                Controller.class
        })
}, useDefaultFilters = false)
@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
        //定制

        //视图解析器
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
                //默认所有的页面都从/WEB-INF/view/Xxx·jsp
                registry.jsp("/WEB-INF/view/", ".jsp");
        }

        //静态资源访问
        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                configurer.enable();
        }

        //拦截器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        }
}
