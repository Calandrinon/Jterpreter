import Controller.Controller;
import Exceptions.RepositoryException;
import Repository.VegetableRepository;
import View.View;

import java.io.IOException;

/**
 * 5. Tomatoes, peppers and eggplants are grown in a greenhouse.
 *    Display all the vegetables with a weight greater than 0.2 kg.
 */

public class Main {
    public static void main(String[] args) throws RepositoryException, IOException {
        VegetableRepository repository = new VegetableRepository(10);
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.run();
    }
}
