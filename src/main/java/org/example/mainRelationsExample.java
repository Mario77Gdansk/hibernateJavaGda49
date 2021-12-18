package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.EntityDao;
import org.example.dao.MovieDao;
import org.example.dao.ReviewerDao;
import org.example.model.Author;
import org.example.model.Movie;
import org.example.model.Reviewer;

public class mainRelationsExample {
    public static void main(String[] args) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        ReviewerDao reviewerDao = new ReviewerDao(hibernateFactory, Reviewer.class);
        EntityDao<Movie> movieDao = new EntityDao<>(hibernateFactory, Movie.class);
        EntityDao<Author> authorDao = new EntityDao<>(hibernateFactory, Author.class);

        savingOneToOne(reviewerDao, movieDao);
        savingOneToMany(authorDao, movieDao);


        hibernateFactory.getSessionFactory().close();
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
