(ns bank-account.core
  (:gen-class)
  (:require [clojure.string :as string])
  (:require [clojure.core.match :refer [match]]))

(defrecord Operation [date amount type])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn newAccount [] '())

(defn new-operation [date amount type] (Operation. date amount type))

(defn deposit [account amount now]
  (cons (new-operation (now) amount "CREDIT") account))

(defn withdraw [account amount now]
  (cons (new-operation (now) amount "DEBIT") account))

(defn to-amount [operation]
  (let [amount (.amount operation)]
    (match [(.type operation)]
      ["CREDIT"] amount
      ["DEBIT"] (- amount))))

(defn sum [numbers] (reduce + 0 numbers))

(defn balance [account]
  (->> account
       (map to-amount)
       (sum)))

(defn statement-line [operation balance]
  (let [amount (.amount operation) date (.date operation)]
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
      { :balance newBalance :statementLines newStatementLines}))

(defn print-statement [account]
  (->> account
       (reverse)
       (reduce agregate {:balance 0 :statementLines '()})
       (:statementLines)
       (reverse)
       (string/join "\n")
       (str "Date | Credit | Debit | Balance\n")))