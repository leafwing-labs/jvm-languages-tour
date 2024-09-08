package tech.leafwinglabs.product

import grails.persistence.Entity

@Entity
class Product {
    String name
    String description
    BigDecimal price

    static constraints = {
        name nullable: false, blank: false
        description nullable: false, blank: false
        price nullable: false, min: 0.0
    }

    static mapping = {
        price scale: 2
    }
}

