package com.app.dao;

import com.app.model.Movie;

import java.util.Optional;
import java.util.Set;

/**
 * Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych
 */
public interface MovieDao {
    void add(Movie movie);
    void update(Movie movie);
    void delete(Integer id);
    Optional<Movie> findOneById(Integer id);
    Set<Movie> findAll();
}
