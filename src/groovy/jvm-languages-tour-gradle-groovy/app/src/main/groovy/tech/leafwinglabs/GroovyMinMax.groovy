package tech.leafwinglabs

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam
import org.codehaus.groovy.runtime.NullObject

import java.lang.annotation.Target


class GroovyMinMax {

    // classic
    def minMax1(arr) {
        def min = arr[0]
        def max = arr[0]
        for (int i = 1; i < arr.size(); i++) {
            if (arr[i] < min) {
                min = arr[i]
            }
            if (arr[i] > max) {
                max = arr[i]
            }
        }
        return [min, max]
    }

    // closure
    def minMax2(arr) {
        def min = arr[0]
        def max = arr[0]
        arr.each { element ->
            min = Math.min(min, element)
            max = Math.max(max, element)
        }
        return [min, max]
    }

    // reduce
    def minMax3(arr) {
        return arr.inject([arr[0], arr[0]]) { result, element ->
            [Math.min(result[0], element),
             Math.max(result[1], element)]
        }
    }

    // functions
    def minMax4(arr) {
        return arr.collect { it }.sort().with { [first(), last()] }
    }

    // API
    def minMax5(arr) {
        return [arr.min(), arr.max { it }]
    }

//    public static <T, U> T with(
//            @Target("self") U self,
//            @DelegatesTo(value = DelegatesTo.Target.class, target = "self", strategy = 1)
//            @ClosureParams(FirstParam.class)
//                    Closure<T> closure) {
//        if (self == NullObject.getNullObject()) {
//            self = null;
//        }
//        Closure<V> mutableClosure = (Closure) closure.clone();
//        mutableClosure.setResolveStrategy(1);
//        mutableClosure.setDelegate(self);
//        V rv = mutableClosure.call(self);
//        return rself : rv;
//    }

}
