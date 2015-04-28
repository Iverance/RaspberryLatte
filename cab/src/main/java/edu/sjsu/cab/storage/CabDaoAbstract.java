package edu.sjsu.cab.storage;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "transactionManager")
public class CabDaoAbstract {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;
    
    public void delete(Object obj) {
        getCurrentSession().delete(obj);
    }

    public <T> void deleteAll(Collection<T> items) {
        for (T item : items) {
            delete(item);
        }
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public void refresh(Object obj) {
        getCurrentSession().refresh(obj);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
