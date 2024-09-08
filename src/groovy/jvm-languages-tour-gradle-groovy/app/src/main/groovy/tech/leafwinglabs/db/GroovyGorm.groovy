package tech.leafwinglabs.db

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
        sort name: "asc"
    }
}

// create
def p = new Product(name: "Book", description: "A good book", price: 19.99)
p.save(flush: true)

// retrieve
def clone = Product.get(1)
def products = [products: p.list()]

// update
p.price = 29.99
p.save(flush: true)

// delete
p.delete(flush: true)
