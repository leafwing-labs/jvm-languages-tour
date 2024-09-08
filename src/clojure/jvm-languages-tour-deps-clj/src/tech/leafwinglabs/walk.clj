(ns tech.leafwinglabs.walk
  (:require [clojure.walk :as walk]))

(comment

  (require '[clojure.walk :as walk])

  (defn postwalk
    [f form]
    (walk (partial postwalk f) f form))

  (walk/postwalk-demo {:a 1 :b {:c 2 :d 3} :e [4 5]})
  ;Walked: :a
  ;Walked: 1
  ;Walked: [:a 1]
  ;Walked: :b
  ;Walked: :c
  ;Walked: 2
  ;Walked: [:c 2]
  ;Walked: :d
  ;Walked: 3
  ;Walked: [:d 3]
  ;Walked: {:c 2, :d 3}
  ;Walked: [:b {:c 2, :d 3}]
  ;Walked: :e
  ;Walked: 4
  ;Walked: 5
  ;Walked: [4 5]
  ;Walked: [:e [4 5]]
  ;Walked: {:a 1, :b {:c 2, :d 3}, :e [4 5]}


  (require '[clojure.walk :as walk])

  (defn prewalk
    [f form]
    (walk (partial prewalk f) identity (f form)))

  (walk/prewalk-demo {:a 1 :b {:c 2 :d 3} :e [4 5]})
  ;Walked: {:a 1, :b {:c 2, :d 3}, :e [4 5]}
  ;Walked: [:a 1]
  ;Walked: :a
  ;Walked: 1
  ;Walked: [:b {:c 2, :d 3}]
  ;Walked: :b
  ;Walked: {:c 2, :d 3}
  ;Walked: [:c 2]
  ;Walked: :c
  ;Walked: 2
  ;Walked: [:d 3]
  ;Walked: :d
  ;Walked: 3
  ;Walked: [:e [4 5]]
  ;Walked: :e
  ;Walked: [4 5]
  ;Walked: 4
  ;Walked: 5

  :rcf)



