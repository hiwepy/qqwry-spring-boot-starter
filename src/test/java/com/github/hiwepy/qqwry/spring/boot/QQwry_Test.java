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
package com.github.hiwepy.qqwry.spring.boot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.hiwepy.qqwry.spring.boot.ext.IPZone;
import com.github.hiwepy.qqwry.spring.boot.ext.QQWry;

public class QQwry_Test {

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");

	public static void main(String args[]) throws IOException {

		QQWry qqwry = new QQWry(); // load qqwry.dat from classpath

		 //QQWry qqwry = new QQWry(Paths.get("D://qqwry.dat")); // load qqwry.dat from java.nio.file.Path

		// byte[] data = Files.readAllBytes(Paths.get("path/to/qqwry.dat"));
		 //QQWry qqwry = new QQWry(data); // create QQWry with provided data

		String myIP = "54.151.155.9";
		IPZone ipzone = qqwry.findIP(myIP);
		System.out.printf("%s : %s, %s", df.format(new Date()), ipzone.getMainInfo(), ipzone.getSubInfo());

	}

}
