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
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express of implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.easy4j.qqwry.spring.boot;

import io.github.easy4j.qqwry.spring.boot.ext.QQWry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link QQwryAutoConfiguration}.
 *
 * <p>Verifies bean registration and every branch of the {@code qqwry()} factory
 * method: classpath default, external file on disk, external resource on the
 * classpath and the fallback path when the external location cannot be resolved.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("QQwryAutoConfiguration Tests")
class QQwryAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(QQwryAutoConfiguration.class);

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        QQwryAutoConfiguration configuration = new QQwryAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("QQWry bean is registered and loads from classpath when external=false (default)")
    void testBeanLoadedFromClasspathByDefault() {
        runner
                .withPropertyValues("qqwry.external=false")
                .run(context -> {
                    assertThat(context).hasSingleBean(QQWry.class);
                    QQWry qqwry = context.getBean(QQWry.class);
                    assertThat(qqwry.findIP("127.0.0.1").getIp()).isEqualTo("127.0.0.1");
                });
    }

    @Test
    @DisplayName("QQWry bean is registered when no properties are set (defaults apply)")
    void testBeanLoadedWithNoProperties() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(QQWry.class);
            assertThat(context.getBean(QQWry.class)).isNotNull();
        });
    }

    @Test
    @DisplayName("QQWry bean loads from an external file on disk when external=true")
    void testBeanLoadedFromExternalFile(@TempDir Path tempDir) throws Exception {
        // copy the bundled qqwry.dat to a temp file so the File path is taken
        Path externalFile = tempDir.resolve("qqwry.dat");
        Files.write(externalFile, readClasspathData());

        runner
                .withPropertyValues(
                        "qqwry.external=true",
                        "qqwry.location=" + externalFile.toAbsolutePath().toString())
                .run(context -> {
                    assertThat(context).hasSingleBean(QQWry.class);
                    QQWry qqwry = context.getBean(QQWry.class);
                    assertThat(qqwry.findIP("127.0.0.1").getIp()).isEqualTo("127.0.0.1");
                });
    }

    @Test
    @DisplayName("QQWry bean loads from a classpath resource when external=true and location is a resource")
    void testBeanLoadedFromExternalResource() {
        runner
                .withPropertyValues(
                        "qqwry.external=true",
                        "qqwry.location=classpath:qqwry.dat")
                .run(context -> {
                    assertThat(context).hasSingleBean(QQWry.class);
                    QQWry qqwry = context.getBean(QQWry.class);
                    assertThat(qqwry.findIP("127.0.0.1").getIp()).isEqualTo("127.0.0.1");
                });
    }

    @Test
    @DisplayName("QQWry bean falls back to classpath when external data is malformed")
    void testBeanFallsBackWhenExternalDataMalformed(@TempDir Path tempDir) throws Exception {
        // an existing file shorter than 8 bytes makes new QQWry(bytes) throw while
        // reading the index header/tail, which is caught and falls back to loading
        // qqwry.dat from the classpath.
        Path bogusFile = tempDir.resolve("qqwry.dat");
        Files.write(bogusFile, new byte[]{1, 2, 3, 4});

        runner
                .withPropertyValues(
                        "qqwry.external=true",
                        "qqwry.location=" + bogusFile.toAbsolutePath().toString())
                .run(context -> {
                    assertThat(context).hasSingleBean(QQWry.class);
                    QQWry qqwry = context.getBean(QQWry.class);
                    assertThat(qqwry.findIP("127.0.0.1").getIp()).isEqualTo("127.0.0.1");
                });
    }

    @Test
    @DisplayName("QQwryProperties bean is also registered")
    void testPropertiesBeanRegistered() {
        runner.run(context ->
                assertThat(context).hasSingleBean(QQwryProperties.class));
    }

    /**
     * Reads the bundled {@code qqwry.dat} from the classpath into a byte array.
     *
     * @return the raw qqwry.dat bytes
     * @throws Exception if the resource cannot be read
     */
    private static byte[] readClasspathData() throws Exception {
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
