# HTML Builder

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.jaredrummler/html-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.jaredrummler/html-builder)
[![License](http://img.shields.io/:license-apache-blue.svg)](LICENSE)
[![API](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://developer.android.com/reference/android/os/Build.VERSION_CODES.html#GINGERBREAD) 
[![Twitter Follow](https://img.shields.io/twitter/follow/jaredrummler.svg?style=social)](https://twitter.com/jaredrummler)

Build valid HTML for Android TextView.

![Screenshot](art/screenshot.png)

## Description

There is a lovely method on the android.text.Html class, fromHtml(), that converts HTML into a Spannable for use with a TextView.

However, the documentation does not stipulate what HTML tags are supported, which makes this method a bit hit-or-miss. This small library provides a fluent API for building valid HTML for android.widget.TextView.

## Usage

```java
HtmlBuilder html = new HtmlBuilder();
html.p("Lorem ipsum dolor sit amet, denique detraxit reprimique quo in. Ius dicat omnes mucius cu.");
html.font().color("red").face("sans-serif-condensed").text("Red Text").close();
textView.setText(html.build());
```

## HTML Tags Supported by TextView

<ul>
 <li><code>&lt;a href=&quot;...&quot;&gt;</code></li>
 <li><code>&lt;b&gt;</code></li>
 <li><code>&lt;big&gt;</code></li>
 <li><code>&lt;blockquote&gt;</code></li>
 <li><code>&lt;br&gt;</code></li>
 <li><code>&lt;cite&gt;</code></li>
 <li><code>&lt;dfn&gt;</code></li>
 <li><code>&lt;div align=&quot;...&quot;&gt;</code></li>
 <li><code>&lt;em&gt;</code></li>
 <li><code>&lt;font color=&quot;...&quot; face=&quot;...&quot;&gt;</code></li>
 <li><code>&lt;h1&gt;</code></li>
 <li><code>&lt;h2&gt;</code></li>
 <li><code>&lt;h3&gt;</code></li>
 <li><code>&lt;h4&gt;</code></li>
 <li><code>&lt;h5&gt;</code></li>
 <li><code>&lt;h6&gt;</code></li>
 <li><code>&lt;i&gt;</code></li>
 <li><code>&lt;img src=&quot;...&quot;&gt;</code></li>
 <li><code>&lt;p&gt;</code></li>
 <li><code>&lt;small&gt;</code></li>
 <li><code>&lt;strike&gt;</code></li>
 <li><code>&lt;strong&gt;</code></li>
 <li><code>&lt;sub&gt;</code></li>
 <li><code>&lt;sup&gt;</code></li>
 <li><code>&lt;tt&gt;</code></li>
 <li><code>&lt;u&gt;</code></li>
 <li><code>&lt;ul&gt;</code> (Android 7.0+)</li>
 <li><code>&lt;li&gt;</code> (Android 7.0+)</li>
 </ul>

## Download

Download [the latest AAR](https://repo1.maven.org/maven2/com/jaredrummler/html-builder/1.0.0/html-builder-1.0.0.aar) or grab via Gradle:

```groovy
compile 'com.jaredrummler:html-builder:1.0.0'
```
or Maven:
```xml
<dependency>
  <groupId>com.jaredrummler</groupId>
  <artifactId>html-builder</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```

## License

    Copyright 2016 Jared Rummler

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
