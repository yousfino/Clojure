(ns project.app
  (:require [project.db :as db])
  (:require [project.menu :as menu])
  (:gen-class))
(use 'clojure.java.io)

; function showProducts to display the product table
(defn showProducts [contents]
  (def counter (count (keys contents)))
  (dotimes
   [i (+ 1 counter)]
    (def prodKey (str i))
    (if (= prodKey "0") ; when all keys have been stored
      (println "----Product Table----\n")
      (println prodKey ":" (get contents prodKey)))) ; print table showing all products
  (println))

; function prodReport to find how many times an item/product was purchased by a customer
(defn prodReport [items item]
  (def total (atom 0)) ; declare total variable to store total number of purchases
  (def size  (count items)) ; size variable to help build the table
  (dotimes [i size]
    (def keyATi (str i))
    (def getProd (nth (get items keyATi) 1)) ; searching for the target product
    (if (= getProd item) ; add unit cost to customer total if customer name matches with given name
      (do
        (def amount (Integer/parseInt (nth (get items keyATi) 2))) ; find total amount an items has been purchased by a customer
        (reset! total (+ @total amount)))))
  (if (= @total 0)
    (println @total "found.")
    (println item ":" @total))) ; display the total

; function getCustomers to print the customer table
(defn getCustomers [custList]
  (def counter (count (keys custList)))
  (dotimes
   [i (+ 1 counter)]
    (def custKey (str i)) ; custKey to store customer's key at i
    (if (= custKey "0") ; when nothing is left to read
      (println "----Customer Table----\n")
      (println custKey ":" (get custList custKey)))) ; display all customers
  (println))

; function salesTable to print the table with all sales info
(defn salesTable [items]
  (def allSales (count (keys items)))
  (dotimes ; do for all items
   [i  allSales]
    (def keyATi (str i))
    (if (= keyATi "0") ; when nothing is left to be read
      (println "----Sales Table----\n")
      (println keyATi ":" (get items keyATi)))) ; displays sales info
  (println))

; function totalPrice to find number of purchases by a customer and the total price of the purchases
(defn totalPrice [items contents name report]
  (def total (atom 0)) ; variable to store total price
  (def elements (count items))
  (dotimes [i elements] ; visit all items
    (def keyATi (str i))
    (def cust (nth (get items keyATi) 0))
    (if (= cust name) ; increment totalSum if current customer name matches with target name
      (do
        (def amount (Double/parseDouble (nth (get items keyATi) 2))) ; get how many times a customer has purchased the item/product
        (def  id1  (nth (get report keyATi) 1))
        (def totalSum (*  amount (read-string (nth (get contents id1) 1)))) ; calculate total price
        (reset! total (+ @total totalSum)))))
  (if (= @total 0)
    (println "$" @total)
    (println name "$" (format "%.2f" @total)))) ; display total price

(defn -main
  [& args]
  
  (def custDB (db/customerTable "C:/Users/User/Desktop/cust.txt.txt")) ; read cust.txt into DB
  (def prodDB (db/productTable "C:/Users/User/Desktop/prod.txt.txt")) ; read prod.txt into DB
  (def salesDB (db/salesTable "C:/Users/User/Desktop/sales.txt.txt")) ; read sales into DB
  (def report @db/report) ; get report info
  (def contents @db/productList) ; get product data
  (def custList @db/customerList) ; get customer data
  (def items @db/salesList) ; get sales data
  (def sel) ; sel variable to store user input from menu
  
  (while (not= sel "6")
    (do
      (menu/menu)
      (println "\nEnter an option?")
      (def sel (read-line)) ; get user input and store in sel
      
      (if (= sel "1")
        (getCustomers custList)) ; if user input = 1, display customer table
      
      (if (= sel "2")
        (showProducts contents)) ; display product table
      
      (if (= sel "3")
        (salesTable items)) ; display sales table

      (if (= sel "4") ; display amount of money from purchases plus customer info
        (do
          (println "Please enter the name of the customer:")
          (def findCustName (read-line))
          (totalPrice items contents findCustName report)
          (println)))
      
      (if (= sel "5") ; display product info
        (do
          (println "Please enter the name of the product: ")
          (def findProd (read-line))
          (prodReport items findProd)
          (println)))
      
      (if (= sel "6") ; exit the while loop and terminate the program
        (println "Good Bye")))))
