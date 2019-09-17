(ns comics-world.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response header]]
            [comics-world.middlewares :refer [wrap-json]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]
            [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [bidi.ring :refer [make-handler]]
            [mount.core :as mount :refer [defstate]]
            [clojure.java.jdbc :as jdbc]
            [cheshire.core :as c]
            [clojure.data.json :as json])
  (:gen-class))

(def db-config {:dbtype   "postgresql"
                :dbname   "comics_world_dev"
                :host     "localhost"
                :user     "postgres"
                :password ""})

(defn get-comic-books []
  (jdbc/query db-config ["select id,title,lead_character,lang,publisher,pages,published_on,country from album"]))

(defn handler-1 [request]
  (-> (get-comic-books)
      (c/generate-string)
      (response)
      (header "Content-Type" "text/json")))

(defn handler-2 [request]
  (def req* request)
  (response "blah"))

(def router
  (make-handler ["/" {"albums"        handler-1
                      "albums_upload" (wrap-stacktrace (wrap-json handler-2))}]))

(defn start-server [port]
  (run-jetty router {:port port :join? false}))

(defstate app-server
  :start (start-server 3000)
  :stop (.stop app-server))

(defn -main
  [& args]
  (println "Hello, World!")
  (mount/start))
