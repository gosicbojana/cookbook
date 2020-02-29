(defproject cookbook "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                [clj-http "2.0.0"]
                [ring/ring-core "1.6.3"]
                [ring/ring-jetty-adapter "1.6.3"],
                [ring/ring-json "0.4.0"]
                [ring/ring-defaults "0.3.1"]
                [korma "0.4.3"]
                [mysql/mysql-connector-java "5.1.6"]
                [metosin/compojure-api "2.0.0-alpha19"]
                [org.clojure/core.unify "0.5.7"]]
  :ring {:handler swagger.api/app}
  :plugins [[lein-ring "0.12.5"]
            [compojure "1.6.1"]]
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                :plugins [[lein-ring "0.12.5"]]}}
)


           
