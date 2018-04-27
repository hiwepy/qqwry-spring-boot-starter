/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.vindell.qqwry.spring.boot.ext;


public class IPZone {
	private final String ip;
	private String mainInfo = "";
	private String subInfo = "";

	public IPZone(final String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public String getMainInfo() {
		return mainInfo;
	}

	public String getSubInfo() {
		return subInfo;
	}

	public void setMainInfo(final String info) {
		this.mainInfo = info;
	}

	public void setSubInfo(final String info) {
		this.subInfo = info;
	}

	@Override
	public String toString() {
		return new StringBuilder(mainInfo).append(subInfo).toString();
	}

}
