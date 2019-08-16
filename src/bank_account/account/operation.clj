(ns bank-account.account.operation
  (:gen-class)
  (:require [clojure.core.match :refer [match]]))

(defrecord Operation [date amount type])

(defn new-operation [date amount type] (Operation. date amount type))

(defn to-amount [operation]
  (let [amount (.amount operation)]
    (match [(.type operation)]
      ["CREDIT"] amount
      ["DEBIT"] (- amount))))
