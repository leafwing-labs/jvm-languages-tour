(ns clojure.jvm-languages-tour-deps-clj.src.tech.leafwinglabs.system)

;; datasource
(defn ->datasource
  []
  (hikari/make-datasource
   {:adapter "postgresql"
    :username "user"
    :password "password"
    :subname "//localhost:5432/mydb"}))

;; component mixin
(defrecord Database [datasource]
  component/Lifecycle
  (start [this]
    (println "Starting the database connection pool")
    (assoc this :datasource datasource))
  (stop [this]
    (println "Stopping the database connection pool")
    (hikari/close-datasource (:datasource this))
    (dissoc this :datasource)))

(def database (->Database (->datasource)))
(def comp-system (component/system-map :database database))

;; start
(component/start comp-system)

;; stop
(component/stop comp-system)



;; Integrant mixin
(def config {:datasoure (->datasource)})

;; Start the component
(def int-system (ig/init config))

;; Stop the component
(ig/halt int-system)