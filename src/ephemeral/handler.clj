(ns ephemeral.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def store (atom {}))

(defroutes app-routes
  (POST "/:key" {:keys [body params]}
    (swap! store assoc (-> params :key keyword) body)
    (response @store))
  (GET "/:key" [key]
    (->> key keyword (get @store) response))
  (GET "/" []
    (response @store))
  (DELETE "/" []
    (reset! store {})
    (response @store)))

(def app
  (routes 
    (-> app-routes
        (wrap-routes wrap-json-response {:keywords? true :bigdecimals? true})
        (wrap-routes wrap-json-body))
    (route/not-found "nil")))
