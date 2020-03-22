package com.app.dao;

import com.app.db.DbConnection;
import com.app.model.Movie;
import com.app.utils.DataReader;

import java.sql.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * Klasa implementująca interface <code>MovieDao</code> Posiada metody:
 * add - dodawanie do bazy
 * update - edycja elementów bazy
 * delete - usuwanie elementów
 * findOneById - szukanie po ID
 * findAll - wszystkie pozycje do kolekcji
 */
public class MovieDaoJDBC implements MovieDao {
    private static Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Movie movie) {
        if (connection == null || movie == null)
        {
            return;
        }


        try {
            String sqlInsertMovie = "INSERT INTO Movie (title, isFullTimeMovie, year, category, screenwriter, director, seasons, isFinished) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement prep = connection.prepareStatement(sqlInsertMovie);
            prep.setString(1, movie.getTitle());
            prep.setInt(2, DataReader.changeBooleanToInt(movie.getIsFullTimeMovie()));
            prep.setInt(3, movie.getDate().getYear());
            prep.setString(4, movie.getCategory());
            prep.setString(5, movie.getScreenwriter());
            prep.setString(6, movie.getDirector());
            prep.setInt(7, movie.getSeasons());
            prep.setInt(8, DataReader.changeBooleanToInt(movie.getIsFinished()));
            prep.execute();
            System.out.println("Pozycja została dodana");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Movie movie) {
        if (connection == null || movie == null)
        {
            return;
        }


        try {
            String sqlUpdate = "UPDATE Movie SET title = ?, isFullTimeMovie= ?, year= ?, category= ?, screenwriter= ?, director= ?, seasons= ?, isFinished= ? WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(sqlUpdate);

            prep.setString(1, movie.getTitle());
            prep.setInt(2, DataReader.changeBooleanToInt(movie.getIsFullTimeMovie()));
            prep.setInt(3, movie.getDate().getYear());
            prep.setString(4, movie.getCategory());
            prep.setString(5, movie.getScreenwriter());
            prep.setString(6, movie.getDirector());
            prep.setInt(7, movie.getSeasons());
            prep.setInt(8, DataReader.changeBooleanToInt(movie.getIsFinished()));
            prep.setInt(9, movie.getId());

            //update
            prep.executeUpdate();
            System.out.println("Pozycja została zaktualizowana");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        if (connection == null || id == null)
        {
            return;
        }


        try {
            String sqlDelete = " DELETE FROM Movie WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(sqlDelete);
            prep.setInt(1, id);
            prep.execute();
            System.out.println("Pozycja zostałą usunieta");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> findOneById(Integer id) {
        Optional<Movie> movieOptional = Optional.empty();
        if (connection == null || id == null)
        {
            return movieOptional;
        }

        try {
            String sqlSelectOne = " SELECT id, title, isFullTimeMovie, year, category, screenwriter, director, seasons, isFinished FROM Movie WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(sqlSelectOne);
            prep.setInt(1, id);

            ResultSet resultSet = prep.executeQuery();
            if (resultSet.next())
            {
                 movieOptional = Optional.of(
                         new Movie(
                                 resultSet.getInt(1),
                                 resultSet.getString(2),
                                 resultSet.getInt(3),
                                 resultSet.getInt(4),
                                 resultSet.getString(5),
                                 resultSet.getString(6),
                                 resultSet.getString(7),
                                 resultSet.getInt(8),
                                 resultSet.getInt(9)
                         )
                 );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieOptional;

    }

    @Override
    public Set<Movie> findAll() {
        Set<Movie> movies = null;
        if (connection == null) {
            return movies;
        }


        try {
            String sqlSelectAll = " SELECT id, title, isFullTimeMovie, year, category, screenwriter, director, seasons, isFinished FROM Movie";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            movies = new TreeSet<>(Comparator
                    .comparing(Movie::getId));

            while (resultSet.next())
            {
                movies.add(
                        new Movie(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getInt(8),
                                resultSet.getInt(9)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;

    }
}
