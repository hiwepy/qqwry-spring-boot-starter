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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link IPZone}.
 *
 * <p>Verifies construction, default values, getters/setters and the toString contract.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("IPZone Tests")
class IPZoneTest {

    private static final String IP = "192.168.1.1";

    private IPZone zone;

    @BeforeEach
    void setUp() {
        zone = new IPZone(IP);
    }

    @Test
    @DisplayName("Constructor stores the ip and returns it via getter")
    void testGetIp() {
        assertThat(zone.getIp()).isEqualTo(IP);
    }

    @Test
    @DisplayName("Default mainInfo is empty string")
    void testDefaultMainInfo() {
        assertThat(zone.getMainInfo()).isEmpty();
    }

    @Test
    @DisplayName("Default subInfo is empty string")
    void testDefaultSubInfo() {
        assertThat(zone.getSubInfo()).isEmpty();
    }

    @Test
    @DisplayName("Setter for mainInfo updates the value")
    void testSetMainInfo() {
        zone.setMainInfo("IANA");
        assertThat(zone.getMainInfo()).isEqualTo("IANA");
    }

    @Test
    @DisplayName("Setter for subInfo updates the value")
    void testSetSubInfo() {
        zone.setSubInfo("保留地址");
        assertThat(zone.getSubInfo()).isEqualTo("保留地址");
    }

    @Test
    @DisplayName("toString concatenates mainInfo and subInfo")
    void testToStringConcatenates() {
        zone.setMainInfo("Main");
        zone.setSubInfo("Sub");
        assertThat(zone.toString()).isEqualTo("MainSub");
    }

    @Test
    @DisplayName("toString with default empty values produces empty string")
    void testToStringEmptyByDefault() {
        assertThat(zone.toString()).isEmpty();
    }
}
