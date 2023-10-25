package org.example.controllers;

import org.example.models.CursosEntity;
import org.example.models.EstudiantesEntity;
import org.example.models.InscripcionesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class InscripcionesController {
    public void GrabarInscripcion(int idEstudiante, int idCurso){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                InscripcionesEntity inscripcion = new InscripcionesEntity();
                inscripcion.setEstudiantes(session.get(EstudiantesEntity.class,idEstudiante));
                inscripcion.setCursos(session.get(CursosEntity.class,idCurso));
                long millis=System.currentTimeMillis();
                inscripcion.setFechaInscripcion(  new Date(millis));
                session.save(inscripcion);
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

    public void UpdateInscripcion(int id, int idEstudiante, int idCurso){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                InscripcionesEntity inscripcion = session.get(InscripcionesEntity.class, id);
                inscripcion.setEstudiantes(session.get(EstudiantesEntity.class,idEstudiante));
                inscripcion.setCursos(session.get(CursosEntity.class,idCurso));
                long millis=System.currentTimeMillis();
                inscripcion.setFechaInscripcion(  new Date(millis));
                session.save(inscripcion);
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

    public void DeleteInscripcion(int id){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                InscripcionesEntity curso =session.get(InscripcionesEntity.class, id);
                session.delete(curso);
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
    public List<InscripcionesEntity> ListInscripciones(){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<InscripcionesEntity> cq = cb.createQuery(InscripcionesEntity.class);
        Root<InscripcionesEntity> rootEntry = cq.from(InscripcionesEntity.class);
        CriteriaQuery<InscripcionesEntity> all = cq.select(rootEntry);

        TypedQuery<InscripcionesEntity> allQuery = session.createQuery(all);
        //TypedQuery<InscripcionesEntity> allQuery = session.createQuery("from InscripcionesEntity i");
        return allQuery.getResultList();
    }
}
