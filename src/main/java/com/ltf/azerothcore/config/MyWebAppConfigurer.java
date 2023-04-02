package com.ltf.azerothcore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 拦截器
* @ClassName: MyWebAppConfigurer
* @author fuce
* @date 2018年6月3日
*
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

	/** 添加拦截器 **/

	/** 静态资源处理 **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//配置虚拟路径为项目得static下面
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/static/index.html");
		WebMvcConfigurer.super.addViewControllers(registry);
	}

	/**
	 * 跨域支持
	 * @author fuce
	 *
	 */
	@Configuration(proxyBeanMethods = false)
	public class MyCorsConfiguration {
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/api/**");
	            }

	        };
	    }
	}


}
