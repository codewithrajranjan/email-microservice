(ns email-service.integration.send-email-integration
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [email-service.client.email-client :as email-client]
            [email-service.core :as core]
            [cheshire.core :as cheshire]
            [mockery.core :as mockery]))



(deftest send-email-test
  (testing "send email success"
    (mockery/with-mocks [send-email-mock {:target ::email-client/send-email-from-gmail :return true}]
                        (let [receiver "receiver@gmail.com"
                              subject "test subject"
                              message "message"
                              request (-> (mock/request :post "/email")
                                          (mock/json-body {:to  receiver
                                                           :subject subject
                                                           :message message}))
                              actual-response ((core/create-handler) request)
                              expected-response {:status 200 :body {"success" true}}]
                          (is (= 200 (:status actual-response)))
                          (is (true? (:called? @send-email-mock)))
                          (is (= `(~receiver ~subject ~message) (:call-args @send-email-mock))))))

  (testing "send email fail because of incorrect data"
    (let [request (-> (mock/request :post "/email")
                      (mock/json-body {:to      "receiver@gmail.com"
                                       :subject "test subject"}))
          actual-response ((core/create-handler) request)
          expected-response {:status 400 :body {:success false :message "message is mandatory"}}]
      (is (= 400 (:status actual-response))))))