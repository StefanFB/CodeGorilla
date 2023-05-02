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
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check for null-params
        try {
            int test1 = seatLocation.row;
            int test2 = seatLocation.column;
        } catch (Exception e) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Translate the seatLocation to row and column
        int row = seatLocation.row;
        int column = seatLocation.column;

        // Check for out of bounds seat
        if (row < 1 || row > cinemaRoom.rows || column < 1 || column > cinemaRoom.columns) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Get the corresponding seat
        Seat seat = cinemaRoom.getSeat(row, column);
        if (!seat.available) {
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"),
                    HttpStatus.BAD_REQUEST);
        }
        seat.available = false;

        return new ResponseEntity(Map.of(
                "row", seat.row,
                "column", seat.column,
                "price", seat.price),
                HttpStatus.OK);
    }
}
