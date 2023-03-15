package tw.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		Member照片生在本地端，即時生成
		registry.addResourceHandler("/memberpicture/**").addResourceLocations("file:C:/temp/memberpicture/");

//		Activity照片生在本地端，即時生成		
		registry.addResourceHandler("/activityphoto/**").addResourceLocations("file:C:/activityphoto/");


		registry.addResourceHandler("/shoppicture/**").addResourceLocations("file:C:/temp/shoppicture/");

	}


	
	
}
