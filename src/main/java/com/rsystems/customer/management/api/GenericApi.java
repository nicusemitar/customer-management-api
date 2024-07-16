package com.rsystems.customer.management.api;

import java.util.List;
import java.util.Optional;

public interface GenericApi<T, ID> {
    List<T> findAll();

    Optional<T> findById(final ID id);

    void save(T entity);

    void update(ID id, T entity);

    void deleteById(ID id);
}
