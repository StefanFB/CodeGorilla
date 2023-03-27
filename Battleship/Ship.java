package battleship;

public class Ship {
    String name;
    int length;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    static final public Ship carrier = new Ship("Aircraft Carrier", 5);
    static final public Ship battleship = new Ship("Battleship", 4);
    static final public Ship submarine = new Ship("Submarine", 3);
    static final public Ship cruiser = new Ship("Cruiser", 3);
    static final public Ship destroyer = new Ship("Destroyer", 2);
}
