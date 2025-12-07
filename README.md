# Ride-Sharing-Platform

================= **Setup Instructions (Docker)** =====================
**1-CD Ride-Sharing-Platform/**
Make sure below folder exisit

Ride-Sharing-Platform
API-Gateway-Service/:
Dockerfile	HELP.md		mvnw		mvnw.cmd	pom.xml		src		target

customer-service/:
Dockerfile	HELP.md		mvnw		mvnw.cmd	pom.xml		src		target

db-init/:
init-databases.sql

driver-service/:
Dockerfile	HELP.md		mvnw		mvnw.cmd	pom.xml		src		target

**2- ##RUN## docker compose up -d**

[+] Running 4/4
 ✔ Container dbtest1           Started                                                                                                                                                                                                                                   0.2s 
 ✔ Container driver-service    Started                                                                                                                                                                                                                                   0.2s 
 ✔ Container api-gateway       Started                                                                                                                                                                                                                                   0.2s 
 ✔ Container customer-service  Started    


================= **POSTMAN Collections =====================
##OPEN## POSTMAN Appliction(TEST)
 ** API GateWay Endpoints**
 1- [POST] Login ---For Authentication and Authorization
 
 **Customer Endpoints**
 2- [POST] customerRequrstRide --- For Request ride from API-GateWay
 3- [GET]viewCustomerRideRequrst ---For View Cutomer Rides history

 **Driver Endpoints**
 4- [GET]driverViewAvaliblRides  ----FOR View Unchosen Rides
 5- [PUT]driverAddRide ----FOR Driver chose(accept) Unchosen Ride.
 6- [PUT]updateDriverStatus ---FOR Dirver update the availblity (Online/Offline)
 7- [GET]driverRide  ---FOR Driver view history of rides they previously accepted

###### NOTE #### ALL Requests Route to Customer/Dirver MS through API GateWay MS PORT 8765 #####


================**  Business Logic Applied ** ====================
1-API Gateway MS As Entry point port 8765
2-Diver Can't Accept any Ride if his Availablity (OFFLINE)












=====================****  PRE INTIALLIZED DATA  ****========================

**=======> NOTE : database already created and loaded with a specific set of data <========*

1- db-init/init-databases.sql

CREATE DATABASE customer_db;
CREATE DATABASE driver_db;
CREATE DATABASE gatewaydb;



2- Ten User Informations inserted while run application (Password hashed by using *BCryptPasswordEncoder();* ) 

*gatewaydb=# select * from app_user;*

id |        email        |                           password                           | phone_number |   role   | username  
----+---------------------+--------------------------------------------------------------+--------------+----------+-----------
  1 | customer0@gmail.com | $2a$10$z8XFSjS1DHJ2NFcasYLEzOTqMS96GNhW5HN3ubVJjIDwaWGMMU90m | 1234567890   | CUSTOMER | customer0
  2 | customer1@gmail.com | $2a$10$oEOwXN5zhTdm2UAlCzSDtucjNKDonWmB3Udhva4h5ZI8k6OyNuH86 | 1234567890   | CUSTOMER | customer1
  3 | customer2@gmail.com | $2a$10$ooRAWNDdg9ollKEzZP63A.bw1L9F.aXXNf.nnAgQESDsvY5h/R0pK | 1234567890   | CUSTOMER | customer2
  4 | customer3@gmail.com | $2a$10$zUtBlzbHNDv/RBT3fjss2emhNN/M6MZf/7HpAAx8ECHy/0GhWiidm | 1234567890   | CUSTOMER | customer3
  5 | customer4@gmail.com | $2a$10$MaBuzvPOR9bLsKq4U/185OFMDQ7Ax9czkWRD.hq/2dyWp0SVpMGHK | 1234567890   | CUSTOMER | customer4
  6 | driver0@gmail.com   | $2a$10$NDHhrh8OKa8HJolJGwW1I.fMOh8XoEwenzGQPAfWcP/bPqOpL4UZa | 1234567890   | DRIVER   | driver0
  7 | driver1@gmail.com   | $2a$10$GUkeLqstZVv8q2arAWUqBOTwr3RxAycePw0HOWDkHLLTRKXcIuD2e | 1234567890   | DRIVER   | driver1
  8 | driver2@gmail.com   | $2a$10$hXEyLgrlasPpL0Tf5k9geev5Vh9yZsvs4Bsoik7sCKTGeXcWmI0Pq | 1234567890   | DRIVER   | driver2
  9 | driver3@gmail.com   | $2a$10$plL./vIv9ZJNTUBrClc7XOtnBQAY8FiZFlk35jW5ycV75eVgIIugi | 1234567890   | DRIVER   | driver3
 10 | driver4@gmail.com   | $2a$10$gu8QPWxHmSRwXdN/znnWzejKHAAUuI1iBg1rLUriyWNItzQXyO2o6 | 1234567890   | DRIVER   | driver4


3- Password as Plain TEXT to Test **Authentication** login endpont using [psotman -->[POST]Login]


 username  |                           password                           
-----------+--------------------------------------------------------------
 customer0 | password0
 customer1 | password1
 customer2 | password2
 customer3 | password3
 customer4 | password4
 driver0   | password0
 driver1   | password1
 driver2   | password2
 driver3   | password3
 driver4   | password4



 
