# Cinema Room REST Service (Java) 
##  Learning outcomes
In this project, you will create a simple Spring REST service that will help you manage a small movie theatre. Handle HTTP requests in controllers, create services and respond with JSON objects.

## About
Always wanted to have your private movie theater and screen only the movies you like? You can buy a fancy projector and set it up in a garage, but how can you sell tickets? Having a booth is old-fashioned, so let's create a special service for that! Make good use of Spring and write a REST service that can show the available seats, sell and refund tickets, and display the statistics of your venue. Pass me the popcorn, please!

---

## Stage 1
### Objectives

Implement the /seats endpoint that handles `GET` requests and returns the information about the movie theatre.

The response should contain information about the rows, columns, and available seats. The response is a JSON object and has the following format:

``` json
{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1
      },

        ........

      {
         "row":9,
         "column":8
      },
      {
         "row":9,
         "column":9
      }
   ]
}
```

Our cinema room has 9 rows with 9 seats each, so the total number of respective rows and columns also amounts to 9 each.

Note that the `available_seats` array contains 81 elements, as there are 81 seats in the room.
