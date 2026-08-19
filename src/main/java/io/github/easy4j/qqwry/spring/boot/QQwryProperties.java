package io.github.easy4j.qqwry.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(QQwryProperties.PREFIX)
/**
 * <p>Configuration properties for QQwry.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class QQwryProperties {

	public static final String PREFIX = "qqwry";
	
	/**
	 * 是否使用外部的IP数据文件.
	 */
	private boolean external = false;
	/**
	 * qqwry.dat 文件路径，默认： classpath:qqwry.dat
	 */
	private String location = "classpath:qqwry.dat";

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}