package org.example.dao;

import org.example.HibernateFactory;
import org.example.model.Reviewer;

public class ReviewerDao extends EntityDao<Reviewer>{
    public ReviewerDao(HibernateFactory hibernateFactory, Class<Reviewer> clazz) {
        super(hibernateFactory, clazz);
    }
}
