(ns email-service.handler.send-email-handler
  (:require [email-service.client.email-client :as email-client]
            [clojure.string :as string]))


(defn handle [request]
  (let [body (:body request)
        receiver (:to body)
        subject (:subject body)
        message (:message body)]
    (cond
      (string/blank? receiver) {:status 400 :body {:success false :message "to is mandatory"}}
      (string/blank? message) {:status 400 :body {:success false :message "message is mandatory"}}
      (string/blank? subject) {:status 400 :body {:success false :message "subject is mandatory"}}
      :else
      (let [response (email-client/send-email receiver subject message)]
        (cond
          (true? response) {:status 200 :body {:success true}}
          :else {:status 500 :body {:success false}})))))