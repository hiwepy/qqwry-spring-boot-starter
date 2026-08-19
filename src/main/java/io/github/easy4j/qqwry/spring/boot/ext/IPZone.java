/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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
package io.github.easy4j.qqwry.spring.boot.ext;


/**
 * <p>IPZone implementation.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class IPZone {
	private final String ip;
	private String mainInfo = "";
	private String subInfo = "";

	public IPZone(final String ip) {
		this.ip = ip;
	}

    /**
     * <p>Returns the ip.</p>
     * @return the get ip
     */
	public String getIp() {
		return ip;
	}

    /**
     * <p>Returns the main info.</p>
     * @return the get main info
     */
	public String getMainInfo() {
		return mainInfo;
	}

    /**
     * <p>Returns the sub info.</p>
     * @return the get sub info
     */
	public String getSubInfo() {
		return subInfo;
	}

    /**
     * <p>Sets the main info.</p>
     * @param info
     */
	public void setMainInfo(final String info) {
		this.mainInfo = info;
	}

    /**
     * <p>Sets the sub info.</p>
     * @param info
     */
	public void setSubInfo(final String info) {
		this.subInfo = info;
	}

	@Override
    /**
     * <p>To string.</p>
     * @return the to string
     */
	public String toString() {
		return new StringBuilder(mainInfo).append(subInfo).toString();
	}

}
