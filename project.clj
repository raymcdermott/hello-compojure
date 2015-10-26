(defproject hello-compojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :username [:env/my-datomic-username]
                                   :password [:env/my-datomic-password]}}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [cheshire "5.5.0"]
                 [environ "1.0.1"]
                 [com.taoensso/carmine "2.12.0"]
                 [com.datomic/datomic-pro "0.9.5302" :exclusions [joda-time]]
                 [ring/ring-defaults "0.1.2"]]
  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.1"]]
  :ring {:handler hello-compojure.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
