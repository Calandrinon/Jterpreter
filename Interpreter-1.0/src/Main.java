import Controller.Controller;
import Exceptions.GeneralException;
import Repository.Repository;
import View.View;

public class Main {
    public static void main(String[] args) throws GeneralException {
        Repository repository = new Repository();
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.run();
    }
}
