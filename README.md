# Telstra Belong Code Challenge

The task is to implement an API that provides the capabilities to 
- get all phone numbers
- get all phone numbers of a single customer
- activate a phone number

It took around 2-3 hours to complete the task.

#Interface Specification

- get all phone numbers

Request

GET /v1/allphonenumbers

Response

connection →keep-alive
content-type →application/json
date →Sun, 27 Jun 2021 04:40:09 GMT
keep-alive →timeout=60
transfer-encoding →chunked

[{"id":"1","phoneNumber":"0400000001","active":true},{"id":"2","phoneNumber":"0400000002","active":true},{"id":"3","phoneNumber":"0400000003","active":false}]


- get all phone numbers of a single customer

Request 

GET /v1/phonenumbers?customerId=

Response

connection →keep-alive
content-type →application/json
date →Sun, 27 Jun 2021 04:41:12 GMT
keep-alive →timeout=60
transfer-encoding →chunked

[{"id":"1","phoneNumber":"0400000001","active":true},{"id":"2","phoneNumber":"0400000002","active":true}]


- activate a phone number

Request

PUT /v1/phonenumber=

Request Body

{
	"active" : "true"
}	


Response

connection →keep-alive
content-type →application/json
date →Sun, 27 Jun 2021 04:42:15 GMT
keep-alive →timeout=60
transfer-encoding →chunked

{"id":"3","phoneNumber":"0400000003","active":true}


# Technologies

JAVA 8
Hibernate

# Sample data

Assumed the phone numbers as a static data structure that is initialised when your program runs.
Used Hibernate to implement a testing database, and testing data are saved in data.sql under /src/resources.

# Implementation

- ApiApplication runs on port 8080
- Version 1.0.1

Prerequisite:

- You need to have java installed on your system.

Running the program from the command line:

- Go to the directory .../api/target
- Run "java -jar api-1.0.1-SNAPSHOT.jar" to start the program
- Open the Postman to send request HTTP calls
Example:
Get request: http://localhost:8080/v1/allphonenumbers
Get request: http://localhost:8080/v1/phonenumbers?customerId=100
Put request: http://localhost:8080/v1/phonenumber=0400000003
             RequestBody: {
                          	"active" : "true"
                          }	
