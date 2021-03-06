(defproject ephemeral "0.2.0-SNAPSHOT"
  :description "Ephemeral, a json in-memory store."
  :url "http://localhost:3000"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/data.json "0.2.6"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler ephemeral.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
