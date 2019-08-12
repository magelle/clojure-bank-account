(ns bank-account.core
  (:gen-class)
  (:require [clojure.string :as string]))

(defrecord Operation [date amount])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn newAccount [] '())

(defn new-operation [date amount] (Operation. date amount))

(defn deposit [account amount now]
  (cons (new-operation (now) amount) account))

(defn withdraw [account amount now]
  (cons (new-operation (now) (- amount)) account))

(defn sum [numbers] (reduce + 0 numbers))

(defn balance [account]
  (->> account
       (map :amount)
       (sum)))

(defn statement-line [operation]
  (str (.date operation) " | " (.amount operation)))

(defn print-statement [account]
  (->> account
       (reverse)
       (map statement-line)
       (string/join "\n")
       (str "Date | Amount\n")))