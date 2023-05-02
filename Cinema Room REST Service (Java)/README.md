# Cinema Room REST Service (Java)

```
   ____ _                              ____
  / ___(_)_ __   ___ _ __ ___   __ _  |  _ \ ___   ___  _ __ ___
 | |   | | '_ \ / _ \ '_ ` _ \ / _` | | |_) / _ \ / _ \| '_ ` _ \
 | |___| | | | |  __/ | | | | | (_| | |  _ < (_) | (_) | | | | | |
  \____|_|_| |_|\___|_| |_| |_|\__,_| |_| \_\___/ \___/|_| |_| |_|

 :: Cinema Room REST Service ::
```

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
### Objectives

Change the JSON response when a customer purchases a ticket by making a `POST` request to the `/purchase` endpoint. Turn it into the following format:

```json
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

We recommend using the `randomUUID()` method of the `UUID` class to generate tokens. Take a look at this [UUID](https://www.baeldung.com/java-uuid) Guide by Baeldung if you're interested in more detail.

Implement the `/return` endpoint, which will handle `POST` requests and allow customers to refund their tickets.

The request should have the `token` feature that identifies the ticket in the request body. Once you have the token, you need to identify the ticket it relates to and mark it as available. The response body should be as follows:

```json
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

The `returned_ticket` should contain the information about the returned ticket.

If you cannot identify the ticket by the token, make your program respond with a `400` status code and the following response body:

```json
{
    "error": "Wrong token!"
}
```

## Stage 4
### Objectives

Implement the `/stats` endpoint that will handle `POST` requests with URL parameters. If the URL parameters contain a `password` key with a `super_secret` value, return the movie theatre statistics in the following format:

```json
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```

Take a look at the description of keys:

- `current_income` — shows the total income of sold tickets.
- `number_of_available_seats` — shows how many seats are available.
- `number_of_purchased_tickets` — shows how many tickets were purchased.

If the parameters don't contain a password key or a wrong value has been passed, respond with a `401` status code. The response body should contain the following:

```json
{
    "error": "The password is wrong!"
}
```
