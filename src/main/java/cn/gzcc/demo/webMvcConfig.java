package cn.gzcc.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/5/18.
 */
@Configuration
public class webMvcConfig implements WebMvcConfigurer {
    @Value("${icollege.web.upload.root-path}")
    private String rootPath;

    @Override //添加资源处理器
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(rootPath);
    }
}
