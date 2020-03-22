package com.app.utils;

import com.app.model.Movie;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Obiekt klasy <code>DataReader</code> przyjmuję dane od uzytkownika (mozliwosc wprowadzania danych tylko przez konsolę)
 */
public class DataReader {
    private Scanner sc;

    public DataReader() {
        sc = new Scanner(System.in);
    }

    public void close() {
        sc.close();
    }

    public String getString() {
        return sc.nextLine();
    }

    /**
     * Metoda zamienia wartosc zmiennej boolean (true na 1) i (false na 0)
     * @param toChange wartość typu boolean
     * @return int (1 or 0)
     */
    public static int changeBooleanToInt(Boolean toChange) {
        if (toChange == true) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Metoda pobiera wartosc boolean od użytkownika
     * @return 'true' or 'false'
     */
    public boolean getBoolean() throws InputMismatchException {
        boolean val = false;
        try {
            val = sc.nextBoolean();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Możesz napisać tylko 'true' lub 'false'");
        } finally {
            sc.nextLine();
        }
        return val;
    }

    /**
     * Metoda pobiera liczbę od użytkownika
     * @return Integer
     */
    public int getInt() throws NumberFormatException {
        int number = 0;
        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            throw new NumberFormatException("Liczba wprowadzona w niepoprawnej formie ");
        } finally {
            sc.nextLine();
        }

        return number;
    }

    /**
     * Metoda pobiera od użytkownika wszystkie parametry odnośnie nowej pozycji wprowadzanej do bazy
     * @return obiekt <code>Movie</code>
     */
    public Movie readAndCreateMovie() throws InputMismatchException {
        System.out.println("Tytuł: ");
        String title = sc.nextLine();
        System.out.println("Reżyser: ");
        String director = sc.nextLine();
        System.out.println("Scenariusz: ");
        String screenwriter = sc.nextLine();
        System.out.println("Gatunek: ");
        String category = sc.nextLine();
        System.out.println("Rok wydania: ");
        int year = 0;
        int seasons = 0;
        boolean isFullTimeMovie = true;
        boolean isFinished = true;
        try {
            year = sc.nextInt();
            sc.nextLine();

            System.out.println("Czy Film jest pełnometrażowy (true/false): ");
            isFullTimeMovie = sc.nextBoolean();
            sc.nextLine();

            if (isFullTimeMovie != true) {
                System.out.println("Liczba sezonów: ");
                seasons = sc.nextInt();
                sc.nextLine();
                System.out.println("Czy serial jest już zakończony (true/false): ");
                isFinished = sc.nextBoolean();
                sc.nextLine();
            }

        } catch (InputMismatchException e) {
            sc.nextLine();
            throw e;
        }

        return new Movie(null, title, isFullTimeMovie, year, category, screenwriter, director, seasons, isFinished);
    }

    /**
     * Metoda umożliwia edycję wybranych pol edytowanego obiektu
     * @param movieToEdit obiekt class <code>Movie</code>
     * @return Obiekt Movie po edycji
     */
    public Movie editMovie (Movie movieToEdit) {
        Option option = null;
        boolean toChange;
        while (option != Option.EXIT) {
            try {
                printOptions();
                option = Option.createFromInt(getInt());
                switch (option) {
                    case TITLE:
                        System.out.println("Podaj nowy tytul");
                        movieToEdit.setTitle(sc.nextLine());
                        break;
                    case IS_FULL_TIME_MOVIE:
                        System.out.println("Czy jest to film pełnometrażowy? (true/false)");
                        toChange = sc.nextBoolean();
                        movieToEdit.setIsFullTimeMovie(changeBooleanToInt(toChange));
                        sc.nextLine();
                        break;
                    case YEAR:
                        System.out.println("Podaj nowy rok produkcji");
                        movieToEdit.setDate(LocalDate.of(sc.nextInt(),1,1));
                        sc.nextLine();
                        break;
                    case CATEGORY:
                        System.out.println("Podaj nową kategorie filmu");
                        movieToEdit.setCategory(sc.nextLine());
                        break;
                    case SCREENWITER:
                        System.out.println("Kto napisał scenariusz?");
                        movieToEdit.setScreenwriter(sc.nextLine());
                        break;
                    case DIRECTOR:
                        System.out.println("Kto odpowiadał za produkcje");
                        movieToEdit.setDirector(sc.nextLine());
                        break;
                    case SEASONS:
                        System.out.println("Ile jest sezonow");
                        movieToEdit.setSeasons(sc.nextInt());
                        sc.nextLine();
                        break;
                    case IS_FINISHED:
                        System.out.println("Czy serial jest zakończony? (true/false)");
                        toChange = sc.nextBoolean();
                        movieToEdit.setIsFinished(changeBooleanToInt(toChange));
                        sc.nextLine();
                        break;
                    case EXIT:
                        System.out.println("EDYCJA ZAKONCZONA!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wprowadzono niepoprawne dane, pozycja nie została zmieniona!");
                sc.nextLine();
            } catch (NumberFormatException | NoSuchElementException e) {
                System.err.println("Wybrana opcja nie istnieje, wybierz ponownie:");
            }
        }

        return movieToEdit;
    }

    /**
     * Metoda wypisuje opcje w konsoli
     */
    private void printOptions() {
        System.out.println("Wybierz polę ktore chcesz edytować: ");
        for (Option o : Option.values()) {
            System.out.println(o);
        }
    }


    /**
     * Typ wyliczeniowy Enum do wygenerowania menu do edycji obiektów
     */
    private enum Option {
        EXIT(0, "Zakończ i zapisz zmiany"),
        TITLE(1, "Tytuł filmu"),
        IS_FULL_TIME_MOVIE(2, "Informacje czy film jest pełnometrażowy (true/false)"),
        YEAR(3, "Rok produkcji "),
        CATEGORY(4, "Kategorię filmu "),
        SCREENWITER(5, "Scenarzysta "),
        DIRECTOR(6, "Producent"),
        SEASONS(7, "Liczba sezonow "),
        IS_FINISHED(8, "Czy produkcja serialu została zakończona" );

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
