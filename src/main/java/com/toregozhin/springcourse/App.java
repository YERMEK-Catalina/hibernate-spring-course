package com.toregozhin.springcourse;


import com.toregozhin.springcourse.model.Principal;
import com.toregozhin.springcourse.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            School school = session.get(School.class, 4);
            Principal principal = school.getPrincipal();

            System.out.println("----------------");
            System.out.println(school);
            System.out.println("----------------");
            System.out.println(principal);

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

