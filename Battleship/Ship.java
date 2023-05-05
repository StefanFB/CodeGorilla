package battleship;

public class Ship {
    String name;
    int length;
    int shipStartX;
    int shipStartY;
    boolean horizontal;
    boolean destroyed;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.shipStartX = 0;
        this.shipStartY = 0;
        this.horizontal = true;
        this.destroyed = false;
    }

    static final public Ship carrier = new Ship("Aircraft Carrier", 5);
    static final public Ship battleship = new Ship("Battleship", 4);
    static final public Ship submarine = new Ship("Submarine", 3);
    static final public Ship cruiser = new Ship("Cruiser", 3);
    static final public Ship destroyer = new Ship("Destroyer", 2);
}
