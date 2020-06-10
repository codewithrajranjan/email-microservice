(ns email-service.client.email-client
  (:require [postal.core :as postal]
            [email-service.config.config-manager :as config-manager]
            [clojure.tools.logging :as log]))

(defn send-email-from-gmail [receiver subject message]
  (let [server-data {:host (config-manager/get-config :email-server-host)
                     :user (config-manager/get-config :email-server-user)
                     :pass (config-manager/get-config :email-server-password)
                     :port (config-manager/get-config :email-server-port)
                     :tls  (config-manager/get-config  :email-server-tls)}
        email-data {:from    (config-manager/get-config  :email-server-user)
                    :to      receiver
                    :subject subject
                    :body    message}
        result (postal/send-message server-data email-data)]
    (cond
      (= :SUCCESS (:error result)) true
      :else false)))

(defn send-email [receiver subject message]
  (println (str "sending email to : " receiver))
  (send-email-from-gmail receiver subject message))