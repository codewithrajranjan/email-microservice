(ns email-service.config.config-manager-test
  (:require [clojure.test :refer :all])
  (:require [email-service.config.config-manager :as config]))

(deftest get-config-test
  (testing "should return config"
    (let [email-host (config/get-config :email-server-host)]
      (is (= "smtp.gmail.com" email-host)))))
