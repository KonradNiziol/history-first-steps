package com.app.model;

import java.time.LocalDate;

/**
 * Na podstawie klacy <code>Movie</code> możemy tworzyć obiekty reprezętujące pozycje filmowe
 */
public class Movie {
    private Integer id;
    private String title;
    private Boolean isFullTimeMovie;
    private LocalDate date;
    private String category;
    private String screenwriter;
    private String director;
    private Integer seasons;
    private Boolean isFinished;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsFullTimeMovie() {
        return isFullTimeMovie;
    }

    public void setIsFullTimeMovie(Integer isFullTimeMovie) {
        if (isFullTimeMovie == 1){
            this.isFullTimeMovie = true;
        } else {
            this.isFullTimeMovie = false;
        }

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        if (isFinished == 1) {
            this.isFinished = true;
        } else {
            this.isFinished = false;
        }
    }

    public Movie() {
    }

    /**
     * Konstruktor klasy nie przyjmujący typu boolean
     * @param id Pozycja w bazie danych
     * @param title Tytuł filmu
     * @param isFullTimeMovie czy jest pełnometrażowy
     * @param year rok produkcji
     * @param category kategoria
     * @param screenwriter scenarzysta
     * @param director producent
     * @param seasons ile sezonow
     * @param isFinished czy produkcja zakonczona
     */
    public Movie(Integer id, String title, Integer isFullTimeMovie, Integer year, String category, String screenwriter, String director, Integer seasons, Integer isFinished) {
        setId(id);
        setTitle(title);
        setIsFullTimeMovie(isFullTimeMovie);
        setDate(LocalDate.of(year,1,1));
        setCategory(category);
        setScreenwriter(screenwriter);
        setDirector(director);
        setSeasons(seasons);
        setIsFinished(isFinished);
    }

    /**
     * Konstruktor klasy z typen boolean
     * @param id Pozycja w bazie danych
     * @param title Tytuł filmu
     * @param isFullTimeMovie czy jest pełnometrażowy
     * @param year rok produkcji
     * @param category kategoria
     * @param screenwriter scenarzysta
     * @param director producent
     * @param seasons ile sezonow
     * @param isFinished czy produkcja zakonczona
     */
    public Movie(Integer id, String title, boolean isFullTimeMovie, Integer year, String category, String screenwriter, String director, Integer seasons, boolean isFinished) {
        setId(id);
        setTitle(title);
        setDate(LocalDate.of(year,1,1));
        setCategory(category);
        setScreenwriter(screenwriter);
        setDirector(director);
        setSeasons(seasons);
        this.isFullTimeMovie = isFullTimeMovie;
        this.isFinished = isFinished;
    }

    @Override
    public String toString() {
        return " \n Movie id=" + id +
                ", Tytuł: '" + title + '\'' +
                ", Data_produkcji: " + date +
                ", Kategoria: '" + category + '\'' +
                ", Scenarzysta: '" + screenwriter + '\'' +
                ", Producent: '" + director + '\'' +
                ", Pełnometrażowy " + isFullTimeMovie +
                ", Liczba_sezonów: " + seasons +
                ", Czy_zakończony: " + isFinished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (isFullTimeMovie != null ? !isFullTimeMovie.equals(movie.isFullTimeMovie) : movie.isFullTimeMovie != null)
            return false;
        if (date != null ? !date.equals(movie.date) : movie.date != null) return false;
        if (category != null ? !category.equals(movie.category) : movie.category != null) return false;
        if (screenwriter != null ? !screenwriter.equals(movie.screenwriter) : movie.screenwriter != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        if (seasons != null ? !seasons.equals(movie.seasons) : movie.seasons != null) return false;
        return isFinished != null ? isFinished.equals(movie.isFinished) : movie.isFinished == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isFullTimeMovie != null ? isFullTimeMovie.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (screenwriter != null ? screenwriter.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (seasons != null ? seasons.hashCode() : 0);
        result = 31 * result + (isFinished != null ? isFinished.hashCode() : 0);
        return result;
    }
}
