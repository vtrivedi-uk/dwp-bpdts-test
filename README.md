# DWP Digital Tech Test - Vikash Trivedi

## Brief

Using the language of your choice please build your own API which calls the API at 
https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either 
living in London, or whose current coordinates are within 50 miles of London.

## Solution

The solution is a java based spring boot app and exposes a rest endpoint to perform a generic search. 

The endpoints return a JSON list of the relevant users to the client.

### Building and Running

To build the solution run the following commands from the root folder:

```
mvn clean install

java -jar dwp-bpdts-0.0.1-SNAPSHOT.jar
```

### How to use

#### Api Call

The solution has one GET rest endpoint that allows parameters to determine the distance and location to use in the 
search.

To return all users within 50 miles of London:
`
GET http://localhost:8686/api/users/within/50/miles/of/london
`
To search with different parameters, replace the values in the angle brackets:
`
GET `http://localhost:8686/users/within/<distance>/<unit>/of/<city>`
`
