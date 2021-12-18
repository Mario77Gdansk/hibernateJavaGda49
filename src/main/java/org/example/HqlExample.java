package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;

import java.util.List;

public class HqlExample {
    public static void main(String[] args) throws InterruptedException {
        HibernateFactory hibernateFactory = new HibernateFactory();

        MovieDao movieDao = new MovieDao(hibernateFactory);
        AuthorDao authorDao = new AuthorDao(hibernateFactory);

        List<Movie> movies = movieDao.getAll();
        movies.forEach(System.out::println);

        List<Author> authors = authorDao.getAll();
        authors.forEach(System.out::println);

        List<Movie> moviesWithsPsy = movieDao.getByTitle("Psy");
        moviesWithsPsy.forEach(System.out::println);

        List<Author> authorsWithAge = authorDao.getByAge(123);
        authorsWithAge.forEach(System.out::println);

        hibernateFactory.getSessionFactory().close();
        Thread.sleep(2000);
    }
}
