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

---

## Stage 2
### Objectives

Implement the `/purchase` endpoint that handles `POST` requests and marks a booked ticket as purchased.

A request should contain the following data:

- `row` — the row number;
- `column` — the column number.

Take these variables and check if the specified ticket is available. If the ticket is booked, mark the seat as purchased and don't show it in the list.

If the purchase is successful, the response body should be as follows:

```json
{
    "row": 5,
    "column": 7,
    "price": 8
}
```

The ticket price is determined by a row number. If the row number is less or equal to 4, set the price at **10**. All other rows cost **8** per seat.

If the seat is taken, respond with a `400 (Bad Request)` status code. The response body should contain the following:

```json
{
    "error": "The ticket has been already purchased!"
}
```

If users pass a wrong row/column number, respond with a `400` status code and the following line:

```json
{
    "error": "The number of a row or a column is out of bounds!"
}
```

Show the ticket price when the `/seats` endpoint is accessed. See the first example for more details.

---

## Stage 3
###
