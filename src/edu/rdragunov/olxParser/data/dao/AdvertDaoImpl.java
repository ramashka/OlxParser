package edu.rdragunov.olxParser.data.dao;

import edu.rdragunov.olxParser.data.entities.Advert;
import edu.rdragunov.olxParser.data.interfaces.AdvertDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;

@Repository(value = "advertDao")
final public class AdvertDaoImpl implements AdvertDao{

    private AdvertDaoImpl(){}

    private static SessionFactory sessionFactory= (SessionFactory) new ClassPathXmlApplicationContext("spring.xml").getBean("sessionFactory");
    //@Autowired
    //private static SessionFactory sessionFactory;

    @Override
    public Advert getAdvert(Integer id) {
        return session().get(Advert.class, id);
    }

    @Override
    public Advert saveAdvert(Advert advert) {
        System.out.println(session().save(advert));
        return advert;
    }

    @Override
    public Set<Advert> getAllAdverts() {
        Set<Advert> advertSet=new LinkedHashSet<>();
        Query query=session().createQuery("from Advert");
        advertSet.addAll(query.list());
        return advertSet;
    }

    @Override
    public Advert removeAdvert(Advert advert) {
        session().delete(advert);
        return advert;
    }

    private Session session(){
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void dropAdverts() {
        session().createQuery("delete from Advert").executeUpdate();
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        AdvertDaoImpl.sessionFactory = sessionFactory;
    }
}
