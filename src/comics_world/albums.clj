(ns comics-world.albums
  (:require [clojure.java.jdbc :as jdbc]
            [ring.util.response :refer [response header]]))


(def db-config {:dbtype   "postgresql"
                :dbname   "comics_world_dev"
                :host     "localhost"
                :user     "postgres"
                :password ""})

(defn get-comic-books []
  (jdbc/query db-config ["select id,title,lead_character,lang,publisher,pages,published_on,country from album"]))


(defn albums [request]
  (println "get called")
  (-> (get-comic-books)
      (response)
      (header "Content-Type" "text/json")))

(defn post-album [request]
  (println "post called")
  (response {:blah-hello       "blah"
             "hello-World-oNe" "world"}))
