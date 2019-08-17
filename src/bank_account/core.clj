(ns bank-account.core
  (:gen-class)
  (:require [bank-account.account.account :refer :all]
            [bank-account.account.statement :refer :all]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (-> (newAccount)
       (deposit 1000 (fn [] "01-01-2019"))
       (withdraw 500 (fn [] "02-01-2019"))
       (deposit 300 (fn [] "03-01-2019"))
       (print-statement)
       (println)))
