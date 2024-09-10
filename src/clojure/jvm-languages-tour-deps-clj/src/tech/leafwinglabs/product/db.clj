(ns tech.leafwinglabs.product.db
  (:require [next.jdbc :as jdbc]))

(declare db-spec)

;; queries
(defn list-products []
  (with-open [conn (jdbc/get-connection db-spec)]
    (jdbc/execute! conn ["SELECT * FROM product"])))

(defn create-product [product]
  (with-open [conn (jdbc/get-connection db-spec)]
    (jdbc/execute! conn ["INSERT INTO product (name, description, price) VALUES (?, ?, ?)"
                         (:name product)
                         (:description product)
                         (:price product)])))

(defn get-product [product-id]
  (let [product-id (Integer/parseInt product-id)]
    (with-open [conn (jdbc/get-connection db-spec)]
      (jdbc/execute! conn ["SELECT * FROM product WHERE id = ?" product-id]))))

;; db mappings
(def db-spec
  (jdbc/get-datasource
    {:dbtype  "postgresql"
     :dbname   "mydatabase"
     :user     "myuser"
     :password "mypassword"
     :host     "localhost"
     :port     5432}))

;; repl
(comment
  (list-products)
  (create-product {:name "Product 5" :description "Description 5" :price 50})
  (get-product 5)

  :rcf)


