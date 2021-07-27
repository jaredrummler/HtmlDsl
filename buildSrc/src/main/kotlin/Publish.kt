/*
 * Copyright (C) 2021 Jared Rummler
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

object Publish {
    const val PUBLISH_GROUP_ID = "com.jaredrummler"
    const val PUBLISH_ARTIFACT_ID = "html-dsl"
    const val PUBLISH_VERSION = "1.0.0"

    const val DESCRIPTION = "DSL to build valid HTML for Android TextView"
    const val URL = "https://github.com/jaredrummler/HtmlDsl"

    data class VersionControl(
        val connection: String, val developerConnection: String, val url: String
    )

    val github = VersionControl(
        "scm:git:github.com/jaredrummler/HtmlDsl.git",
        "scm:git:ssh://github.com/jaredrummler/HtmlDsl.git",
        "https://github.com/jaredrummler/ktsh/tree/main"
    )
}