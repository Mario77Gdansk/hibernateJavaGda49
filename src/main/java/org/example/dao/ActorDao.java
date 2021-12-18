package org.example.dao;

import org.example.HibernateFactory;
import org.example.model.Actor;

public class ActorDao extends EntityDao<Actor>{
    public ActorDao(HibernateFactory hibernateFactory) {
        super(hibernateFactory, Actor.class);

    }
}
