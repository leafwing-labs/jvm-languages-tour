package tech.leafwinglabs.db

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@ToString
@TupleConstructor
@EqualsAndHashCode
class TestMeta {
    String name
    String description
    BigDecimal price
}
