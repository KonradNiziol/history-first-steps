package com.app.data;

import com.app.dao.MovieDao;
import com.app.dao.MovieDaoJDBC;
import com.app.model.Movie;

import java.util.Optional;
import java.util.Set;

/**
 * obiekt klasy <code>Library</code> zapisuję dane z bazy danych do kolekcji
 * posiata również metody do edycji, dodawania i usuwania elementów z bazy danych
 */
public class Library {

    /**
     * @param movieDao obiekt na podstawie klasy MovieDao posiada wszystkie niezbędne metody do pracy z bazą danych
     */
    private MovieDao movieDao;
    /**
     * @param listOfMovies kolekcja filmow
     */
    private Set<Movie> listOfMovies;

    public Set<Movie> getListOfMovies() {
        return listOfMovies;
    }


    public Library() {
        movieDao = new MovieDaoJDBC();
        listOfMovies = movieDao.findAll();
    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            return;
        }

        movieDao.add(movie);
        listOfMovies = movieDao.findAll();
    }

    public void deleteMovie(int id) {
        listOfMovies.removeIf(x -> x.getId() == id);
        movieDao.delete(id);
        listOfMovies = movieDao.findAll();

    }

    public Movie findOneById(int id) {
        Optional<Movie> movieOp = movieDao.findOneById(id);
        if (movieOp.isPresent()){
            return movieOp.get();
        } else {
            return null;
        }
    }

    public void update(Movie toUpdate) {
        movieDao.update(toUpdate);
        listOfMovies = movieDao.findAll();
    }

    @Override
    public String toString() {
        return "Lista filmow: " + listOfMovies;
    }
}
