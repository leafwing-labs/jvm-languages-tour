package tech.leafwinglabs.meta

import groovy.transform.TypeChecked


class TypeCheckers {

    @TypeChecked(extensions = 'groovy.typecheckers.RegexChecker')
    def whenIs2020Over() {
        def newYearsEve = '2020-12-31'
        def matcher = newYearsEve =~ /(\d{4})-(\d{1,2})-(\d{1,2}/
    }

}
