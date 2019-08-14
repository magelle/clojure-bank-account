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

(defn statement-line [operation]
  (let [amount (.amount operation) date (.date operation)]
    (match [(:type operation)]
      ["CREDIT"] (str date " | " amount " | ")
      ["DEBIT"] (str date " |  | " amount))))

(defn print-statement [account]
  (->> account
       (reverse)
       (map statement-line)
       (string/join "\n")
       (str "Date | Credit | Debit\n")))