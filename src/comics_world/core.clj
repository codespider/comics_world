(ns comics-world.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response header]]
            [bidi.ring :refer [make-handler]]
            [mount.core :as mount :refer [defstate]]
            [clojure.java.jdbc :as jdbc])
  (:gen-class))

(def db-config {:dbtype "postgresql"
            :dbname     "comics_world_dev"
            :host       "localhost"
            :user       "postgres"
            :password   ""})

(defn get-comic-books []
  (jdbc/query db-config ["select id,title,lead_character,lang,publisher,pages,published_on,country from album"]))

(defn handler-1 [request]
  (-> (response (get-comic-books))
      (header "Content-Type" "text/json")))

(def router
  (make-handler ["/albums" handler-1]))

(defn start-server [port]
  (run-jetty router {:port port :join? false}))

(defstate app-server
  :start (start-server 3000)
  :stop (.stop app-server))

(defn -main
  [& args]
  (println "Hello, World!")
  (mount/start))
