(defproject email-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.logging "1.1.0"]
                 [ring "1.8.1"]
                 [bidi "2.1.6"]
                 [ring/ring-json "0.5.0"]
                 [cheshire "5.10.0"]
                 [camel-snake-kebab "0.4.1"]
                 [com.draines/postal "2.0.3"]
                 [ring/ring-mock "0.4.0"]
                 [mockery "0.1.4"]]
  :main ^:skip-aot email-service.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
