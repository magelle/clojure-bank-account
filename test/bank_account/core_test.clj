(ns bank-account.core-test
  (:require [clojure.test :refer :all]
            [bank-account.core :refer :all]))

(deftest account-test
  (testing "A new bank account is empty"
    (is (= (newAccount) '())))
  (testing "make a deposit should add an operation in account"
    (is (= (deposit (newAccount) 1000 (fn [] "01-01-2019")) (list (new-operation "01-01-2019" 1000 "CREDIT")))))
  (testing "make a withdrawal should add an operation in account"
    (is (= (withdraw (newAccount) 1000 (fn [] "01-01-2019")) (list (new-operation "01-01-2019" 1000 "DEBIT"))))))

(deftest balance-test
  (testing "an empty account should have a balance of 0"
    (is (= (balance (newAccount)) 0)))
  (testing "balance should be added with deposit"
    (is (= (balance (deposit (newAccount) 300 (fn [] "01-01-2019"))) 300)))
  (testing "balance should be substracted with withdraw"
    (is (= (balance (withdraw (newAccount) 300 (fn [] "01-01-2019"))) -300))))

(deftest print-statement-test
  (testing "an empty account should have only the headers"
    (is (= (print-statement (newAccount)) "Date | Credit | Debit\n")))
  (testing "statement of a deposit"
    (is (= (print-statement (deposit (newAccount) 300 (fn [] "01-01-2019"))) "Date | Credit | Debit\n01-01-2019 | 300 | ")))
  (testing "statement of a withdraw"
    (is (= (print-statement (withdraw (newAccount) 300 (fn [] "01-01-2019"))) "Date | Credit | Debit\n01-01-2019 |  | 300")))
  (testing "statement of several opeartions"
    (is (= (print-statement (-> (newAccount)
                                (deposit 1000 (fn [] "01-01-2019"))
                                (withdraw 500 (fn [] "02-01-2019"))
                                (deposit 300 (fn [] "03-01-2019"))))
           "Date | Credit | Debit\n01-01-2019 | 1000 | \n02-01-2019 |  | 500\n03-01-2019 | 300 | "))))

