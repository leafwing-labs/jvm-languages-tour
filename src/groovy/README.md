# Groovy

<!-- npm i --save markdown-toc -->
<!-- toc -->

<!-- tocstop -->

## Groovy Overview

* Incubation => 2003
* Release => 2007
* Solutions
    * Dynamic Typing
    * Scripting
    * Testing
    * Build
    * Web
* Inspirations
    * Java
    * Python
    * Ruby

## Groovy Architecture

## Groovy Features

* Dynamic Typing
* Closures
* Multiline strings
* String Interpolation => `"$name"`
* Null Safety => `?.`
* AST Transformations => Lombok
* Groovy Shell

```groovy
// closures
isLowerCase = { c -> try { Character.isLowerCase(c as char) } catch (ignored) { false } }
isUpperCase = { c -> try { Character.isUpperCase(c as char) } catch (ignored) { false } }

// split string on character types
splitString = { s -> s.toList().groupBy { isLowerCase(it) ? 'lower' : isUpperCase(it) ? 'upper' : 'other' } }

// snake case
toSnakeCase = { s -> s.replaceAll(/([a-z])([A-Z])/, '$1_$2').toLowerCase() }
```

## Groovy Releases

| Version     | Release Date | Features                                                                                     |
|-------------|--------------|----------------------------------------------------------------------------------------------|
| 1.0         | 2007-01-03   | Initial Release                                                                              |
| 1.5         | 2007-12-07   | Interactive shell, Java interop enhancements, Language enhancements                          |
| 1.6         | 2009-02-18   | Grape, AST Transformations, Multi-assignment, Mixins, `@Immutable`, `@Lazy`, `@Delegate`     |
| 1.7         | 2009-12-22   | Annotations Everywhere, Class/Closure interop, `ASTBuilder`                                  |
| 1.8         | 2011-04-27   | DSL Command Chains, AST Transformation Enhancements, JDK 7 Alignment                         |
| 2.0         | 2012-06-28   | `@TypeChecked`, `@CompileStatic`, Java 7 interop, Modularization                             |
| 2.1         | 2013-01-24   | Annotation Aliasing, Full `invokedynamic` support, `@DelegatesTo`, `@TypeChecked` extensions |
| 2.2         | 2013-11-06   | Closure coercion, `@Memoized`, Type-Checking pre-compilation, Script Delegates               |
| 2.3         | 2014-05-05   | JDK 8 Support, Traits, `@TailRecursive`, `@Builder`, Tool ehancements                        |
| 2.4         | 2015-01-21   | `@SelfType`, Performance Improvements                                                        |
| 2.5         | 2018-05-27   | Macro Support, `ASTMatcher`, AST improvements                                                |
| 3.0         | 2020-02-07   | Parrot Parser, GDK Improvements                                                              |
| 4.0         | 2022-01-25   | Switch Expressions, Sealed Types, Records                                                    |
| 5.0-alpha-9 | 2024-06-27   | Wildcard Type, String format type-checker, Extension Method improvements                     |

## Groovy Domains

| Domain    | Sample Libraries                             | 
|-----------|----------------------------------------------|
| Web       | Grails, Ratpack                              |
| Data      | GORM, JDBI, Groovy SQL, Groovy Couchbase     |
| Testing   | Spock, Geb, JUnit, TestNG                    |
| Build     | Gradle, Gant, Griffon                        |
| Scripting | Groovy Shell, Groovy Console, Groovy Scripts |

### Articles

* [Wikipedia Apache Groovy](https://en.wikipedia.org/wiki/Apache_Groovy)

### Libraries

* [Groovy Shell](https://docs.groovy-lang.org/latest/html/documentation/groovysh.html)
* [Groovy Changelogs](https://groovy-lang.org/changelogs.html)
* [Rundeck](https://github.com/rundeck/rundeck/wiki/FAQ)
* [Nextflow](https://github.com/nextflow-io/nextflow?tab=readme-ov-files)
* [Groovy Transform](https://www.baeldung.com/groovy-metaprogramming)
