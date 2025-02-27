/*
 * Copyright 2011-2023 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.http.funspec

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.css.{ CssCheckType, CssOfType }
import io.gatling.http.Predef._
import io.gatling.http.funspec.GatlingHttpFunSpecCompileTest._
import io.gatling.http.protocol.HttpProtocolBuilder

import jodd.lagarto.dom.NodeSelector

class GatlingHttpFunSpecCompileTest extends GatlingHttpFunSpec {
  override val baseUrl: String = "http://example.com"
  override def httpProtocol: HttpProtocolBuilder = super.httpProtocol.header("MyHeader", "MyValue")

  spec {
    http("Index test")
      .get("/index.html")
      .check(h1.exists)
  }
}

object GatlingHttpFunSpecCompileTest {
  def h1: CheckBuilder.MultipleFind[CssCheckType, NodeSelector, String] with CssOfType = css("h1")
}
