(ns project.db
  (:require [clojure.string :as str]))
(use 'clojure.java.io)

(def customerList (atom {})) ; to store customer info to use for customer table
(def productList (atom {})) ; to store product info to use for product table
(def salesList (atom {})) ; to store sales info to use for sales table
(def report (atom {})) ; to store report info

; function customerTable reads the text file "cust.txt" line by line and stores all its contents
; by splitting at every bar "|" found, then the data are distributed accordingly then stored
; into a map and then sent in a list form
(defn customerTable [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (def data (str/split line #"\|"))
      (def custID (get data 0))
      (def custName (get data 1))
      (def address (get data 2))
      (def phoneNumber (get data 3))
      (def keyValues (list custName address phoneNumber))
      (swap! customerList assoc custID keyValues))))

; function prodcutTable reads the text file "prod.txt" line by line and stores all its contents
; by splitting at every bar "|" found, then the data are distributed accordingly then stored
; into a map and then sent in a list form
(defn productTable [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (def data (str/split line #"\|"))
      (def prodID (get data 0))
      (def itemDescription (get data 1))
      (def unitCost (get data 2))
      (def values (list itemDescription unitCost))
      (swap! productList assoc prodID values))))

; function salesTable reads the text file "sales.txt" line by line and stores all its contents
; by splitting at every bar "|" found, then the data are distributed accordingly then stored
; into a map and then sent in a list form
(defn salesTable [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (def data (str/split line #"\|")) ; reads line by line breaking into n different components
      (def salesID (get data 0))
      (def custID (get data 1))
      (def prodID (get data 2))
      (def itemCount (get data 3))
      (def sales (list custID  prodID itemCount)) ; customer name , product number, amount
      (def values (list (nth (get @customerList custID) 0) (nth (get @productList prodID) 0) itemCount))
      (swap! report assoc salesID sales)
      (swap! salesList assoc salesID values))))