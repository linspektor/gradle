/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.env.internal;

import net.rubygrapefruit.platform.SystemInfo;
import org.gradle.env.Architecture;
import org.gradle.env.BuildEnvironment;
import org.gradle.env.OperatingSystem;

import javax.inject.Inject;

public class DefaultBuildEnvironment implements BuildEnvironment {

    private final SystemInfo systemInfo;

    private final org.gradle.internal.os.OperatingSystem operatingSystem;

    @Inject
    public DefaultBuildEnvironment(SystemInfo systemInfo, org.gradle.internal.os.OperatingSystem operatingSystem) {
        this.systemInfo = systemInfo;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public OperatingSystem getOperatingSystem() {
        if (operatingSystem.isWindows()) {
            return OperatingSystem.WINDOWS;
        } else if (operatingSystem.isMacOsX()) {
            return OperatingSystem.MAC_OS;
        } else if (operatingSystem.isLinux()) {
            return OperatingSystem.LINUX;
        }
        return OperatingSystem.OTHER;
    }

    @Override
    public String getOperatingSystemName() {
        return operatingSystem.getFamilyName();
    }

    @Override
    public Architecture getArchitecture() {
        switch (systemInfo.getArchitecture()) {
            case i386:
                return Architecture.I386;
            case amd64:
                return Architecture.AMD64;
            case aarch64:
                return Architecture.AARCH64;
        }
        return Architecture.OTHER;
    }

    @Override
    public String getArchitectureName() {
        return systemInfo.getArchitectureName();
    }
}
