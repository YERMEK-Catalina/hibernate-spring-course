package com.toregozhin.springcourse;


import com.toregozhin.springcourse.model.Item;
import com.toregozhin.springcourse.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 10);
            session.delete(person);

//            person.addItem(new Item("Book. The Prince."));
//            person.addItem(new Item("Book. The Art of Worldly Wisdom"));
//            person.addItem(new Item("Book. The Essays"));

//            session.save(person);

            session.getTransaction().commit();

        } catch (Exception e) {

            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            sessionFactory.close();
        }

        System.out.println("GREETINGS YERMEK! GOOD JOB!");
    }
}

