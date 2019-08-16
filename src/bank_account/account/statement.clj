(ns bank-account.account.statement
  (:gen-class)
  (:require [clojure.string :as string]
            [bank-account.account.account :refer :all]
            [bank-account.account.operation :refer :all]
            [clojure.core.match :refer [match]]))

(defn statement-line [operation balance]
  (let [amount (:amount operation) date (:date operation)]
    (match [(:type operation)]
      ["CREDIT"] (str date " | " amount " |  | " balance)
      ["DEBIT"] (str date " |  | " amount " | " balance))))

(defn add-operation-to-balance [balance operation]
  (+ balance (to-amount operation)))

(defn add-operation-to-statement-lines [statementLines operation balance]
  (cons (statement-line operation balance) statementLines))

(defn agregate [balancedOperations operation]
  (let [balance (:balance balancedOperations)
        statementLines (:statementLines balancedOperations)
        newBalance (add-operation-to-balance balance operation)
        newStatementLines (add-operation-to-statement-lines statementLines operation newBalance)]
    {:balance newBalance :statementLines newStatementLines}))

(defn print-statement [account]
  (->> account
       (reverse)
       (reduce agregate {:balance 0 :statementLines '()})
       (:statementLines)
       (reverse)
       (string/join "\n")
       (str "Date | Credit | Debit | Balance\n")))
