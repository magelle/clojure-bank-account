(ns bank-account.account.balance-test
  (:require [clojure.test :refer :all])
  (:require [bank-account.account.balance :refer :all]
            [bank-account.account.account :refer :all]))

(deftest balance-test
  (testing "an empty account should have a balance of 0"
    (is (= (balance (newAccount)) 0)))
  (testing "balance should be added with deposit"
    (is (= (balance (deposit (newAccount) 300 (fn [] "01-01-2019"))) 300)))
  (testing "balance should be substracted with withdraw"
    (is (= (balance (withdraw (newAccount) 300 (fn [] "01-01-2019"))) -300))))