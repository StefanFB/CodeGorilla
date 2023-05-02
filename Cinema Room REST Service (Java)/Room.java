package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Room {
    @JsonProperty("total_rows")
    public int rows;
    @JsonProperty("total_columns")
    public int columns;
    @JsonProperty("available_seats")
    public ArrayList<Seat> availableSeats = new ArrayList<>();

    public Room(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.availableSeats = getAvailableSeats();
    }

    private ArrayList<Seat> getAvailableSeats() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                availableSeats.add(new Seat(i,j));
            }
        }
        return availableSeats;
    }

    public Seat getSeat(int row, int column) {
        int index = (row - 1) * columns + column -1;
        return availableSeats.get(index);
    }
}
