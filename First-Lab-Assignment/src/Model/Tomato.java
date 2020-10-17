package Model;

public class Tomato implements Vegetable {
    private final double weight_in_kg, price;
    private final int id;

    public Tomato(int id, double weight_in_kg, double price) {
        this.weight_in_kg = weight_in_kg;
        this.price = price;
        this.id = id;
    }

    @Override
    public int get_id() {
        return this.id;
    }


    @Override
    public double get_weight() {
        return this.weight_in_kg;
    }


    @Override
    public double get_price() {
        return this.price;
    }


    @Override
    public boolean is_heavy() {
        return this.weight_in_kg > 0.2;
    }

    public String toString() {
        return "Tomato|ID: " + Integer.toString(this.id) + "; Weight(kg): " + Double.toString(this.weight_in_kg) + "; Price: " + Double.toString(this.price);
    }
}
