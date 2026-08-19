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

    /**
     * <p>Checks if external.</p>
     * @return the is external
     */
	public boolean isExternal() {
		return external;
	}

    /**
     * <p>Sets the external.</p>
     * @param external
     */
	public void setExternal(boolean external) {
		this.external = external;
	}

    /**
     * <p>Returns the location.</p>
     * @return the get location
     */
	public String getLocation() {
		return location;
	}

    /**
     * <p>Sets the location.</p>
     * @param location
     */
	public void setLocation(String location) {
		this.location = location;
	}

}