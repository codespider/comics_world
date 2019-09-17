(ns comics-world.middlewares
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [clojure.data.json :as json]
            [cheshire.core :as c]))

(defn- keywordize [m]
  (cske/transform-keys csk/->kebab-case-keyword m))

(defn- camelise [m]
  (cske/transform-keys csk/->camelCase m))


(defn wrap-dummy [handler]
  (fn [req]
    (println "called first")
    (let [response (handler req)]
      (println "called next")
      response)))

(defn wrap-json-request [handler]
  (fn [req]
    (-> req
        (update :body slurp)
        (update :body json/read-str)
        (update :body keywordize)
        (update :headers keywordize)
        handler)))

(defn wrap-json-response [handler]
  (fn [req]
    (-> req
        handler
        (update :body camelise)
        (update :body c/generate-string))))
