package com.github.vindell.qqwry.spring.boot;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import com.github.vindell.qqwry.spring.boot.ext.QQWry;

/**
 * 
 */
@Configuration
@EnableConfigurationProperties({ QQwryProperties.class })
public class QQwryAutoConfiguration implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	@Autowired
	private QQwryProperties properties;
	
	@Bean
	public QQWry qqwry() throws IOException {
		QQWry qqwry = null;
		if(properties.isExternal()) {
			
			if(new File(properties.getLocation()).exists()) {
				qqwry = new QQWry(Paths.get(properties.getLocation())); // load qqwry.dat from java.nio.file.Path
			} else {
				// 查找resource
				Resource resource = resourceLoader.getResource(properties.getLocation());
				
				try {
					if(resource.exists()) {
						ByteArrayOutputStream output = new ByteArrayOutputStream();
						FileCopyUtils.copy(resource.getInputStream(), output);
						qqwry = new QQWry(output.toByteArray()); // create QQWry with provided data
					}
				} catch (Exception e) {
					qqwry = new QQWry(); // load qqwry.dat from classpath
				}
			}
			
		} else {
			qqwry = new QQWry(); // load qqwry.dat from classpath
		}
		
		return qqwry;  
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
