(ns comics-world.middlewares
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [clojure.data.json :as json]))



(defn wrap-json [handler]
  (fn [req]
    (def middle* req)
    ;(def body* (-> req :body slurp))
    (-> req
        (update :body slurp)
        (update :body json/read-str)
        (update :body #(cske/transform-keys csk/->kebab-case-keyword %))
        (update :headers #(cske/transform-keys csk/->kebab-case-keyword %))
        handler)))
