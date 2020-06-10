(ns email-service.core
  (:require [bidi.ring :as bidi]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.adapter.jetty :as jetty]
            [camel-snake-kebab.extras :as cske]
            [camel-snake-kebab.core :as csk]
            [email-service.handler.send-email-handler :as send-email-handler])
  (:gen-class))



(def routes ["/" {"email" {:post send-email-handler/handle}}])


(defn convert-to-keys [handler]
  (fn [request]
    (let [body (:body request)
          request (assoc request :body (cske/transform-keys csk/->kebab-case-keyword body))]
      (handler request))))

(defn create-handler []
   (-> (bidi/make-handler routes)
                        (convert-to-keys)
                        (wrap-json-body)
                        (wrap-json-response)))

(defn -main
  [& args]
  (println "Staring server")
  (jetty/run-jetty (create-handler) {:port 3000}))
