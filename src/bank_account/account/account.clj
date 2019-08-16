(ns bank-account.account.account
  (:require [bank-account.account.operation :refer :all]))

(defn newAccount [] '())

(defn deposit [account amount now]
  (cons (new-operation (now) amount "CREDIT") account))

(defn withdraw [account amount now]
  (cons (new-operation (now) amount "DEBIT") account))
