package com.quyvx.main_server.domain.repositories;

import java.util.Optional;

public interface IRepository<T> {
    T save(T entity);

    void delete(Long id);

    Optional<T> findById(Long id);
}
