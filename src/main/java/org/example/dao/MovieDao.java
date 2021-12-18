package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.HibernateFactory;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor
public class MovieDao {
    private HibernateFactory hibernateFactory;

    //create
    //read
    //update
    //delete
    public void add(Movie movie) {
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(movie);

        transaction.commit();
        session.close();
    }
    public Movie getById(Integer id){
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Movie movie = session.find(Movie.class, id);
        session.close();
        return movie;
    }

    public List<Movie> getAll() {
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Movie> fromMovie = session.createQuery("FROM Movie", Movie.class).list();
        return fromMovie;
    }
    public List<Movie> getByTitle(String titleFromMethod){
        Session session = hibernateFactory.getSessionFactory().openSession();
        Query<Movie> query = session.createQuery("FROM Movie m WHERE m.title = :titleToFind", Movie.class);
        query.setParameter("titleToFind", titleFromMethod);
        List<Movie> listOfResult = query.list();
        session.close();

        return listOfResult;

    }

}
