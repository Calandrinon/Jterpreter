package Model;

public class Eggplant {
    private final double weight_in_kg, price;
    private final int id;

    Eggplant(int id, double weight_in_kg, double price) {
        this.weight_in_kg = weight_in_kg;
        this.price = price;
        this.id = id;
    }

    public int get_id() {
        return this.id;
    }

    public double get_weight() {
        return this.weight_in_kg;
    }

    public double get_price() {
        return this.price;
    }

    public boolean is_heavy() {
        return this.weight_in_kg > 0.2;
    }
}
