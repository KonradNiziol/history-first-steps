package com.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasa napisana do połączenia z bazą danych SQLite
 * Na podstawie wzorca <strong>Singleton</strong>
 */
public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance;
    }

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE = "jdbc:sqlite:data.db";

    private Connection connection;
    public Connection getConnection() {
        return connection;
    }

    private DbConnection() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE);
            createTables();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda tworzy tablicę z danymi jeśli takowa jeszcze nie istnieje
     */
    public void createTables()
    {
        try {
            String sqlTableMovie =
                    "CREATE TABLE IF NOT EXISTS Movie ( " +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "title VARCHAR(50) NOT NULL, " +
                            "isFullTimeMovie INTEGER DEFAULT 0,  " +
                            "year INTEGER DEFAULT 2000,  " +
                            "category VARCHAR(50)," +
                            "screenwriter VARCHAR(50)," +
                            "director VARCHAR(50)," +
                            "seasons INTEGER DEFAULT 0," +
                            "isFinished INTEGER DEFAULT 1)";

            Statement statement = connection.createStatement();
            statement.execute(sqlTableMovie);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
