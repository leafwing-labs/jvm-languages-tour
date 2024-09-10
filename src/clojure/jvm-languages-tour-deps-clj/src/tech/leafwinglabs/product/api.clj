(ns tech.leafwinglabs.product.api
  (:require [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [tech.leafwinglabs.product.db :as db]))

;; response util
(defn response [status body]
  {:status  status
   :headers {"Content-Type" "application/json"}
   :body    body})

;; Route handlers
(defn list-products-handler [_]
  (response 200 (db/list-products)))

(defn create-product-handler [request]
  (let [product (-> request :body)]
    (response 201 (db/create-product product))))

(defn get-product-handler [request]
  (let [product-id (-> request :path-params :id)]
    (if-let [product (db/get-product product-id)]
      (response 200 product)
      (response 404 {:error "Product not found"}))))

;; API Routes
(def handler
  (-> (ring/ring-handler
        (ring/router
          [["/products"
            {:get  list-products-handler
             :post create-product-handler}]
           ["/products/:id"
            {:get get-product-handler}]]))
      (wrap-json-body)
      (wrap-json-response)))

;; server lifecycle
(defonce ^:private server (atom nil))

(defn restart []
  (swap! server (fn [x]
                  (when x (.stop x))
                  (jetty/run-jetty
                    #'handler
                    {:port 3000 :join? false})))
  (println "server running in port 3000"))

;; repl
(comment
  (restart)

  (handler {:request-method :get :uri "/products"})
  (handler {:request-method :get :uri "/products/1"})
  (handler {:request-method :post :uri "/products"
            :body           {:name "Product 5"
                             :description "Product 5 description"
                             :price 50.0}})

  :rcf)

