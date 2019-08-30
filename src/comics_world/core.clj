(ns comics-world.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [mount.core :as mount :refer [defstate]]
            [clojure.java.jdbc :as jdbc])
  (:gen-class))

(def db-config {:dbtype "postgresql"
            :dbname     "postgres"
            :host       "localhost"
            :user       "postgres"
            :password   ""})

(defn get-time-from-db []
  (first (jdbc/query db-config
                     ["select now()"]
                     {:row-fn :now})))

(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "Hello World!!! time now is " (get-time-from-db))})

(defn start-server [port]
  (run-jetty handler {:port port :join? false}))

(defstate app-server
  :start (start-server 3000)
  :stop (.stop app-server))

(defn -main
  [& args]
  (println "Hello, World!")
  (mount/start))
