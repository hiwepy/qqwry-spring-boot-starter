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
package io.github.easy4j.qqwry.spring.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link QQwryProperties}.
 *
 * <p>Verifies default values, getters/setters and the configuration prefix constant.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("QQwryProperties Tests")
class QQwryPropertiesTest {

    private QQwryProperties properties;

    @BeforeEach
    void setUp() {
        properties = new QQwryProperties();
    }

    @Test
    @DisplayName("Configuration prefix is 'qqwry'")
    void testPrefix() {
        assertThat(QQwryProperties.PREFIX).isEqualTo("qqwry");
    }

    @Test
    @DisplayName("Default value of external is false")
    void testDefaultExternal() {
        assertThat(properties.isExternal()).isFalse();
    }

    @Test
    @DisplayName("Setter for external updates the value")
    void testSetExternal() {
        properties.setExternal(true);
        assertThat(properties.isExternal()).isTrue();
    }

    @Test
    @DisplayName("Default value of location is 'classpath:qqwry.dat'")
    void testDefaultLocation() {
        assertThat(properties.getLocation()).isEqualTo("classpath:qqwry.dat");
    }

    @Test
    @DisplayName("Setter for location updates the value")
    void testSetLocation() {
        properties.setLocation("/etc/qqwry/qqwry.dat");
        assertThat(properties.getLocation()).isEqualTo("/etc/qqwry/qqwry.dat");
    }
}
