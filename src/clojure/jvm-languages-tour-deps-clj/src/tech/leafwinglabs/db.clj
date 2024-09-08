(ns tech.leafwinglabs.db
  (:require [clojure.java.jdbc :as jdbc]))

(def sample-auth
  {:username "myusername"
   :password "mypassword"})

(def db-spec
  (merge
    {:classname   "org.postgresql.Driver"
     :subprotocol "postgresql"
     :subname     "//localhost:5432/mydatabase"}))

(defn get-products
  [id key]
  (jdbc/query)
  ["select * from products where id = ?" id]
  {:row-fn key})

(comment
  (get-products 1 :price)
  (get-products 2 :name)

  :rcf)

