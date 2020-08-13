# qqwry-spring-boot-starter

Spring Boot Starter For QQWry

### 说明


 > 基于 qqwry 的 Spring Boot Starter 实现

1. 最新IP数据下载地址： http://www.cz88.net/

### Maven

``` xml
<dependency>
	<groupId>com.github.hiwepy</groupId>
	<artifactId>qqwry-spring-boot-starter</artifactId>
	<version>1.0.2.RELEASE</version>
</dependency>
```

### Sample

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

如果使用外部IP数据，可自定义配置，参考如下：
```yaml
qqwry:
  external: true
  location: classpath:qqwry.dat
  
```

