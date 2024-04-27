/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.gradle.utils

import dev.icerock.gradle.metadata.resource.ImageMetadata.Appearance
import org.apache.commons.text.StringEscapeUtils
import java.util.Locale

/**
 * Replace all new lines including space characters before and after.
 * This is required to remove the IDE indentation.
 */
internal fun String.removeLineWraps(): String {
    return replace(Regex("\\s*\n\\s*"), " ")
}

internal fun String.processXmlTextContent(strictLineBreaks: Boolean): String {
    return if (strictLineBreaks) {
        this
    } else {
        this.removeLineWraps()
    }.replace("\\n", "\n")
}

internal val String.withoutScale
    get() = substringBefore("@")

internal fun String.capitalize(): String {
    return replaceFirstChar { char ->
        if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString()
    }
}

internal fun String.decapitalize(): String {
    return replaceFirstChar { it.lowercase(Locale.ROOT) }
}

internal fun String.remove(char: Char): String {
    return this.remove(char.toString())
}

internal fun String.remove(char: String): String {
    return this.replace(char, "")
}

internal val String.flatName: String
    get() = this.remove('.')

internal fun String.convertXmlStringToLocalizationValue(): String {
    return StringEscapeUtils.unescapeXml(this).let {
        StringEscapeUtils.escapeEcmaScript(it)
    }
}

internal val String.appearance: Appearance
    get() {
        return if (withoutScale.endsWith(suffix = Appearance.DARK.suffix, ignoreCase = true)) {
            Appearance.DARK
        } else {
            Appearance.LIGHT
        }
    }

internal val String.withoutAppearance: String
    get() {
        // If name doesn't contains potential suffix - early return
        if (!this.contains('-')) return this

        Appearance.values().forEach { type ->
            val typeSuffix: String = type.suffix
            // Find theme suffix in end of file name
            // that exclude invalid result for names like: samurai-dark-japan-dark.svg
            val latestIncludeIndex: Int = lastIndexOf(string = typeSuffix, ignoreCase = true)

            // Skip value if not found
            if (latestIncludeIndex == -1) return@forEach

            val nameWithAppearanceLength: Int = latestIncludeIndex + typeSuffix.length
            // Check correct is founded suffix
            val latestSuffixIsTheme: Boolean = length == nameWithAppearanceLength
            // If theme suffix is found return clean name
            if (latestSuffixIsTheme) {
                return removeRange(
                    startIndex = latestIncludeIndex,
                    endIndex = nameWithAppearanceLength
                )
            }
        }

        return this
    }
