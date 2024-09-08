package tech.leafwinglabs

import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.macro.runtime.Macro
import org.codehaus.groovy.macro.runtime.MacroContext
import org.codehaus.groovy.syntax.SyntaxException

class GroovyLang {

    // methods
    def main() {
        def name = "Groovy"                             // inference
        def answer = 42
        println "$name, ${new Random().nextInt(100)}"   // interpolation

        def lst = [1, 2, 3, 4, 5]                              // collection literals
        def map = [name: "Groovy", answer: 42]
        def dbl = lst*.toDouble()            // spread operator
        def even = lst.findAll { it % 2 == 0 }   // streams

        println greet()                                    // error
        println greet("Eric")                            // Eric: 0
        println greet(answer = answer, name = name)            // Groovy: 42

        // closures
        def isLowerCase = {
            c ->
                try {
                    Character.isLowerCase(c as char)
                } catch (ignored) {
                    false
                }
        }
    }

    def greet(name, answer = 0) {
        "$name: $answer."                            // default & named arguments
    }

    def meta() {
        // metaprogramming
        String.metaClass.repeatString = { n ->
            def result = ''
            n.times { result += delegate }
            result
        }
        println "abc".repeatString(3)                       // abcabcabc

        // DSLS
        please show the square_root of 100                     // 10.0
    }

    // DSLs
    def please(action) {
        [the: { what ->
            [of: { n -> action(what(n)) }]
        }]
    }
    def show = { it -> println it }
    def square_root = { Math.sqrt(it) }

    @Macro
    static Expression upper(MacroContext macroContext, ConstantExpression constX) {
        if (constX.value instanceof String) {
            return new ConstantExpression(constX.value.toUpperCase())
        }
        macroContext.sourceUnit.addError(new SyntaxException("Can't use upper with non-String", constX))
    }

}

class String {

}