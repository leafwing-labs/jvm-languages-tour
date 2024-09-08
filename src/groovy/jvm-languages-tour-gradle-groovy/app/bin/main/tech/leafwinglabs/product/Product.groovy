package tech.leafwinglabs.product

import grails.persistence.Entity

@Entity
class Product {
    @Identity
    Long id
    String name
    String description
    double price

    Product() {
        // default constructor
    }
}