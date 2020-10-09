package Controller;

import Exceptions.RepositoryException;
import Model.Eggplant;
import Model.Pepper;
import Model.Tomato;
import Model.Vegetable;
import Repository.RepositoryInterface;

public class Controller {
    private final RepositoryInterface repository;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public String filter_vegetables(){
        String filtered_content = "";
        Vegetable[] container = this.repository.get_container();
        int container_size = this.repository.get_size();
        if (container_size > 0) {
            for (int vegetable_index = 0; vegetable_index < container_size; vegetable_index++) {
                Vegetable vegetable = container[vegetable_index];
                if (vegetable.get_weight() > 0.2)
                    filtered_content += vegetable.toString() + "\n";
            }
        }

        return filtered_content;
    }

    public String get_all_vegetables() {
        String vegetables_string = "";
        Vegetable[] container = this.repository.get_container();
        int container_size = this.repository.get_size();
        if (container_size > 0) {
            for (int vegetable_index = 0; vegetable_index < container_size; vegetable_index++) {
                vegetables_string += container[vegetable_index].toString() + "\n";
            }
        }

        return vegetables_string;
    }

    public void add_tomato(int id, double weight_in_kg, double price) throws RepositoryException{
        Tomato tomato = new Tomato(id, weight_in_kg, price);
        this.repository.add(tomato);
    }

    public void remove_tomato(int id) throws RepositoryException{
        this.repository.remove(id);
    }

    public void add_pepper(int id, double weight_in_kg, double price) throws RepositoryException{
        Pepper pepper = new Pepper(id, weight_in_kg, price);
        this.repository.add(pepper);
    }

    public void remove_pepper(int id) throws RepositoryException{
        this.repository.remove(id);
    }

    public void add_eggplant(int id, double weight_in_kg, double price) throws RepositoryException{
        Eggplant eggplant = new Eggplant(id, weight_in_kg, price);
        this.repository.add(eggplant);
    }

    public void remove_eggplant(int id) throws RepositoryException{
        this.repository.remove(id);
    }
}
