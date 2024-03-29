package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    String token;

    @JsonProperty("ticket")
    Seat seat;

    public Ticket(Seat seat) {
        this.token = UUID.randomUUID().toString();
        this.seat = seat;
    }

    public Ticket() {
        this.token = "wrong";
        this.seat = new Seat();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
