package repository;

import java.util.List;

public interface Repository<T, ID>{
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}
