package agh.ics.oop;

import java.util.Arrays;

import static java.lang.System.out;

// Klasa zawiera notatki porównujące Jave do C#
public class World
{
    public static void main(String[] args)
    {
        Animal animal = new Animal();

        out.println(animal);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        out.println(animal);

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        out.println(animal);
        MoveDirection[] moves = new OptionsParser().parse(args);

        animal.move(moves);

        out.println(animal);

        // Pytanie nr 10
        // A - stworzenie listy zwierząt i przekazywanie jej za każdym razem w funkcji move sprawdzajac mozliwosc wykonania ruchu
        // B - stworzenie listy zwierzat i przekazanie jej referencji w konstruktorze zwierzecia (w move sprawdzamy mozliwosc ruchu)
        // C - stworzenie tablicy bool przedstawiajacej swiat, gdzie wartosc bool odpowiada czy pole jest zajete czy nie, przekazywanie referencji do tablicy w konstruktorze nowego zwierzecia
        // w move validaca wykoniania ruchu jezeli ok to uwolnienie poprzedniego pola
        // D - to samo co w C ale przekazywanie mapy swiata w funkcji move

        // rozwiązanie A i B dość długie do sprawdzenia z kżda zmiana trzeba sprawdzac wszystkie zwierzeta

        // rozwiazanie C i D znacznie szybsze jednak bedzie potrzeba wiecej miejsca na dane

        out.println("****system zakończył działanie****\n");
    }

}
