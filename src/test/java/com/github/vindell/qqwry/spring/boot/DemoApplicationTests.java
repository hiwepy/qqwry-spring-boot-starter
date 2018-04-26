package com.github.vindell.qqwry.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.vindell.qqwry.spring.boot.QQwryTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	QQwryTemplate template;
	
	@Test
	public void contextLoads() {
		
		// QQ qqwry.dat API 根据IP获取地址位置  离线获取IP的位置
		 System.out.println(template.getAddress("127.0.0.1"));  
		 System.out.println(template.getAddress("115.159.94.190"));  //120.11.88.41   113.87.161.3    115.159.94.190
	}

}
