package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.EntityDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        HibernateFactory hibernateFactory = new HibernateFactory();

//        MovieDao movieDao = new MovieDao(hibernateFactory);
//        AuthorDao authorDao = new AuthorDao(hibernateFactory);
//
//        Movie movie = new Movie();
//        movie.setTitle("Psy");
//        movie.setProductionYear(1992);
//        movie.setTime(5400000);
//        movie.setType("comedy");
//        movieDao.add(movie);
//        Author author01 = new Author();
//        author01.setName("Wladyslaw");
//        author01.setSurname("Pasikowski");
//        author01.setNationality("polish");
//        author01.setAge(78);
//        authorDao.add(author01);
//
//        movieDao.getById(1);

        EntityDao<Movie> genericMovieDao = new EntityDao<>(hibernateFactory, Movie.class);
        EntityDao<Author> genericAuthorDao = new EntityDao<>(hibernateFactory, Author.class);


        Movie movie1 = new Movie();
        movie1.setTitle("Psy 02");
        movie1.setType("drama");
        movie1.setProductionYear(2004);
        movie1.setTime(5500000);
        genericMovieDao.save(movie1);

        Author author02 = new Author();
        author02.setName("Roman");
        author02.setSurname("Polański");
        author02.setNationality("polish");
        author02.setAge(123);
        genericAuthorDao.save(author02);

        System.err.println("Sample movie get " + genericMovieDao.getById(3));
        System.err.println("Sample author get "+ genericAuthorDao.getById(10));

        // update tests
        Author authorPreparedToUpdate = genericAuthorDao.getById(14);
        authorPreparedToUpdate.setNationality("czech");
        genericAuthorDao.update(authorPreparedToUpdate);
        System.err.println("Sample author update " + genericAuthorDao.getById(14));

        Movie moviePreparedToUpdate = genericMovieDao.getById(13);
        moviePreparedToUpdate.setTime(3600000);
        genericMovieDao.update(moviePreparedToUpdate);
        System.err.println("Sample movie update " + genericMovieDao.getById(13));

        //delete tests
        Author authorPreparedToDelete = genericAuthorDao.getById(16);
        genericAuthorDao.delete(authorPreparedToDelete);
        System.err.println("Sample author delete " + genericAuthorDao.getById(16));

        Movie moviePreparedToDelete = genericMovieDao.getById(16);
        genericMovieDao.delete(moviePreparedToDelete);
        System.err.println("Sample movie delete " + genericMovieDao.getById(16));

        hibernateFactory.getSessionFactory().close();
        Thread.sleep(5000);
    }
}
