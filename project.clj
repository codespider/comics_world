(defproject comics_world "0.1.0-SNAPSHOT"
  :description "Comics World"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-core "1.7.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [mount "0.1.16"]
                 [com.taoensso/timbre "4.10.0"]
                 [org.apache.logging.log4j/log4j-core "2.11.2"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.11.2"]
                 [org.clojure/java.jdbc "0.7.10"]
                 [org.postgresql/postgresql "42.2.5"]]
  :main ^:skip-aot comics-world.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
