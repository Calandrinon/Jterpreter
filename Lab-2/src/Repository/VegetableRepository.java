package Repository;

import Exceptions.RepositoryException;
import Model.Vegetable;

public class VegetableRepository implements RepositoryInterface {
    private final Vegetable[] container;
    private int number_of_vegetables;

    VegetableRepository(int size) throws RepositoryException {
        if (size <= 0)
            throw new RepositoryException("Repository size should be greater than 0.");
        this.container = new Vegetable[size];
        this.number_of_vegetables = 0;
    }

    public void add(Vegetable vegetable) throws RepositoryException {
        if (this.number_of_vegetables == this.container.length)
            throw new RepositoryException("There is no more space in the container.");

        for (Vegetable obj: this.container) {
            if (obj.get_id() == vegetable.get_id()) {
                throw new RepositoryException("There is already a vegetable with that id!");
            }
        }

        this.container[this.number_of_vegetables] = vegetable;
        this.number_of_vegetables++;
    }

    public void remove(int id) throws RepositoryException {
        for (int vegetable_index = 0; vegetable_index < this.number_of_vegetables; vegetable_index++) {
            if (container[vegetable_index].get_id() == id) {
                while (vegetable_index < this.number_of_vegetables - 1) {
                    container[vegetable_index] = container[vegetable_index + 1];
                }

                this.number_of_vegetables--;
                return;
            }
        }

        throw new RepositoryException("The vegetable with the given id does not exist.");
    }

    public Vegetable[] get_container() {
        return this.container;
    }

    public Vegetable get_by_id(int id) throws RepositoryException {
        for (Vegetable vegetable: this.container) {
            if (vegetable.get_id() == id) {
                return vegetable;
            }
        }

        throw new RepositoryException("The vegetable with the given id does not exist.");
    }
}
