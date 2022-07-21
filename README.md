# qqwry-spring-boot-starter

Spring Boot Starter For QQWry

### 组件简介

 > 基于 qqwry 的 Spring Boot Starter 实现

1. 最新IP数据下载地址： http://www.cz88.net/

### 使用说明

##### 1、Spring Boot 项目添加 Maven 依赖

``` xml
<dependency>
	<groupId>com.github.hiwepy</groupId>
	<artifactId>qqwry-spring-boot-starter</artifactId>
	<version>2.0.0.RELEASE</version>
</dependency>
```


##### 2、在`application.yml`文件中增加如下配置

```yaml
################################################################################################################
###okhttp3基本配置：
################################################################################################################
qqwry:
  external: true
  location: classpath:qqwry.dat
```

##### 3、使用示例

```java


import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hiwepy.qqwry.spring.boot.ext.QQWry;

@EnableQQwry
@SpringBootApplication
public class Application {
	
	@Autowired
	QQWry qqwry;
	
	@PostConstruct
	public void test() throws IOException {
		
		System.out.println(qqwry.findIP("127.0.0.1"));
		System.out.println(qqwry.findIP("127.0.0.1"));
		
	}
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

```


## Jeebiz 技术社区

Jeebiz 技术社区 **微信公共号**、**小程序**，欢迎关注反馈意见和一起交流，关注公众号回复「Jeebiz」拉你入群。

|公共号|小程序|
|---|---|
| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/qrcode_for_gh_1d965ea2dfd1_344.jpg)| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/gh_09d7d00da63e_344.jpg)|


