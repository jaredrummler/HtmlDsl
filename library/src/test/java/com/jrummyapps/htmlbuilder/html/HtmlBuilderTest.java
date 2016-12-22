/*
 * Copyright (C) 2016 JRummy Apps Inc.
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
 * Raw
 */

package com.jrummyapps.htmlbuilder.html;

import com.jrummyapps.android.util.HtmlBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlBuilderTest {

  @Test public void anchor_isExpectedHtml() {
    HtmlBuilder html = new HtmlBuilder();
    html.a("https://www.google.com/", "Google");
    assertEquals(html.toString(), "<a href=\"https://www.google.com/\">Google</a>");
  }

  @Test public void bold_isExpectedHtml() {
    HtmlBuilder html = new HtmlBuilder();
    html.b("bold text");
    assertEquals(html.toString(), "<b>bold text</b>");
  }

  @Test public void headers_isExpectedHtml() {
    HtmlBuilder html = new HtmlBuilder();
    html.h1("This is heading 1");
    html.h2("This is heading 2");
    html.h3("This is heading 3");
    html.h4("This is heading 4");
    html.h5("This is heading 5");
    html.h6("This is heading 6");
    assertEquals(html.toString(),
        "<h1>This is heading 1</h1><h2>This is heading 2</h2><h3>This is heading 3</h3><h4>This is heading 4</h4><h5>This is heading 5</h5><h6>This is heading 6</h6>");

  }

  @Test public void img_isExpectedHtml() {
    HtmlBuilder html = new HtmlBuilder.Img().src("smiley.gif").alt("Smiley face").height(42).width(42).close();
    assertEquals(html.toString(), "<img src=\"smiley.gif\" alt=\"Smiley face\" height=\"42\" width=\"42\">");
  }

  @Test public void font_isExpectedHtml() {
    HtmlBuilder html = new HtmlBuilder();
    html.font().size(3).color("red").text("This is some text!").close().append('\n');
    html.font().size(2).color("blue").text("This is some text!").close().append('\n');
    html.font().face("verdana").color("green").text("This is some text!").close();
    assertEquals(html.toString(), "<font size=\"3\" color=\"red\">This is some text!</font>\n" +
        "<font size=\"2\" color=\"blue\">This is some text!</font>\n" +
        "<font face=\"verdana\" color=\"green\">This is some text!</font>");
  }

  @SuppressWarnings("NewApi")
  @Test public void unorderedList_isExpectedHtml() {
    HtmlBuilder html = new HtmlBuilder();
    html.ul()
        .li("Coffee")
        .li("Tea")
        .li("Milk")
        .close();
    assertEquals(html.toString(), "<ul><li>Coffee</li><li>Tea</li><li>Milk</li></ul>");
  }

  @Test public void loremIpsum_isExpectedHtml() {
    // Lorem Ipsum with p, b, and i tags.
    HtmlBuilder html = new HtmlBuilder();
    html.p()
        .append(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. ")
        .b("Lorem ipsum dolor sit amet, consectetur adipiscing elit")
        .append(
            ". Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. ")
        .close();
    html.append("\n\n");
    html.p().append(
        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. ")
        .b("Lorem ipsum dolor sit amet, consectetur adipiscing elit")
        .append(". Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. ")
        .b("Fusce nec tellus sed augue semper porta")
        .append(
            ". Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. ")
        .i("Lorem ipsum dolor sit amet, consectetur adipiscing elit")
        .append(
            ". Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh. ")
        .close();
    html.append("\n\n");
    final String LOREM_ESPUM =
        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. <b>Lorem ipsum dolor sit amet, consectetur adipiscing elit</b>. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. </p>\n\n<p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. <b>Lorem ipsum dolor sit amet, consectetur adipiscing elit</b>. Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. <b>Fusce nec tellus sed augue semper porta</b>. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. <i>Lorem ipsum dolor sit amet, consectetur adipiscing elit</i>. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh. </p>\n\n";
    assertEquals(html.toString(), LOREM_ESPUM);
  }

}
