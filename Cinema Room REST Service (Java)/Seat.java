package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Seat {

    int row;
    int column;

    @JsonProperty("price")
    int price;

    @JsonIgnore
    boolean available;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = setPrice();
        this.available = true;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    private int setPrice() {
        if (this.row <= 4) {
            return 10;
        } else {
            return 8;
        }
    }
}
