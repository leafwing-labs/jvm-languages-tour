# Kotlin

# Kotlin

<!-- npm i --save markdown-toc -->

<!-- toc -->

<!-- tocstop -->

## Kotlin Overview

# Development => 2011

* Release => 2016
* Solutions
    * Web
    * Android
    * General
* Inspirations
    * Java
    * C#
    * Groovy
    * Python
    * Scala

## Kotlin Architecture

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

## Kotlin Features

*

### Kotlin Releases

| Version | Release Date | Features                                                                                             |
|---------|--------------|------------------------------------------------------------------------------------------------------|
| 1.0     | 2016-02-15   | Initial Release on JVM                                                                               |
| 1.1     | 2016-02-15   | Coroutines Experimental, JavaScript, Type Aliases, Bound Callables, std-lib enhancements             |
| 1.2     | 2017-11-28   | Multi-platform Experimental, top-level `lateinit`, Type Inference Improvements, std-lib enhancements |
| 1.3     | 2018-10-29   | Coroutines Release, Experimental Contracts, Inline Classes, JVM companion fields                     |
| 1.4     | 2020-08-17   | Single Abstract Method, Mixed Named/Positional Args, Callable Reference Improvements                 |
| 1.5     | 2021-05-25   | `@JvmRecord`, `@JvmInline`, Sealed Interfaces, Stable JVM IR backend, `invokedynamic` improvements   |
| 1.6     | 2021-11-30   | Exhaustive `when`, Suspend Function Types, Annotated Generic Types                                   |
| 1.7     | 2022-06-09   | Wildcard Types, Inline Delegation K2 Alpha, Gradle & K/Native                                        |
| 1.8     | 2022-12-28   | Java 19 support, Gradle 7.3 support, reflection optimizations                                        |
| 1.9     | 2023-07-06   | Multiplatform Enhancements, Reduce WASM artifact size                                                |
| 2.0     | 2024-05-21   | K2 Compiler, Extended Smart Casts                                                                    |

* K2
    * Concrete Syntax Tree => Intermediate Abstract Syntax Tree
    * Branch Reduction

### Kotlin `invokedynamic`

* JVM Method Invocation Types
    * `invokevirtual` => Normal method
    * `invokestatic` => Static method
    * `invokeinterface` => Interface method
    * `invokespecial` => Constructor / `private` method
    * `invokedynamic` => Dynamic method
        * Avoid reflection inefficiencies
        * Abstract dynamic methods and code generation into `CallSite` / `MethodHandle` objects

## Kotlin Examples

* Generic
    * [MinMax Computation](jvm-languages-tour-gradle-java/app/src/main/java/tech/leafwinglabs/MinMaxComputation.java)
    * [MiniStringFuck](jvm-languages-tour-gradle-java/app/src/main/java/tech/leafwinglabs/MiniStringFuck.java)
* Java Persistence
* Java Web
* Java Functional

## Kotlin Sources

### Kotlin Articles

* [Wikipedia Java (Programming Language)](https://en.wikipedia.org/wiki/Java_%28programming_language%29)
* [Wikipedia Java (Software Platform)](https://en.wikipedia.org/wiki/Java_(software_platform))
* [Baeldung](https://www.baeldung.com/)
* [Happy Coders](https://www.happycoders.eu/)
* [Baeldung invokedynamic](https://www.baeldung.com/java-invoke-dynamic)
* [Baeldung Java Poet](https://www.baeldung.com/java-poet)
