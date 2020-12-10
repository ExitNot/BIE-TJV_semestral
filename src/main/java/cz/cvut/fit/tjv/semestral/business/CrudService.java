package cz.cvut.fit.tjv.semestral.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudService<T, k> {
    T create(T data);

    Optional<T> readById(k id);

    Page<T> readAll(Pageable pageable);

    void update(T newData);

    void delete(k id);

}
