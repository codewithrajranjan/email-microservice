(ns email-service.config.config-manager
  (:gen-class))

(def config
  {:email-server-host     "smtp.gmail.com"
   :email-server-port     587
   :email-server-tls      true
   :email-server-user     "<email_address>"
   :email-server-password "<password>"})

(defn get-config [key]
  (key config))