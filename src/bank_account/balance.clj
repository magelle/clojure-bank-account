(ns bank-account.balance
    (:gen-class)
    (:require [bank-account.operation :refer :all]))

(defn sum [numbers] (reduce + 0 numbers))

(defn balance [account]
  (->> account
       (map to-amount)
       (sum)))
