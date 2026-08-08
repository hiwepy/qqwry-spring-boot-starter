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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link QQWry}.
 *
 * <p>Verifies all three constructors, the public {@code findIP} lookup and the
 * IP parsing validation. The bundled {@code qqwry.dat} on the classpath is used
 * as the data source so the binary search paths are exercised against real data.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("QQWry Tests")
class QQWryTest {

    private QQWry qqwry;

    @BeforeEach
    void setUp() throws IOException {
        // load qqwry.dat from classpath
        qqwry = new QQWry();
    }

    @Test
    @DisplayName("Default constructor loads qqwry.dat from classpath")
    void testClasspathConstructor() {
        assertThat(qqwry).isNotNull();
    }

    @Test
    @DisplayName("byte[] constructor builds an equivalent instance")
    void testByteArrayConstructor() throws IOException {
        byte[] data = readClasspathData();
        QQWry fromBytes = new QQWry(data);
        assertThat(fromBytes.findIP("127.0.0.1").getIp()).isEqualTo("127.0.0.1");
    }

    @Test
    @DisplayName("Path constructor reads the file from disk")
    void testPathConstructor(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("qqwry.dat");
        Files.write(file, readClasspathData());
        QQWry fromPath = new QQWry(file);
        IPZone zone = fromPath.findIP("127.0.0.1");
        assertThat(zone.getIp()).isEqualTo("127.0.0.1");
    }

    @Test
    @DisplayName("findIP returns a zone carrying the requested ip")
    void testFindIPReturnsZoneWithIp() {
        String ip = "127.0.0.1";
        IPZone zone = qqwry.findIP(ip);
        assertThat(zone.getIp()).isEqualTo(ip);
        // mainInfo is non-null for a known range
        assertThat(zone.getMainInfo()).isNotNull();
    }

    @Test
    @DisplayName("findIP resolves a public US address")
    void testFindIPPublicAddress() {
        IPZone zone = qqwry.findIP("54.151.155.9");
        assertThat(zone.getIp()).isEqualTo("54.151.155.9");
        assertThat(zone.toString()).isNotNull();
    }

    @Test
    @DisplayName("findIP resolves an Indonesian address (forces search traversal)")
    void testFindIPIndonesianAddress() {
        IPZone zone = qqwry.findIP("61.94.43.82");
        assertThat(zone.getIp()).isEqualTo("61.94.43.82");
    }

    @Test
    @DisplayName("findIP resolves a Chinese address")
    void testFindIPChineseAddress() {
        IPZone zone = qqwry.findIP("114.114.114.114");
        assertThat(zone.getIp()).isEqualTo("114.114.114.114");
    }

    @Test
    @DisplayName("findIP with an IP below the minimum range returns an empty-info zone")
    void testFindIPBelowMinimumRange() {
        IPZone zone = qqwry.findIP("0.0.0.0");
        assertThat(zone.getIp()).isEqualTo("0.0.0.0");
        assertThat(zone.toString()).isNotNull();
    }

    @Test
    @DisplayName("findIP with an IP above the maximum range returns an empty-info zone")
    void testFindIPAboveMaximumRange() {
        IPZone zone = qqwry.findIP("255.255.255.255");
        assertThat(zone.getIp()).isEqualTo("255.255.255.255");
    }

    @Test
    @DisplayName("findIP throws IllegalArgumentException when ip has too few parts")
    void testFindIPRejectsMalformedIpTooFewParts() {
        assertThatThrownBy(() -> qqwry.findIP("1.2.3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ip=1.2.3");
    }

    @Test
    @DisplayName("findIP throws IllegalArgumentException when ip has too many parts")
    void testFindIPRejectsMalformedIpTooManyParts() {
        assertThatThrownBy(() -> qqwry.findIP("1.2.3.4.5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ip=1.2.3.4.5");
    }

    /**
     * Reads the bundled {@code qqwry.dat} from the classpath into a byte array,
     * mirroring how the default constructor loads it.
     *
     * @return the raw qqwry.dat bytes
     * @throws IOException if the resource cannot be read
     */
    private static byte[] readClasspathData() throws IOException {
        try (java.io.InputStream in = QQWry.class.getClassLoader().getResourceAsStream("qqwry.dat");
             java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream(10 * 1024 * 1024)) {
            assertThat(in).as("qqwry.dat must be present on the classpath").isNotNull();
            byte[] buffer = new byte[4096];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            return out.toByteArray();
        }
    }
}
