package com.github.vindell.qqwry.spring.boot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

/**
 * 
 */
@Configuration
@ConditionalOnProperty(prefix = QQwryProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ QQwryProperties.class })
public class QQwryAutoConfiguration implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	@Autowired
	private QQwryProperties properties;
	
	@Bean
	public RandomAccessFile qqwryFile() throws FileNotFoundException, IOException {
		// 查找resource
		Resource resource = resourceLoader.getResource(properties.getQqwryDat());
		if(resource.isFile()) {
			return new RandomAccessFile(resource.getFile(), "r");  
		} else {
			File qqwry = new File(SystemUtils.getJavaIoTmpDir(), "qqwry.dat");
			FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(qqwry));
			return new RandomAccessFile(qqwry, "r");  
		}
	}
	
	@Bean
	public QQwryTemplate qqwryTemplate(RandomAccessFile qqwryFile) throws IOException {
		return new QQwryTemplate(qqwryFile, properties.getIpRecordLength());  
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
