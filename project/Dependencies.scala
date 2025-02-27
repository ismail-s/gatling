import sbt._

object Dependencies {
  // Compile dependencies

  // format: OFF
  private def scalaReflect(version: String)  = "org.scala-lang"                       % "scala-reflect"                     % version
  private def scalaCompiler(version: String) = ("org.scala-lang"                      % "scala-compiler"                    % version)
    .exclude("org.jline", "jline")
  private val scalaSwing                     = "org.scala-lang.modules"              %% "scala-swing"                       % "3.0.0"
  private val scalaParserCombinators         = "org.scala-lang.modules"              %% "scala-parser-combinators"          % "2.3.0"
  private val netty                          = "io.netty"                             % "netty-codec-http"                  % "4.1.96.Final"
  private val nettyBuffer                    = netty.withName("netty-buffer")
  private val nettyHandler                   = netty.withName("netty-handler")
  private val nettyMqtt                      = netty.withName("netty-codec-mqtt")
  private val nettyProxy                     = netty.withName("netty-handler-proxy")
  private val nettyDns                       = netty.withName("netty-resolver-dns")
  private val nettyEpollLinuxX86             = netty.withName("netty-transport-native-epoll")                               classifier "linux-x86_64"
  private val nettyEpollLinuxArm             = netty.withName("netty-transport-native-epoll")                               classifier "linux-aarch_64"
  private val nettyIoUringLinuxX86           = "io.netty.incubator"                   % "netty-incubator-transport-native-io_uring" % "0.0.21.Final" classifier "linux-x86_64"
  private val nettyIoUringLinuxArm           = nettyIoUringLinuxX86                                                                                  classifier "linux-aarch_64"
  private val nettyHttp2                     = netty.withName("netty-codec-http2")
  private val nettyResolverNativeOsXX86      = netty.withName("netty-resolver-dns-native-macos") classifier "osx-x86_64"
  private val nettyResolverNativeOsXArm      = nettyResolverNativeOsXX86                                 classifier "osx-aarch_64"
  private val nettyTcNative                  = netty.organization                     % "netty-tcnative-classes"            % "2.0.61.Final"
  private val nettyTcNativeBoringSsl         = nettyTcNative.withName("netty-tcnative-boringssl-static")
  private val nettyTcNativeBoringSslLinuxX86 = nettyTcNativeBoringSsl  classifier "linux-x86_64"
  private val nettyTcNativeBoringSslLinuxArm = nettyTcNativeBoringSsl  classifier "linux-aarch_64"
  private val nettyTcNativeBoringSslOsXX86   = nettyTcNativeBoringSsl  classifier "osx-x86_64"
  private val nettyTcNativeBoringSslOsXArm   = nettyTcNativeBoringSsl  classifier "osx-aarch_64"
  private val nettyTcNativeBoringSslWindows  = nettyTcNativeBoringSsl  classifier "windows-x86_64"
  private val brotli4j                       = "com.aayushatharva.brotli4j"           % "brotli4j"                          % "1.12.0"
  private val brotli4jLinuxX86               = brotli4j.withName("native-linux-x86_64")
  private val brotli4jLinuxArm               = brotli4j.withName("native-linux-aarch64")
  private val brotli4cOsXX86                 = brotli4j.withName("native-osx-x86_64")
  private val brotli4cOsXArm                 = brotli4j.withName("native-osx-aarch64")
  private val brotli4jWindows                = brotli4j.withName("native-windows-x86_64")
  private val akka                           = "com.typesafe.akka"                   %% "akka-actor"                        % "2.6.20"
  private val akkaSlf4j                      = akka.withName("akka-slf4j")
  private val config                         = "com.typesafe"                         % "config"                            % "1.4.2"
  private val saxon                          = "net.sf.saxon"                         % "Saxon-HE"                          % "10.6"
  private val slf4jApi                       = "org.slf4j"                            % "slf4j-api"                         % "1.7.36"
  private val spire                          = ("org.typelevel"                      %% "spire-macros"                      % "0.17.0")
    .exclude("org.typelevel", "machinist_2.13")
    .exclude("org.typelevel", "algebra_2.13")
    .exclude("org.scala-lang.modules", "scala-collection-compat_2.13")
  private val scopt                          = "com.github.scopt"                    %% "scopt"                             % "3.7.1"
  private val scalaLogging                   = "com.typesafe.scala-logging"          %% "scala-logging"                     % "3.9.5"
  private val jackson                        = "com.fasterxml.jackson.core"           % "jackson-databind"                  % "2.15.2"
  private val sfm                            = ("org.simpleflatmapper"                % "lightning-csv"                     % "8.2.3")
    .exclude("org.simpleflatmapper", "ow2-asm")
  private val lagarto                        = "org.jodd"                             % "jodd-lagarto"                      % "6.0.6"
  private val jmespath                       = "io.burt"                              % "jmespath-jackson"                  % "0.5.1"
  private val boopickle                      = "io.suzaku"                           %% "boopickle"                         % "1.3.3"
  private val redisClient                    = "net.debasishg"                       %% "redisclient"                       % "3.42"
  private val zinc                           = ("org.scala-sbt"                      %% "zinc"                              % "1.9.3")
    .exclude("org.jline", "jline")
    .exclude("org.scala-sbt.jline3", "jline-terminal")
    .exclude("org.jline", "jline-terminal-jna")
    .exclude("org.jline", "jline-terminal-jansi")
    .exclude("org.scala-sbt.jline", "jline")
    .exclude("org.scala-lang.modules", "scala-parser-combinators_2.13")
    .exclude("org.scala-lang.modules", "scala-xml_2.13")
    .exclude("org.scala-sbt", "launcher-interface")
    .exclude("org.scala-sbt", "sbinary_2.13")
    .exclude("org.scala-sbt", "zinc-ivy-integration_2.13")
    .exclude("com.eed3si9n", "sjson-new-core_2.13")
    .exclude("com.eed3si9n", "sjson-new-scalajson_2.13")
    .exclude("com.lihaoyi", "fastparse_2.13")
    .exclude("com.lmax", "disruptor")
    .exclude("org.apache.logging.log4j", "log4j-api")
    .exclude("org.apache.logging.log4j", "log4j-core")
  private val compilerBridge                 = zinc.withName("compiler-bridge")
  private val testInterface                  = zinc.organization                      % "test-interface"                    % "1.0"
  private val jmsApi                         = "javax.jms"                            % "javax.jms-api"                     % "2.0.1"
  private val logback                        = "ch.qos.logback"                       % "logback-classic"                   % "1.2.12"
  private val tdigest                        = "com.tdunning"                         % "t-digest"                          % "3.1"
  private val hdrHistogram                   = "org.hdrhistogram"                     % "HdrHistogram"                      % "2.1.12"
  private val caffeine                       = "com.github.ben-manes.caffeine"        % "caffeine"                          % "2.9.3"
  private val bouncyCastle                   = "io.gatling"                           % "gatling-recorder-bc-shaded"        % "1.76"
  private val quicklens                      = "com.softwaremill.quicklens"          %% "quicklens"                         % "1.9.6"
  private val fastUuid                       = "com.eatthepath"                       % "fast-uuid"                         % "0.2.0"
  private val pebble                         = "io.pebbletemplates"                   % "pebble"                            % "3.2.1"
  private val spotbugs                       = "com.github.spotbugs"                  % "spotbugs-annotations"              % "4.7.3"
  private val typetools                      = "net.jodah"                            % "typetools"                         % "0.6.3"
  private val gatlingEnterprisePluginCommons = "io.gatling"                           % "gatling-enterprise-plugin-commons" % "1.6.0"

  // Test dependencies
  private val scalaTest                      = "org.scalatest"                       %% "scalatest"                         % "3.2.16"            % Test
  private val scalaTestScalacheck            = "org.scalatestplus"                   %% "scalacheck-1-16"                   % "3.2.14.0"          % Test
  private val scalaTestMockito               = scalaTestScalacheck.organization      %% "mockito-3-4"                       % "3.2.10.0"          % Test
  private val scalaCheck                     = "org.scalacheck"                      %% "scalacheck"                        % "1.17.0"            % Test
  private val akkaTestKit                    = akka.withName("akka-testkit")                                                              % Test
  private val mockitoCore                    = "org.mockito"                          % "mockito-core"                      % "4.11.0"             % Test
  private val activemqBroker                 = ("org.apache.activemq"                 % "activemq-broker"                   % "5.16.6"            % Test)
    .exclude("org.apache.geronimo.specs", "geronimo-jms_1.1_spec")
  private val h2                             = "com.h2database"                       % "h2"                                % "2.2.220"           % Test
  private val jmh                            = "org.openjdk.jmh"                      % "jmh-core"                          % "1.27"

  private val junit                          = "org.junit.jupiter"                    % "junit-jupiter-api"                 % "5.10.0"             % Test
  private val junitEngine                    = junit.withName("junit-jupiter-engine")
  private val jupiterInterface               = "net.aichler"                          % "jupiter-interface"                 % "0.11.1"            % Test

  private val jetty                          = "org.eclipse.jetty"                    % "jetty-server"                      % "9.4.51.v20230217"  % Test
  private val jettyProxy                     = jetty.organization                     % "jetty-proxy"                       % jetty.revision      % Test

  // Docs dependencies
  private val commonsIo                      = "commons-io"                           % "commons-io"                        % "2.13.0"
  private val commonsLang                    = "org.apache.commons"                   % "commons-lang3"                     % "3.13.0"
  private val commonsCodec                   = "commons-codec"                        % "commons-codec"                     % "1.16.0"

  // format: ON
  private val loggingDeps = Seq(slf4jApi, scalaLogging, logback)
  private val testDeps = Seq(
    scalaTest,
    scalaTestScalacheck,
    scalaTestMockito,
    scalaCheck,
    akkaTestKit,
    mockitoCore
  )
  private val parserDeps = Seq(jackson, saxon, lagarto, jmespath)

  // Dependencies by module

  val docSamplesDependencies =
    Seq(commonsIo, commonsLang, commonsCodec)

  val nettyUtilDependencies =
    Seq(nettyBuffer, nettyEpollLinuxX86, nettyEpollLinuxArm, nettyIoUringLinuxX86, nettyIoUringLinuxArm, junit, junitEngine, jupiterInterface)

  def commonsSharedDependencies(scalaVersion: String) =
    Seq(scalaReflect(scalaVersion), boopickle) ++ testDeps

  val commonsSharedUnstableDependencies = testDeps

  val commonsDependencies =
    Seq(config, spire) ++ loggingDeps ++ testDeps

  val jsonpathDependencies =
    Seq(scalaParserCombinators, jackson) ++ testDeps

  val coreDependencies =
    Seq(
      akka,
      akkaSlf4j,
      sfm,
      caffeine,
      pebble,
      scalaParserCombinators,
      scopt,
      nettyHandler,
      nettyTcNative,
      quicklens
    ) ++
      parserDeps ++ testDeps

  val defaultJavaDependencies =
    Seq(spotbugs, junit, junitEngine, jupiterInterface) ++ testDeps

  val coreJavaDependencies =
    Seq(typetools) ++ defaultJavaDependencies

  val redisDependencies = redisClient +: testDeps

  val httpClientDependencies = Seq(
    netty,
    nettyBuffer,
    nettyHandler,
    nettyProxy,
    nettyDns,
    nettyEpollLinuxX86,
    nettyEpollLinuxArm,
    nettyHttp2,
    nettyResolverNativeOsXX86,
    nettyResolverNativeOsXArm,
    nettyTcNative,
    nettyTcNativeBoringSsl,
    nettyTcNativeBoringSslLinuxX86,
    nettyTcNativeBoringSslLinuxArm,
    nettyTcNativeBoringSslOsXX86,
    nettyTcNativeBoringSslOsXArm,
    nettyTcNativeBoringSslWindows,
    brotli4j,
    brotli4jLinuxX86,
    brotli4jLinuxArm,
    brotli4cOsXX86,
    brotli4cOsXArm,
    brotli4jWindows,
    junit,
    junitEngine,
    jupiterInterface,
    jetty,
    jettyProxy
  ) ++ loggingDeps

  val httpDependencies = Seq(saxon) ++ testDeps

  val jmsDependencies = Seq(jmsApi, fastUuid, activemqBroker) ++ testDeps

  val jdbcDependencies = h2 +: testDeps

  val mqttDependencies = Seq(nettyHandler, nettyTcNative, nettyMqtt, nettyEpollLinuxX86, nettyEpollLinuxArm)

  val chartsDependencies = tdigest +: testDeps

  val graphiteDependencies = hdrHistogram +: testDeps

  val benchmarkDependencies = Seq(jmh)

  val bundleDependencies = gatlingEnterprisePluginCommons +: testDeps

  def compilerDependencies(scalaVersion: String) =
    Seq(
      scalaCompiler(scalaVersion),
      scalaReflect(scalaVersion),
      config,
      slf4jApi,
      logback,
      zinc,
      compilerBridge,
      scopt
    )

  val recorderDependencies = Seq(scalaSwing, jackson, bouncyCastle, netty, akka) ++ testDeps

  val testFrameworkDependencies = Seq(testInterface)

  val docDependencies = Seq(activemqBroker)
}
