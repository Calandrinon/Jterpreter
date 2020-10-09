package View;

import Controller.Controller;
import Exceptions.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
    private final Controller controller;
    private boolean running;
    private int option;

    public View(Controller controller) {
        this.controller = controller;
        this.running = true;
        this.option = 0;
    }

    private void print_menu() {
        String message = "\n\nSelect an option:\n" +
                "1. Add a tomato\n" +
                "2. Remove a tomato by id\n" +
                "3. Add a pepper\n" +
                "4. Remove a pepper by id\n" +
                "5. Add an eggplant\n" +
                "6. Remove an eggplant by id\n" +
                "7. Get all vegetables with their weight greater than 0.2 kg\n" +
                "8. Get all vegetables\n" +
                "0. Exit";
        System.out.println(message);
    }

    private void read_option() throws IOException {
        System.out.print("Enter an option: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.option = Integer.parseInt(reader.readLine());
    }

    private int read_id() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the id:");
        String id_as_string = reader.readLine();
        int id = Integer.parseInt(id_as_string);
        return id;
    }

    private double read_weight() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the weight(in kg):");
        String weight_as_string = reader.readLine();
        double weight_in_kg = Double.parseDouble(weight_as_string);
        return weight_in_kg;
    }

    private double read_price() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the price:");
        String price_as_string = reader.readLine();
        double price = Double.parseDouble(price_as_string);
        return price;
    }

    private void add_tomato() throws IOException, RepositoryException {
        int id = this.read_id();
        double weight_in_kg = this.read_weight();
        double price = this.read_price();
        this.controller.add_tomato(id, weight_in_kg, price);
    }

    private void add_pepper() throws IOException, RepositoryException {
        int id = this.read_id();
        double weight_in_kg = this.read_weight();
        double price = this.read_price();
        this.controller.add_pepper(id, weight_in_kg, price);
    }

    private void add_eggplant() throws IOException, RepositoryException {
        int id = this.read_id();
        double weight_in_kg = this.read_weight();
        double price = this.read_price();
        this.controller.add_eggplant(id, weight_in_kg, price);
    }

    private void remove_tomato() throws IOException, RepositoryException {
        int id = this.read_id();
        this.controller.remove_tomato(id);
    }

    private void remove_pepper() throws IOException, RepositoryException {
        int id = this.read_id();
        this.controller.remove_pepper(id);
    }

    private void remove_eggplant() throws IOException, RepositoryException {
        int id = this.read_id();
        this.controller.remove_eggplant(id);
    }

    private void filter_vegetables() {
        System.out.print(this.controller.filter_vegetables());
    }

    private void get_all_vegetables() {
        System.out.println(this.controller.get_all_vegetables());
    }

    public void run() throws IOException, RepositoryException {
        this.controller.add_tomato(25, 0.1, 0.2);
        this.controller.add_pepper(32, 0.3, 0.4);
        this.controller.add_eggplant(52, 0.9, 0.7);
        this.controller.add_pepper(67, 0.3, 0.4);

        while (this.running) {
            this.print_menu();
            this.read_option();

            try {
                switch (this.option) {
                    case 0 -> this.running = false;
                    case 1 -> this.add_tomato();
                    case 2 -> this.remove_tomato();
                    case 3 -> this.add_pepper();
                    case 4 -> this.remove_pepper();
                    case 5 -> this.add_eggplant();
                    case 6 -> this.remove_eggplant();
                    case 7 -> this.filter_vegetables();
                    case 8 -> this.get_all_vegetables();
                    default -> System.out.println("The option you entered does not exist...");
                }
            } catch (RepositoryException re) {
                System.out.println(re.getMessage());
            }
        }
    }
}
