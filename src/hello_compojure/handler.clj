(ns hello-compojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hello-compojure.registry :as registry]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

; in here we are going to connect to the back end Datomic DB and say hello to the only user in the db

(def datomic (registry/db-connect))

(defroutes app-routes
  (GET "/" [] (str "Hello World"))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
