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

package com.jrummyapps.htmlbuilder.demo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jrummyapps.android.util.HtmlBuilder;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView textView = (TextView) findViewById(R.id.textView);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
    textView.setText(buildDemoHtml());
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0, Menu.FIRST, 0, "GitHub").setIcon(R.drawable.ic_github).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case Menu.FIRST:
        try {
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jaredrummler/html-builder")));
        } catch (ActivityNotFoundException ignored) {
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private Spanned buildDemoHtml() {
    HtmlBuilder html = new HtmlBuilder();
    html.h1("Example Usage");

    html.h3().font("cursive", "Code:").close();
    html.font(0xFFCAE682, "HtmlBuilder")
        .append(' ')
        .font(0xFFD4C4A9, "html")
        .append(' ')
        .font(0xFF888888, "=")
        .append(" ")
        .font(0xFF33B5E5, "new")
        .append(" ")
        .font(0xFFCAE682, "HtmlBuilder")
        .append("()")
        .br();
    html.font(0xFFD4C4A9, "html")
        .append(".strong(")
        .font(0xFF95E454, "\"Strong text\"")
        .append(").br();")
        .br();
    html.font(0xFFD4C4A9, "html")
        .append(".font(")
        .font(0xFFCAE682, "Color")
        .append('.')
        .font(0xFF53DCCD, "RED")
        .append(", ")
        .font(0xFF95E454, "\"This will be red text\"")
        .append(");")
        .br();
    html.font(0xFFCAE682, "textView")
        .append(".setText(")
        .font(0xFFD4C4A9, "html")
        .append(".build());")
        .close()
        .br();

    html.h3().font("cursive", "Result:").close();
    html.strong("Strong text").br().font(Color.RED, "This will be red text");

    html.h1("Supported Tags");
    html.append("&lt;a href=&quot;...&quot;&gt;").br();
    html.append("&lt;b&gt;").br();
    html.append("&lt;big&gt;").br();
    html.append("&lt;blockquote&gt;").br();
    html.append("&lt;br&gt;").br();
    html.append("&lt;cite&gt;").br();
    html.append("&lt;dfn&gt;").br();
    html.append("&lt;div align=&quot;...&quot;&gt;").br();
    html.append("&lt;em&gt;").br();
    html.append("&lt;font color=&quot;...&quot; face=&quot;...&quot;&gt;").br();
    html.append("&lt;h1&gt;").br();
    html.append("&lt;h2&gt;").br();
    html.append("&lt;h3&gt;").br();
    html.append("&lt;h4&gt;").br();
    html.append("&lt;h5&gt;").br();
    html.append("&lt;h6&gt;").br();
    html.append("&lt;i&gt;").br();
    html.append("&lt;img src=&quot;...&quot;&gt;").br();
    html.append("&lt;p&gt;").br();
    html.append("&lt;small&gt;").br();
    html.append("&lt;strike&gt;").br();
    html.append("&lt;strong&gt;").br();
    html.append("&lt;sub&gt;").br();
    html.append("&lt;sup&gt;").br();
    html.append("&lt;tt&gt;").br();
    html.append("&lt;u&gt;").br();
    html.append("&ul;u&gt;").br();
    html.append("&li;u&gt;").br();

    html.h1("Links");
    html.p()
        .strong().a("https://twitter.com/jrummy16", "Twitter").close()
        .append("&nbsp;&nbsp;|&nbsp;&nbsp;")
        .strong().a("https://github.com/jaredrummler", "GitHub").close()
        .close();

    return html.build();
  }

}
