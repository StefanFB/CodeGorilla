package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Seat {

    @JsonProperty("row")
    int row;

    @JsonProperty("column")
    int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
