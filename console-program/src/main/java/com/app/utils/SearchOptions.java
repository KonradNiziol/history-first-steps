package com.app.utils;

import com.app.model.Movie;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Klasa final posiata tylko pola statyczne
 * posiada specjalna metodę do wyszukiwania filmów
 */
public final class SearchOptions {
    private static DataReader dataReader  = new DataReader();

    /**
     * Metoda tworząca pętlę z menu wyboru wyszukiwania filmów
     * @param listOfMovies kolekcja wszystkich filmow
     */
    public static void searchOption (Set<Movie> listOfMovies) {
        Option option = null;
        while (option != Option.EXIT) {
            try {
                printOptions();
                option = Option.createFromInt(dataReader.getInt());
                switch (option) {
                    case TITLE:
                        System.out.println("Podaj nazwę tytułu:");
                        afterTheTitle(listOfMovies, dataReader.getString());
                        break;
                    case IS_FULL_TIME_MOVIE:
                        System.out.println("Czy film ma być pełnometrażowy? (true/false)");
                        afterFullTimeMovies(listOfMovies, dataReader.getBoolean());
                        break;
                    case YEAR:
                        System.out.println("Podaj wybrany rok:");
                        afterYear(listOfMovies, dataReader.getInt());
                        break;
                    case CATEGORY:
                        System.out.println("Podaj wybrana kategorie filmow:");
                        afterCategory(listOfMovies, dataReader.getString());
                        break;
                    case SCREENWITER:
                        System.out.println("Wybierz scemarzystę:");
                        afterScreenwiter(listOfMovies, dataReader.getString());
                        break;
                    case EXIT:

                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wprowadzono niepoprawne dane, sprobuj ponownie:");
            } catch (NumberFormatException | NoSuchElementException e) {
                System.err.println("Wybrana opcja nie istnieje, wybierz ponownie:");
            }
        }
    }

    private static void afterTheTitle(Set<Movie> listOfMovies, String title){
        listOfMovies.stream()
                .filter(x -> x.getTitle().equals(title))
                .forEach(System.out::println);
    }

    private static void afterFullTimeMovies(Set<Movie> listOfMovies, boolean isFullTime) {
        listOfMovies.stream()
                .filter(x -> x.getIsFullTimeMovie() == isFullTime)
                .forEach(System.out::println);
    }

    private static void afterYear(Set<Movie> listOfMovies, int year) {
        listOfMovies.stream()
                .filter(x -> x.getDate().getYear() == year)
                .forEach(System.out::println);
    }

    private static void afterCategory(Set<Movie> listOfMovies, String category) {
        listOfMovies.stream()
                .filter(x -> x.getCategory().equals(category))
                .forEach(System.out::println);
    }

    private static void afterScreenwiter(Set<Movie> listOfMovies, String screenwiter) {
        listOfMovies.stream()
                .filter(x -> x.getScreenwriter().equals(screenwiter))
                .forEach(System.out::println);
    }

    /**
     * Metoda wyświetla menu w konsoli
     */
    private static void printOptions() {
        System.out.println(" \n Szukaj filmy po: ");
        for (Option o : Option.values()) {
            System.out.println(o);
        }
    }

    /**
     * Typ wyliczeniowy Enum do wygenerowania menu z opcjami szukania
     */
    private enum Option {
        EXIT(0, "Powrot do glownego menu"),
        TITLE(1, "Tytule"),
        IS_FULL_TIME_MOVIE(2, "Pokaż tylko filmy pełnometrażowe - true, pokaż tylko seriame - false"),
        YEAR(3, "Filmy z wybranego roku  "),
        CATEGORY(4, "Z wybranej kategori"),
        SCREENWITER(5, "Filmy wybranego scenarzysty ");

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
