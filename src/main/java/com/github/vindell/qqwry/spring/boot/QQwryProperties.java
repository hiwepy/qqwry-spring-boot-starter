package com.github.vindell.qqwry.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(QQwryProperties.PREFIX)
public class QQwryProperties {

	public static final String PREFIX = "qqwry";
	// 一些固定常量，比如记录长度等等  
	public static final int IP_RECORD_LENGTH = 7;  
	
	/**
	 * qqwry.dat 文件路径，默认： classpath:qqwry.dat
	 */
	private String location = "classpath:qqwry.dat";
	/**
	 * IP地址记录长度。默认：7
	 */
	private int ipRecordLength = IP_RECORD_LENGTH;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIpRecordLength() {
		return ipRecordLength;
	}

	public void setIpRecordLength(int ipRecordLength) {
		this.ipRecordLength = ipRecordLength;
	}
	
	

}