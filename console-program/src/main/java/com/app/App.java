package com.app;

/**
 * App - Aplikacja stworzona na cele rekrutacji do firmy PGS Software
 * Jest to prosta biblioteka filmowa z mozliwoscia:
 * - dodawania
 * - edycji
 * - usuwania
 * Posiada opcje wyszukiwania wybranych przez nas filmów
 *
 * @author Konrad Nizioł
 * @version Biblioteka Filmowa v0.9 beta
 */
public class App 
{
    public static void main( String[] args )
    {
        final String appName = "Biblioteka Filmowa v0.9";
        System.out.println(appName);

        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();


    }
}
