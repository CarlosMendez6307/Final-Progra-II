package org.example.controllers;

import org.example.models.EstudiantesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EstudiantesController {
    public void GrabarEstuidante(String nombre, String apellido, String email){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                EstudiantesEntity estudiante = new EstudiantesEntity();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setEmail(email);
                session.save(estudiante);
                session.getTransaction().commit();
                sessionFactory.close();


            }catch(Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        }else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public void UpdateEstudiante(int id, String nombre, String apellido, String email){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                EstudiantesEntity estudiante = session.get(EstudiantesEntity.class, id);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setEmail(email);
                session.save(estudiante);
                session.getTransaction().commit();
                sessionFactory.close();


            }catch(Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        }else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public void DeleteEstudiante(int id){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                EstudiantesEntity estudiante =session.get(EstudiantesEntity.class, id);
                session.delete(estudiante);
                session.getTransaction().commit();
                sessionFactory.close();


            }catch(Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        }else {
            System.out.println("Error al abrir la conexion");
        }
    }
    public List<EstudiantesEntity> ListEstudiantes(){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EstudiantesEntity> cq = cb.createQuery(EstudiantesEntity.class);
        Root<EstudiantesEntity> rootEntry = cq.from(EstudiantesEntity.class);
        CriteriaQuery<EstudiantesEntity> all = cq.select(rootEntry);

        TypedQuery<EstudiantesEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
