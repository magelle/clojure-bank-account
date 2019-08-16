(ns bank-account.core
  (:gen-class)
  (:require [bank-account.operation :refer :all]
            [clojure.core.match :refer [match]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn newAccount [] '())

(defn deposit [account amount now]
  (cons (new-operation (now) amount "CREDIT") account))

(defn withdraw [account amount now]
  (cons (new-operation (now) amount "DEBIT") account))
