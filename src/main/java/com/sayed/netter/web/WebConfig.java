package com.sayed.netter.web;

import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.sayed.netter.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	}
  
  @Bean
  public MessageSource messageSource() {
	  ResourceBundleMessageSource messageSource =
	  new ResourceBundleMessageSource();
	  messageSource.setBasename("messages");
	  return messageSource;
  }
  
  @Bean
  public TilesConfigurer tilesConfigurer() {
	  TilesConfigurer tiles = new TilesConfigurer();
	  tiles.setDefinitions(new String[] {
	  "/WEB-INF/layout/tiles.xml"
	  });
	  tiles.setCheckRefresh(true);
	  return tiles;
  }
  
  @Bean
  public MultipartResolver multipartResolver() throws IOException {
	  return new StandardServletMultipartResolver();
  }
  
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // TODO Auto-generated method stub
    super.addResourceHandlers(registry);
  }

}
