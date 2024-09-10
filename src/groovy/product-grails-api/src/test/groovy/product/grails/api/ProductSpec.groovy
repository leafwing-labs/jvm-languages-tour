package product.grails.api

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProductSpec extends Specification implements DomainUnitTest<Product> {

    def "product should have id, name, description, and price"() {
        given:
        def productId = 1
        def productName = "Sample Product"
        def productDescription = "Sample description"
        def productPrice = 100.00

        when:
        def product = new Product(
            id: productId,
            name: productName,
            description:
            productDescription,
            price: productPrice
            )

        then:
        product.id == productId
        product.name == productName
        product.description == productDescription
        product.price == productPrice
    }

}



