package product.grails.api

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProductSpec extends Specification implements DomainUnitTest<Product> {

     void "test domain constraints"() {
        when:
        Product domain = new Product()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
