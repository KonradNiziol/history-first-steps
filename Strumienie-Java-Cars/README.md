# Strumienie-Java-Cars

## 1. Wstęp
Projekt mini salonu samochodowego składającego się z listy samochodów do kupienia. Program stworzony w ramach nauki Javy oraz GitHuba

Zagadnienia, które zostały użyte do wykonania zadania:
* Klasa i obiekt
* Kompozycja
* Wyrażenia lambda
* Kolekcje
* Mapy
* Strumienie Java 8.

## 2. Objasnienie klas

  2.1 **Klasa CarShowroom**  
Posiada pole prywatne listOfCar, które jest kolekcją (obiektów klacy Car) bez duplikatów posortowaną alfabetycznie
według marki samochodu. Przykładowe metody:
* Osobne metody zwracają kolekcję samochodów posortowaną według ceny, marki, koloru, rodzaju nadwozia,
* Metoda zwraca kolekcję o określonym rodzaju nadwozia podanym jako argument oraz o cenie z przedziału <a,b>,
gdzie a oraz b to kolejne argumenty metody,
* Metoda zwraca kolekcję nazw samochodów, które tankowane są 
rodzajem paliwa podanym jako argument,
* Metoda wypisuje samochody o konfiguracji podanej przez użytkownika. Użytkownik pytany jest o zakres cen przechowywany 
w zmiennych cenaMax oraz cenaMin, rodzaj paliwa, zakres pojemności silnika przechowywany w zmiennych pojemnoscMin, 
pojemnośćMax oraz wymagane elementy wyposażenia,  Metoda jako argument przyjmuje nazwę parametru (dopuszczalne to pojemność,
cena, rozmiar kola) i prezentuje statystykę dla tego parametru, czyli średnią arytmetyczną, wartość, minimalną oraz wartość maksymalną, 
* Prywatna metoda zwraca mapę, w której kluczem jest Samochód, natomiast wartością jest liczba kilometrów, po której samochód
musi przejść kolejny przegląd, 
* Metoda zwraca mapę, którą generuje opisana w poprzednim punkcie metoda prywatna, dodatkowo sortując jej elementy malejąco 
według liczby kilometrów przeglądu,
* Metoda zwraca mapę, w której kluczem jest rodzaj opony (ZIMOWA lub LETNIA), a wartością lista samochodów z odpowiednim typem opon.

2.2 **Klasa Car**  
Składa się z marka (String), cena podstawowa (double), obiekt klasy Silnik, obiekt klasy Nadwozie oraz obiekt 
klasy Kolo. Posiada metodę wczytującą dane z pliku tekstowego.

2.3 **Klasa CarEngine**  
2.4 **Klasa Body**  
2.5 **Klasa CarWheel**  

