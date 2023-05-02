package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Room {
    @JsonProperty("total_rows")
    public int rows;
    @JsonProperty("total_columns")
    public int columns;
    @JsonProperty("available_seats")
    public ArrayList<Seat> allSeats = new ArrayList<>();

    @JsonIgnore
    public ArrayList<Ticket> soldTickets = new ArrayList<>();

    @JsonIgnore
    public int income;

    public Room(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.allSeats = setAllSeats();
        this.income = 0;
    }

    private ArrayList<Seat> setAllSeats() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                allSeats.add(new Seat(i,j));
            }
        }
        return allSeats;
    }

    public int calcAvailableSeats() {
        int availableSeats = 0;
        for (Seat seat : allSeats) {
            if (seat.available) availableSeats++;
        }
        return availableSeats;
    }

    public int calcSoldTickets() {
        int totalSeats = rows * columns;
        return totalSeats - calcAvailableSeats();
    }

    public Seat getSeat(int row, int column) {
        int index = (row - 1) * columns + column -1;
        return allSeats.get(index);
    }

    public Ticket getTicket(String uuidToken) {
        for (Ticket T : soldTickets) {
            if (T.token.equals(uuidToken)) {
                System.out.printf("Ticket found with uuid %s!\n", uuidToken);
                return T;
            }
        }
        return new Ticket();
    }
}
