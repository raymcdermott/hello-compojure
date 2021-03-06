(ns hello-compojure.registry
  (:require [taoensso.carmine :as redis :refer (wcar)]
            [datomic.api :as d]
            [cheshire.core :refer :all]
            [environ.core :refer [env]]))

(defn- get-redis-connection-pool []
  (let [spec {:pool {} :spec (if-let [uri (env :redis-url)]
                               {:uri uri}
                               {:host "127.0.0.1" :port 6379})}]
    spec))

(defn- look-up-datomic [list-name]
  (if-let [value (redis/wcar (get-redis-connection-pool) (redis/lindex list-name 0))]
    (parse-string value true)))

(defn db-connect []
  (let [datomic (look-up-datomic "datomic")
        uri (str "datomic:sql://" (:host datomic) ":" (:port datomic) "/customer")
        conn (d/connect uri)
        db (d/db conn)]
    db))

; TODO: remove :sql: from here and put the storage into REDIS
