package com.app;

import com.app.data.Library;
import com.app.model.Movie;
import com.app.utils.DataReader;
import com.app.utils.SearchOptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Obiekt klasy wyswietla menu aplikacji oraz posiada odpowiednie pola do jej obsługi
 */
public class LibraryControl {

    /**
     * <code>dataReader</code> obiekt odpowiada za pobieranie danych od uzytkownika
     */
    private DataReader dataReader;
    /**
     * <code>library</code> klasa posiada kolekcję filmow oraz metody jej obsługi
     */
    private Library library;

    public LibraryControl() {
        dataReader = new DataReader();
        library = new Library();
    }

    /**
     * Główna pętla programu
     */
    public void controlLoop() {
        Option option = null;
        while (option != Option.EXIT) {
            try {
                printOptions();
                option = Option.createFromInt(dataReader.getInt());
                switch (option) {
                    case PRINT_LIST:
                        System.out.println(library);
                        break;
                    case ADD_MOVIE:
                        addMovie();
                        break;
                    case REMOVE_MOVIE:
                        System.out.println("Podaj ID Filmu który chcesz usunąć: ");
                        removeMovies(dataReader.getInt());
                        break;
                    case EDIT_MOVIE:
                        System.out.println("Podaj ID Filmu który chcesz edytować: ");
                        editMovie(dataReader.getInt());
                        break;
                    case SEARCH_OPTIONS:
                        SearchOptions.searchOption(library.getListOfMovies());
                        break;
                    case EXIT:
                        System.out.println("Do zobaczenia ponownie!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wprowadzono niepoprawne dane, filmu nie dodano");
            } catch (NumberFormatException | NoSuchElementException e) {
                System.err.println("Wybrana opcja nie istnieje, spróbuj ponownie:");
            }
        }
        // zamykamy strumień wejścia
        dataReader.close();
    }

    /**
     * Metoda wypisuje opcje w konsoli
     */
    private void printOptions() {
        System.out.println(" \n Wybierz opcję: ");
        for (Option o : Option.values()) {
            System.out.println(o);
        }
    }

    /**
     * Metoda pobiera od użytkownika i dodaje bezpośrednio do bazy danych
     */
    private void addMovie() {
        Movie movie = dataReader.readAndCreateMovie();
        library.addMovie(movie);
    }

    /**
     * Metoda usuwa film z bazy danych
     * @param id klucz ID - filmu ktory ma byś usunięty
     */
    private void removeMovies(int id) {
        library.deleteMovie(id);
    }

    /**
     * Metoda pozwala edytować wybrany film
     * @param id  klucz ID - filmu do edycji
     */
    private void editMovie(int id) {
        Movie toEdit = library.findOneById(id);
        if (toEdit == null){
            System.out.println("Pozycja o wybranym id nie istnieje spróbuj ponownie");
            return;
        }

        Movie afterEdit = dataReader.editMovie(toEdit);
        library.update(afterEdit);
    }


    /**
     * Typ wyliczeniowy Enum do wygenerowania głównego menu programu
     */
    private enum Option {
        EXIT(0, "Wyjście z programu"),
        PRINT_LIST(1, "Wyświetlenie calej listy"),
        ADD_MOVIE(2, "Dodanie filmu"),
        REMOVE_MOVIE(3, "Usuń pozycję o wybranym id: "),
        EDIT_MOVIE(4, "Edytuj pozycję o wybranym id: "),
        SEARCH_OPTIONS(5, "Wyszyukiwarka filmow");

        private int value;
        private String description;

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        /**
         * Metoda na podstawie wybranej liczby wybiera odpowiednia opcje z menu
         * @param option wybrana opcja w postaci liczby
         * @return Jedno z pól klasy wyliczeniowej
         * @throws NoSuchElementException w przypadku gdzy wybrana liczba nie jest przypisana do żadnego pola
         */
        public static Option createFromInt(int option) throws NoSuchElementException {
            Option result;
            try {
                result = Option.values()[option];
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException("Brak elementu o wskazanym ID");
            }
            return result;
        }
    }
}
