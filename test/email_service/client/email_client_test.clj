(ns email-service.client.email-client-test
  (:require [clojure.test :refer :all]
            [email-service.client.email-client :as email-client]
            [mockery.core :as mockery]))

(deftest send-email-test
  (testing "should send email successfully"
    (mockery/with-mocks [send-gmail-mock {:target ::email-client/send-email-from-gmail :return true}]
                        (let [receiver "receiver@gmail.com"
                              subject "test-subject"
                              message "test-message"
                              response (email-client/send-email receiver subject message)]
                          (is (true? response))
                          (is (true? (:called? @send-gmail-mock)))
                          (is (= `(~receiver ~subject ~message) (:call-args @send-gmail-mock))))))

  (testing "should cause error when sending email "
    (mockery/with-mocks [send-gmail-mock {:target ::email-client/send-email-from-gmail :return false}]
                        (let [receiver "receiver@gmail.com"
                              subject "test-subject"
                              message "test-message"
                              response (email-client/send-email receiver subject message)]
                          (is (false? response))
                          (is (true? (:called? @send-gmail-mock)))
                          (is (= `(~receiver ~subject ~message) (:call-args @send-gmail-mock)))))))
