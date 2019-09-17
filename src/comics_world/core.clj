(ns comics-world.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [comics-world.albums :refer [albums post-album]]
            [comics-world.middlewares :refer [wrap-json-request wrap-json-response wrap-dummy]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]
            [bidi.ring :refer [make-handler]]
            [mount.core :as mount :refer [defstate]])
  (:gen-class))

(def router
  (make-handler
    ["/albums" {:get  (wrap-stacktrace (wrap-json-response albums))
                :post (wrap-stacktrace (wrap-json-response (wrap-json-request post-album)))}]))

(defn start-server [port]
  (run-jetty router {:port port :join? false}))

(defstate app-server
  :start (start-server 3000)
  :stop (.stop app-server))

(defn -main
  [& args]
  (println "Hello, World!")
  (mount/start))
