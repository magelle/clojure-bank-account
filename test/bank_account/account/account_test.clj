(ns bank-account.account.account-test
  (:require [clojure.test :refer :all])
  (:require [bank-account.account.account :refer :all]
            [bank-account.account.balance :refer :all]
            [bank-account.account.statement :refer :all]
            [bank-account.account.operation :refer :all]))

(deftest account-test
  (testing "A new bank account is empty"
    (is (= (newAccount) '())))
  (testing "make a deposit should add an operation in account"
    (is (= (deposit (newAccount) 1000 (fn [] "01-01-2019")) (list (new-operation "01-01-2019" 1000 "CREDIT")))))
  (testing "make a withdrawal should add an operation in account"
    (is (= (withdraw (newAccount) 1000 (fn [] "01-01-2019")) (list (new-operation "01-01-2019" 1000 "DEBIT"))))))


