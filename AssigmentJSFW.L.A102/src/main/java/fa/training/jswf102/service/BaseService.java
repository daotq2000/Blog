package fa.training.jswf102.service;

import fa.training.jswf102.exception.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BaseService <T,K>{

    public List<T> findAll();
    public T findById(K id) throws NotFoundException;
    public T save(T t) throws NotFoundException;
    public T update(T t, K id) throws NotFoundException;
    public boolean delete(K id) throws NotFoundException;
}
