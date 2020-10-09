package Repository;

import Exceptions.RepositoryException;
import Model.Vegetable;

public interface RepositoryInterface {
    void add(Vegetable vegetable) throws RepositoryException;
    void remove(int id) throws RepositoryException;
    Vegetable[] get_container();
    int get_size();
    Vegetable get_by_id(int id) throws RepositoryException;
}
