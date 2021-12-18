package org.example;

import org.example.dao.*;
import org.example.model.*;

import java.util.List;

public class mainRelationsExample {
    public static void main(String[] args) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        ReviewerDao reviewerDao = new ReviewerDao(hibernateFactory, Reviewer.class);
        EntityDao<Movie> movieDao = new EntityDao<>(hibernateFactory, Movie.class);
        EntityDao<Author> authorDao = new EntityDao<>(hibernateFactory, Author.class);
        EntityDao<Actor> actorDaoFromEntity = new EntityDao<>(hibernateFactory, Actor.class);
        ActorDao actorDao = new ActorDao(hibernateFactory);

 //       savingOneToOne(reviewerDao, movieDao);
 //       savingOneToMany(authorDao, movieDao);
        savingManyToMany(actorDao, movieDao);


        hibernateFactory.getSessionFactory().close();
    }

    private static void savingManyToMany(ActorDao actorDao, EntityDao<Movie> movieDao) {
        Actor actor01 = new Actor();
        actor01.setName("Michał");
        actor01.setSurname("Michalski");
        actor01.setRating(Rating.HIGH);
        Actor actor02 = new Actor();
        actor02.setName("Bartosz");
        actor02.setSurname("Bartczak");
        actor02.setRating(Rating.MEDIUM);
        Movie movie01 = new Movie();
        movie01.setTitle("dupa węża 01");
        movie01.setType("horror");
        Movie movie02 = new Movie();
        movie02.setTitle("dupa węża 02");
        movie02.setType("horror");

        movie02.setActors(List.of(actor01,actor02));
        actorDao.save(actor01);
        actorDao.save(actor02);
        movieDao.save(movie01);
        movieDao.save(movie02);
    }

    private static void savingOneToOne(ReviewerDao reviewerDao, EntityDao<Movie> movieDao) {
        Movie sampleMovie = new Movie();
        sampleMovie.setTitle("Sample movie");
        sampleMovie.setProductionYear(1977);
        sampleMovie.setType("DRAMA");

        Reviewer reviewer = new Reviewer();
        reviewer.setName("Adam");
        reviewer.setSurname("Adamski");
        reviewer.setMovie(sampleMovie);

        movieDao.save(sampleMovie);
        reviewerDao.save(reviewer);
        Reviewer byId = reviewerDao.getById(reviewer.getId());
        System.out.println(byId);
    }

    private static void savingOneToMany(EntityDao<Author> authorDao, EntityDao<Movie> movieDao) {

        Author sampleAuthor01 = new Author();
        sampleAuthor01.setName("Tomasz");
        sampleAuthor01.setSurname("Tomala");
        sampleAuthor01.setNationality("Polish");
        sampleAuthor01.setAge(55);

        Movie sampleMovie01 = new Movie();
        sampleMovie01.setTitle("Sample movie01");
        sampleMovie01.setProductionYear(1977);
        sampleMovie01.setType("DRAMA");

        Movie sampleMovie02 = new Movie();
        sampleMovie02.setTitle("Sample movie02");
        sampleMovie02.setProductionYear(1977);
        sampleMovie02.setType("DRAMA");


        authorDao.save(sampleAuthor01);
        movieDao.save(sampleMovie01);
        movieDao.save(sampleMovie02);
    }
}
