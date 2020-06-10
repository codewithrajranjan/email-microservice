(ns email-service.handler.send-email-handler-test
  (:require [clojure.test :refer :all]
            [email-service.handler.send-email-handler :as send-email-handler]
            [email-service.client.email-client :as email-client]
            [mockery.core :as mockery]))

(deftest handle-test
  (testing "should send email"
    (mockery/with-mocks [send-email-mock {:target ::email-client/send-email :return true}]
                        (let [receiver "receiver@gmail.com"
                              subject "test subject"
                              message "test message"
                              request {:body {:to      receiver
                                              :subject subject
                                              :message message}}
                              response (send-email-handler/handle request)
                              expected-response {:status 200
                                                 :body {:success true}}]
                          (is (= expected-response response))
                          (is (true? (:called? @send-email-mock)))
                          (is (= `(~receiver ~subject ~message) (:call-args @send-email-mock))))))

  (testing "should not send send email"
    (mockery/with-mocks [send-email-mock {:target ::email-client/send-email :return false}]
                        (let [receiver "receiver@gmail.com"
                              subject "test subject"
                              message "test message"
                              request {:body {:to      receiver
                                              :subject subject
                                              :message message}}
                              response (send-email-handler/handle request)
                              expected-response {:status 500
                                                 :body {:success false}}]
                          (is (= expected-response response))
                          (is (true? (:called? @send-email-mock)))
                          (is (= `(~receiver ~subject ~message) (:call-args @send-email-mock))))))
  )
