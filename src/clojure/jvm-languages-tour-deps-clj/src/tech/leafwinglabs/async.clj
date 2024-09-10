(ns tech.leafwinglabs.async
  (:require [clojure.core.async :as async :refer [<!! >!]]))

;; product => could be clojure.spec
(def product {:id          1
              :name        "Product 1"
              :description "Product 1 description"
              :price       100})

;; all products
(def product-stream (async/chan))
;; high price products
(def high-price-products (async/chan))

;; domain logic
(defn high-price? [product]
  (> (:price product) 50))

(comment

  (def product-stream (async/chan))
  (def high-price-stream (async/chan))
  (def high-price-pipe (async/pipeline 4
                                       high-price-products
                                       (filter high-price?) ; transducer
                                       product-stream))

  (async/go
    (let []
      (doseq [id (range 100)]
        (let [product {:id          id
                       :name        (str "Product")
                       :description (str "Product " id " description")
                       :price       (rand-int 100)}]
          (>! product-stream product)))))

  (println (str "Product : " (<!! product-stream)))
  (println (str "High price product: " (<!! high-price-products)))

  (async/close! product-stream)
  (async/close! high-price-products)

  :rcf)


(defn product-stream []
  (let [prod-chan       (async/chan)                        ; Channel for product stream
        price-chan      (async/chan)                        ; Channel for prices
        high-price-chan (async/chan)                        ; Channel for high prices
        low-price-chan  (async/chan)                        ; Channel for low prices
        result-chan     (async/chan)]                       ; Channel for final results

    (async/thread
      (loop []
        (let [product {:name "Product" :price (rand-int 100)}
              high?   (> (:price product) 50)]

          (async/>!! prod-chan product)                     ; Put product into the stream

          (if high?
            (async/>!! high-price-chan product)             ; Put high price products into high-price channel
            (async/>!! low-price-chan product))             ; Put low price products into low-price channel

          (recur)))

      (async/>!! prod-chan :end)                            ; Signal the end of the product stream
      (async/close! prod-chan)
      (async/close! high-price-chan)
      (async/close! low-price-chan))

    (async/thread
      (async/pipeline 1
                      result-chan
                      (async/merge high-price-chan low-price-chan) ; Merge high and low-price channels

                      ;; Process the merged channel
                      (fn [product]
                        (if (nil? product)
                          (async/<!! (async/timeout 1000))  ; Timeout in case of nil product
                          (async/>!! result-chan product)))))

    result-chan))

(comment


  :rcf)

(defn -main []
  (let [result (product-stream)]
    (loop []
      (if-let [product (async/<!! result)]
        (do
          (println "Product received: " product)
          (recur))
        (do
          (println "Stream completed.")
          (async/close! result))))))

