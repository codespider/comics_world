(ns comics-world.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [mount.core :refer [defstate]])
  (:gen-class))




(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World!!! 1234567"})

(defn start-server [port]
  (run-jetty handler {:port port :join? false}))

(defstate app-server
  :start (start-server 3000)
  :stop (.stop app-server))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (start-server 3000))
