package org.hina.buoi5.Movies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.hina.buoi5.RedBlackBST;

public class Movies {
    private final PeopleST peopleST;
    private final MoviesST moviesST;

    public Movies() {
        peopleST = new PeopleST();
        moviesST = new MoviesST();
        try (Scanner scanner = new Scanner(new File("src/main/java/org/hina/buoi5/Movies/movies.txt"))) {
            while (scanner.hasNext()) {
                String[] strings = scanner.nextLine().split("/");
                moviesST.add(strings);
                peopleST.add(strings);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<?> getMoviesOfActor(String actorName) {
        return peopleST.getMovies(actorName);
    }

    public List<?> getActorOfMovie(String movieName) {
        return moviesST.getActors(movieName);
    }

    public static void main(String[] args) {
        Movies movies = new Movies();
        List<?> actor = movies.getActorOfMovie("'Breaker' Morant (1980)");
//        actor.forEach(System.out::println);

        List<?> movie = movies.getMoviesOfActor("Brown, Bryan (I)");
        movie.forEach(System.out::println);
    }
}
