import Controller.Controller;
import Exceptions.GeneralException;
import Repository.Repository;
import View.View;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws GeneralException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the log file: ");
        String logFilePath = scanner.nextLine();

        Repository repository = new Repository(logFilePath);
        Controller controller = new Controller(repository);
        View view = new View(controller);
        view.run();
    }
}
