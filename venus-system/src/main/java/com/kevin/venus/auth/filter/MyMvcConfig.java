package com.kevin.venus.auth.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态文件存储配置 暂时没使用
 * 
 * @author kevin
 *
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
	
	@Value("${upload.file.path}")
	private String uploadFilePath;
	
	
	@Value("${show.file.path}")
	private String showFilePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// String pathStr = "d:/test/";
		// System.out.println(pathStr);

		// registry.addResourceHandler("/xyz/**").addResourceLocations("classpath:/static/");
		// registry.addResourceHandler("[/]abc/**").addResourceLocations("file:///" +
		// pathStr);
		// registry.addResourceHandler("/abc/**").addResourceLocations("d:/test/");
		System.out.println("静态文件存储目录");
		registry.addResourceHandler(showFilePath + "**").addResourceLocations("file:" + uploadFilePath);
		super.addResourceHandlers(registry);

	}
}
