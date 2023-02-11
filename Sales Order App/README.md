# Description

The goal of this small project is to develop a simple Sales Order application 
with Clojure. The application will load data from a series of three disk 
file. This data will then form the sales database. Each table will have a 
schema that indicates the fields inside. The DB looks like this:

----------------------

cust.txt: This is the data for the customer table. The schema is

<custID, name, address, phoneNumber>

An example of the cust.txt disk file might be:

1|John Smith|123 Here Street|456-4567

2|Sue Jones|43 Rose Court Street|345-7867

3|Fan Yuhong|165 Happy Lane|345-4533

----------------------

prod.txt: This is the data for the product table. The schema is

<prodID, itemDescription, unitCost>

An example of the prod.txt disk file might be:

1|shoes|14.96

2|milk|1.98

3|jam|2.99

4|gum|1.25

5|eggs|2.98

6|jacket|42.99

----------------------

sales.txt: This is the data for the main sales table. The schema is

<salesID, custID, prodID, itemCount>

An example of the sales.txt disk file might be:

1|1|1|3

2|2|2|3

3|2|1|1

4|3|3|4

----------------------

The first record (salesID 1), for example, indicates that John Smith (customer 1) bought 3 pairs of 
shoes (product 1). Again, you can assume that all of the values in the file (e.g., custID, prodID) are 
valid.

The following menu is provided for the user in order to interact with the application. The menu looks 
like this:

----------------------

*** Sales Menu ***

1. Display Customer Table
2. Display Product Table
3. Display Sales Table
4. Total Sales for Customer
5. Total Count for Product
6. Exit

Enter an option?

----------------------

This small application introduced me to functional programming and what it is used for.
