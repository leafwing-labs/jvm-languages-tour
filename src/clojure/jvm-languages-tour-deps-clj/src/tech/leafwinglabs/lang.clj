(ns tech.leafwinglabs.lang                                  ;; namespaces encapsulate data
  (:require [clojure.string :as str])                       ;; import Clojure namespaces
  (:import [java.util Date]))                               ;; import Java classes

;; Everything is a s-expr
(print                                                      ;; call fn form => *out*
  "a"                                                       ;; data
  :b                                                        ;; keyword => identity [function]
  (fn [x y] (+ x y))                                        ;; build other fns
  (str 1 " and " 2)                                         ;; call site
  [1 2 3])                                                  ;; vector

; Data
(def m {:a "foo" :b 1 :c "bar"})                            ;; keywords mark data
(:a m)                                                      ;; keywords are functions
(m :a)                                                      ;; maps are functions too

;; Functions
(str/split "a,b,c" #",")                                    ;; call a function in other ns
(defn inc' [x] (+ x 1))                                     ;; intern a function
(println (inc' 1))                                          ;; 2
(def inc'' (partial + 1))                                   ;; partial application
(println (inc'' 11))                                        ;; 12
((comp not str/blank? :name) {:name "eric"})                ;; composition

;; special forms
(if '(:pred) true false)                                    ;; branch on predicate
(do '(:body))                                               ;; evaluate forms and return last
(let [x 1] '(:body))                                        ;; local bindings
(fn f [x] '(:do))                                           ;; higher order function
#(':do)                                                     ;; anonymous functions
(loop [acc '()] (do '(:body) '(recur)))                       ;; tail recursion
(try '(:body)                                               ;; exception handling
     (catch Exception e (throw e))
     (finally '(:body)))

;; collection data structures
(seq '(:a))                                                 ;; base => lazy lists
'(:a :b :c)                                                 ;; lists => linked
[1 2 3]                                                     ;; vectors => eager arrays
{:a 1 :b 2 :c 3}                                            ;; maps => seq of pairs
#{:a :b :c}                                                 ;; sets => domains
(defn fib [a b] (lazy-seq (cons a (fib b (+ a b)))))        ;; infinite lists => streams

;; collection functions
(map inc [1 2 3])                                           ;; map => transform
(filter odd? [1 2 3])                                       ;; filter => select
(reduce + [1 2 3])                                          ;; reduce => fold
(apply min [1 3 0 2])                                       ;; apply => variadic

;; streams
(def seq
  (->> '("1" "2" "3" "4" "5")                               ;; chain of responsibilities
       (map #(Integer/parseInt %))                          ;;  |
       (filter even?)                                       ;;  |....
       (map #( * % %))
       (sort-by #(apply str (reverse (str %))))
       (map #(str (char %)))
       (clojure.string/join "|")));; => 1 2 3

;; metaprogramming
(defmacro when' [t b] (list 'if t (cons 'do b)))            ;; macros transform symbols
(macroexpand-1 '(when' true (println "hi")))                ;; (if true (do println "hi"))

(defmacro unless [pred & body]
  `(if (not ~pred)
     (do ~@body)))                                          ;; syntax quotes stop evaluation

;; threading / do => stream processing
(->> [1 2 3]                                                ;; thread last => collections
     (map inc)
     (filter odd?))                                         ;; => (3)
(-> " one , two, three "                                    ;; thread first => objects
    str/trim
    (str/replace #"\s?,\s?" "|")
    #_(str/split #"|"))
;; => "one|two|three"

;; object side effects
(doto (StringBuilder.)
  (.append "a")
  (.append "b"))
;; => #object[java.lang.StringBuilder 0x2c708440 "ab"]

;; consumer
(doseq [x [1 2 3]] (println x))
