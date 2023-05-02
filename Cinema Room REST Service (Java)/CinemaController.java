package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class CinemaController {

    Room cinemaRoom = new Room(9,9);

    @GetMapping("/")
    public String greeting() {
        return "Hello, Spring";
    }

    @GetMapping("/seats")
    public Room showAvailableSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTickets(@RequestBody SeatLocation seatLocation) {

        // Check for empty post-request
        if (seatLocation == null) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check for null-params
        try {
            int test1 = seatLocation.row;
            int test2 = seatLocation.column;
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Translate the seatLocation to row and column
        int row = seatLocation.row;
        int column = seatLocation.column;

        // Check for out of bounds seat
        if (row < 1 || row > cinemaRoom.rows || column < 1 || column > cinemaRoom.columns) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Get the corresponding seat. Check availability else set it to false
        Seat seat = cinemaRoom.getSeat(row, column);
        if (!seat.available) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"),
                    HttpStatus.BAD_REQUEST);
        } else {
            seat.available = false;
        }

        // Generate ticket with this seat
        Ticket ticket = new Ticket(seat);
        cinemaRoom.soldTickets.add(ticket);

        // Add the price of the seat to the income
        cinemaRoom.income += seat.price;

        for (Ticket T : cinemaRoom.soldTickets) {
            System.out.println(Map.of(
                    "token", T.token,
                    "ticket", T.seat.row + " " + T.seat.column
            ));
        }

        return new ResponseEntity<>(Map.of(
                "token", ticket.token,
                "ticket", ticket.seat),
                HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTickets(@RequestBody String postRequest) {

        // Check for empty post-request
        if (postRequest == null) {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"),
                    HttpStatus.BAD_REQUEST);
        }

        // System.out.println("Token is: " + postRequest);

        // Parse the token into a correct token-string
        String[] tokenSplitOne = postRequest.split(":");
        String[] tokenSplitTwo = tokenSplitOne[1].split("\"");
        String token = tokenSplitTwo[1];

        // System.out.println("Token is: " + token);


        // Check for invalid token, if token is set to "wrong" the token does not exist (via method)
        Ticket ticket = cinemaRoom.getTicket(token);
        if (ticket.token.equals("wrong")) {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Else the token is valid, and we can return the ticket
        // Also set the seat to be available again
        else {
            // Reset seat availability and deduct price from income
            ticket.seat.available = true;
            cinemaRoom.income -= ticket.seat.price;

            return new ResponseEntity<>(Map.of(
                    "returned_ticket", ticket.seat),
                    HttpStatus.OK);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> showStats(@RequestParam(value="password", required = false) String password) {
        if (password == null) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);
        } else if (password.equals("super_secret")) {
            return new ResponseEntity<>(Map.of(
                    "current_income", cinemaRoom.income,
                    "number_of_available_seats", cinemaRoom.calcAvailableSeats(),
                    "number_of_purchased_tickets", cinemaRoom.calcSoldTickets()
                    ),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
