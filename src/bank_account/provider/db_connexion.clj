(ns bank-account.provider.db-connexion
  (:require [clojure.java.jdbc :as sql]))


; {:classname   "org.h2.Driver"
;  :subprotocol "h2"
;  :subname     "dbc:h2:~/clojure-db"
;  :user        "sa"}


(def h2_db {:classname   "org.h2.Driver"
            :subprotocol "h2:mem"
            :subname "clojure;DB_CLOSE_DELAY=-1"
            :user "sa"
            :password ""})

(sql/db-do-commands h2_db
                    (sql/create-table-ddl :operation
                                          [[:date "varchar(10)"]
                                           [:amount "integer"]
                                           [:type "varchar(50)"]]))

(sql/insert! h2_db :operation {:date "13/04/2019" :amount 14000 :type "CREDIT"}) 
(sql/insert! h2_db :operation {:date "14/04/2019" :amount 7000 :type "DEBIT"}) 

(sql/query h2_db ["SELECT * FROM operation"])
