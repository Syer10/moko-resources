/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import dev.icerock.gradle.MRVisibility

plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform-resources")
    id("detekt-convention")
}

dependencies {
    commonMainApi(projects.resources)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.icerockdev.library.nested"
    multiplatformResourcesClassName = "NestedMR"
    multiplatformResourcesVisibility = MRVisibility.Internal
}
