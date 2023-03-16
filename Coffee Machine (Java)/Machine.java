package machine;

public class Machine {
    int water = 400;
    int milk = 540;
    int coffee = 120;
    int cups = 9;
    int money = 550;

    Coffee espresso = new Coffee("espresso", 250, 0, 16, 4);
    Coffee latte = new Coffee("latte", 350, 75, 20, 7);
    Coffee cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);
}
