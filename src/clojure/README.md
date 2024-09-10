# Clojure

- [Clojure Overview](#Clojure-overview)
- [Clojure Architecture](#Clojure-architecture)
- [Clojure Features](#Clojure-features)
    * [Clojure Releases](#Clojure-releases)
    * [Clojure `invokedynamic`](#Clojure-invokedynamic)
- [Clojure Examples](#Clojure-examples)
- [Clojure Sources](#Clojure-sources)
    * [Clojure Articles](#Clojure-articles)

## Clojure Overview

* Release => 2007
* Solutions
    * Web
    * Functional
    * Data Science
    * Machine Learning
* Inspirations
    * Lisp
    * Scheme
    * Haskell

## Clojure Architecture

## Clojure Features

### Clojure Releases

| Version            | Release Date | Features                                                                           |
|--------------------|--------------|------------------------------------------------------------------------------------|
| 0.x                | 2007-10-17   | Initial Release                                                                    |
| 1.0                | 2009-05-04   | Persistent Data Structures, Protocols, Multimethods, Agents, Atoms                 |
| 1.1                | 2009-12-31   | Futures/Promises, `clojure.test`, `clojure.walk`                                   |
| 1.2                | 2010-08-19   | `defprotocol`, `defrecord`, `case`, Destructuring, Enhancements                    |
| 1.3                | 2011-09-23   | Enhanced Primitive Support, Error Reporting, Performance Improvements              |
| 1.4                | 2012-04-15   | `edn`, Reader Literals, Java Interop and Collections enhancements                  |
| 1.5                | 2013-06-07   | `core.async`, Reducers, Agent / Threading Improvements , Performance Enhancements  |
| 1.6                | 2014-03-25   | Java API Enhancements, Namespaced Key Destructuring, Performance Enhancements      |
| 1.7                | 2015-06-30   | Transducers, Reader Conditionals, Performance Enhancements                         |
| 1.8                | 2016- 01-19  | Direct Linking, Core String Functions, Socket Server REPL                          |
| 1.9                | 2017-12-08   | `clojure.spec`, Qualified Maps, New Predicates, Instant Support                    |
| 1.10               | 2018-12-17   | JDK 1.8+, Error Introspection, Graph Navigation, `tap`                             |
| 1.11               | 2022-03-22   | `clojure.math`, `update-keys`, `update-vals`                                       |
| Clojure 1.12.0-rc2 | 2024-08-28   | Hot Load Libs, `clojure.java.process`, Qualified Methods, Performance Enhancements |

## Clojure Domains

| Domain       | Sample Libraries                             | 
|--------------|----------------------------------------------|
| System       | Component, Integrant, Mount, Polylith        |
| Config       | edn, aero                                    |
| Web Server   | Ring, Reitit, Compojure                      |
| Web Client   | Clojurescript, Pedestal, Om, Duct            |
| Async        | core.async, aleph                            |
| Data         | next.jdbc, honeysql, korma                   |
| Data Science | Incanter, Neanderthal, Scicloj               |
| Graphs       | Pathom, Specter,  Datomic                    |
| Testing      | clojure.test, kaocha                         |
| Spec         | clojure.spec, metosin/malli, plumatic/schema |
| Matching     | core.match, Meander                          |
| Tools        | deps, clj-kondo, Flow Storm                  |

### Clojure DSLs

* [Glisp](https://glisp.app/commit:e7fbaae/)
* [Hiccup](https://github.com/weavejester/hiccup)
* [Dali](https://glisp.app/commit:e7fbaae/)

## Clojure Sources

* [Clojure Wikipedia](https://en.wikipedia.org/wiki/Clojure)
* [Dev Changelog](https://clojure.org/releases/devchangelog)