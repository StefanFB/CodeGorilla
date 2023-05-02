package cinema;

import org.springframework.web.bind.annotation.*;


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
}
