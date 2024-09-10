# Java

<!-- npm i --save markdown-toc -->

<!-- toc -->

- [Java Overview](#java-overview)
- [Java Architecture](#java-architecture)
- [Java Features](#java-features)
    * [Java Releases](#java-releases)
    * [Java `invokedynamic`](#java-invokedynamic)
- [Java Examples](#java-examples)
    * [Java Persistence](#java-persistence)
    * [Java Web](#java-web)
    * [Java Functional](#java-functional)
- [Java Sources](#java-sources)
    * [Java Articles](#java-articles)

<!-- tocstop -->

## Java Overview

* Release => 1995
* Solutions
    * Web
* Inspirations
    * C/C++
    * Smalltalk

## Java Architecture

* Historical Implementations
    * <1.5 => Early
    * 1.5-1.8 => Generics, Annotations, Lambda
    * 9+ => JPMS, JShell, `invokedynamic`, G1GC
    * 11+ => HTTP Client, ZGC, Java EE Deprecation
    * 17+ => LTS, Switch-Pattern Match Incubation
    * 21+ => String Templates, Sequence Collection, Key Encapsulation API
* Changes
    * Java EE Deprecation
    * Release History & Schedule

## Java Features

### Java Releases

| Version | Release Date | Features                                                                                                               | LTS |
|---------|--------------|------------------------------------------------------------------------------------------------------------------------|-----|
| 1.0     | 1996         | OOP, Bytecode                                                                                                          |     |
| 1.1     | 1997         | Inner Classes, Reflection, Java Beans, JDBC API                                                                        |     |
| 1.2     | 1998         | Collections Framework, String Internment, JIT compiler, Swing API                                                      |     |
| 1.3     | 2000         | HotSpot JVM, JNDI, Java Platform Debugger Architecture                                                                 |     |
| 1.4     | 2002         | Regular Expressions, Exception Chaining, NIO Interface, Logging API Changes                                            |     |
| 1.5     | 2004         | Generics, Enhanced for-loop, Autoboxing, Static Import, Varargs, Enumerations, Annotations                             |     |
| 1.6     | 2006         | Scripting Language Support, Web Service Enhancements,  JDBC 4.0 & SQL XML Support                                      |     | 
| 1.7     | 2011         | try-with-resources, String Switch, Diamond Operator, `invokedynamic`, Exception Handling & File I/O Enhancements, G1GC |     |
| 1.8     | 2014         | Lambda Expressions, Date and Time API Enhancements                                                                     | X   |
| 9       | 2017         | Java Platform Module System (JPMS), JShell, G1GC Official / CMS GC Deprecated                                          |     | 
| 10      | Mar 2018     | Local-Variable Type Inference, Immutable Collections                                                                   |     |
| 11      | Sep 2018     | Official New HTTP Client, Open Java Flight Recorder, ZGC Linux, Java EE & Other Deprecations                           | X   |
| 12      | Mar 2019     | Switch Expressions, Minor Language Enhancements, Shenandoah Low-Pause GC                                               |     | 
| 13      | Sep 2019     | Switch Expression Yield, Text Blocks, Socket API Reimplementation                                                      |     |
| 14      | Mar 2020     | Records, Instance Pattern Matching, ZGC Multi-Platform, Native Memory API Enhancments                                  |     |
| 15      | Sep 2020     | Records Official, Sealed Classes, Pattern Matching Types, ZGC & Shenandoah Official                                    |     |
| 16      | Mar 2021     | Vector API Incubation Records / Sealed Classes / Pattern Matching Enhancements                                         |     |
| 17      | Sep 2021     | LTS release with Minor Enhancements / Deprecations, Switch-Pattern Match Incubation                                    | X   |   
| 18      | Mar 2022     | Simple Web Server, Default UTF-8, Internet Address SPI, Other Enhancements                                             |     |
| 19      | Sep 2022     | Record Patterns / Virtual Threads / Structured Concurrency Incubation, Other Enhancements                              |     |
| 20      | Mar 2023     | Scoped Values, Other Enhancements                                                                                      |     |
| 21      | Sep 2023     | String Templates, Sequence Collection, Key Encapsulation API                                                           | X   |
| 22      | Mar 2024     | Unnamed Variables, Statements before super, Stream Gather, FFI Official                                                |     |
| 23      | ---          | Markdown Docs,                                                                                                         |     |

* Projects
    * _Project Coin_
    * _Project Amber_ => Sealed Classes
    * _Project Loom_ => Fibers, Structured Concurrency, & Scoped Values
    * _Project Panama_ => Foreign Function Interface (FFI) & Vector API
    * _Project Valhalla_ => Value Types

### Java `invokedynamic`

* JVM Method Invocation Types
    * `invokevirtual` => Normal method
    * `invokestatic` => Static method
    * `invokeinterface` => Interface method
    * `invokespecial` => Constructor / `private` method
    * `invokedynamic` => Dynamic method
        * Avoid reflection inefficiencies
        * Abstract dynamic methods and code generation into `CallSite` / `MethodHandle` objects

### Java Domains

| Domain     | Sample Libraries                         | 
|------------|------------------------------------------|
| Framework  | Spring Boot, Micronaut, Quarkus          |
| Web        | Servlets, Spring, Reactor, Dropwizard    |
| Cloud      | Spring Cloud, Micronaut, Netflix OSS     |
| Networking | Netty, okhttp, Jetty, Tomcat             |
| Scheduling | Quartz, Spring Batch, Akka               |
| Data       | Hibernate, JPA, JDBI                     |
| Caching    | Caffeine, Guava, Ehcache                 |
| Encoding   | Jackson, Gson, Protobuf                  |
| Testing    | JUnit, Mockito, TestNG                   |
| Build      | Maven, Gradle                            |
| Logging    | Log4j, Logback, SLF4J                    |
| Security   | Spring Security, Bouncy Castle, Keycloak |
| Messaging  | Kafka, RabbitMQ, ActiveMQ                |
| Monitoring | Prometheus, Grafana, Jaeger              |

### Java Articles

* [Wikipedia Java (Programming Language)](https://en.wikipedia.org/wiki/Java_%28programming_language%29)
* [Wikipedia Java (Software Platform)](https://en.wikipedia.org/wiki/Java_(software_platform))
* [Baeldung](https://www.baeldung.com/)
* [Baeldung Netty Reactor](https://www.baeldung.com/spring-boot-reactor-netty)
* [Baeldung invokedynamic](https://www.baeldung.com/java-invoke-dynamic)
* [Baeldung Java Poet](https://www.baeldung.com/java-poet)
* [Open JDK](https://openjdk.org/)
* [Spring Reactor](https://projectreactor.io/docs/core/release/reference/)
* [Happy Coders](https://www.happycoders.eu/)
