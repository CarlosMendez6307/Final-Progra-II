package org.example.controllers;

import org.example.models.CursosEntity;
import org.example.models.EstudiantesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CursosController {

    public void GrabarCurso(String nombreCurso, String profeso){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                CursosEntity curso = new CursosEntity();
                curso.setNombreCurso(nombreCurso);
                curso.setProfesor(profeso);
                session.save(curso);
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

    public void UpdateCurso(int id, String nombreCurso, String profeso){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                CursosEntity curso = session.get(CursosEntity.class, id);
                curso.setNombreCurso(nombreCurso);
                curso.setProfesor(profeso);
                session.save(curso);
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

    public void DeleteCurso(int id){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session != null){
            System.out.println("Session Correcta");

            try{
                session.beginTransaction();
                CursosEntity curso =session.get(CursosEntity.class, id);
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
    public List<CursosEntity> ListCursos(){
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CursosEntity> cq = cb.createQuery(CursosEntity.class);
        Root<CursosEntity> rootEntry = cq.from(CursosEntity.class);
        CriteriaQuery<CursosEntity> all = cq.select(rootEntry);

        TypedQuery<CursosEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
