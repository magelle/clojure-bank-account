(ns bank-account.account.statement_test
  (:require [clojure.test :refer :all]
            [bank-account.account.account :refer :all]
            [bank-account.account.statement :refer :all]))

(deftest print-statement-test
  (testing "an empty account should have only the headers"
    (is (= (print-statement (newAccount)) "Date | Credit | Debit | Balance\n")))
  (testing "statement of a deposit"
    (is (= (print-statement (deposit (newAccount) 300 (fn [] "01-01-2019"))) "Date | Credit | Debit | Balance\n01-01-2019 | 300 |  | 300")))
  (testing "statement of a withdraw"
    (is (= (print-statement (withdraw (newAccount) 300 (fn [] "01-01-2019"))) "Date | Credit | Debit | Balance\n01-01-2019 |  | 300 | -300")))
  (testing "statement of several opeartions"
    (is (= (print-statement (-> (newAccount)
                                (deposit 1000 (fn [] "01-01-2019"))
                                (withdraw 500 (fn [] "02-01-2019"))
                                (deposit 300 (fn [] "03-01-2019"))))
           (str "Date | Credit | Debit | Balance\n"
                "01-01-2019 | 1000 |  | 1000\n"
                "02-01-2019 |  | 500 | 500\n"
                "03-01-2019 | 300 |  | 800")))))
