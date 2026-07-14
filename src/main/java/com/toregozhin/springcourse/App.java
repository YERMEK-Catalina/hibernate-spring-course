package com.toregozhin.springcourse;


import com.toregozhin.springcourse.model.Passport;
import com.toregozhin.springcourse.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            session.remove(person);

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

