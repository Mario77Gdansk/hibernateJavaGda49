package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.HibernateFactory;
import org.example.model.Author;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@AllArgsConstructor public class AuthorDao {
    private HibernateFactory hibernateFactory;

    //create
    //read
    //update
    //delete

    public void add(Author author){
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(author);

        transaction.commit();
        session.close();
    }

    public Author getById(Integer id){
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Author author = session.find(Author.class, id);
        session.close();
        return author;
    }

    public List<Author> getAll() {
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Author> fromAuthor = session.createQuery("FROM Author", Author.class).list();
        return fromAuthor;
    }

    public List<Author> getByAge(int i) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Query<Author> query = session.createQuery("FROM Author a WHERE a.age = :ageToFind", Author.class);
        query.setParameter("ageToFind", i);
        List<Author>listOfResult = query.list();
        session.close();
        return listOfResult;
    }

}
