(ns comics-world.core
  (:require [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))



(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World!!!"})

(defn start-server [port]
  (run-jetty handler {:port port}))
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (start-server 3000))
