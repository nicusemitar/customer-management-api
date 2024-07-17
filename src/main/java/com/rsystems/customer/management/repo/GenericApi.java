package com.rsystems.customer.management.repo;

import java.util.List;
import java.util.Optional;

public interface GenericApi<T, I> {
    List<T> findAll();

    Optional<T> findById(final I id);

    void save(T entity);

    void deleteById(I id);

}
