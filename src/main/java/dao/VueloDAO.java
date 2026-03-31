/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import modelo.Vuelo;
import modelo.Aeropuerto;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class VueloDAO extends GenericDAO<Vuelo, Long> {
    
    public VueloDAO() {
        super(Vuelo.class);
    }
    
     
    public Vuelo findByNumero(int numeroVuelo) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
        String jpql = "SELECT v FROM Vuelo v WHERE v.numeroVuelo = :numero";
        TypedQuery<Vuelo> query = em.createQuery(jpql, Vuelo.class);
        query.setParameter("numero", numeroVuelo);
        Vuelo vuelo = query.getSingleResult();

        
        vuelo.getTarifas().size();
        vuelo.getAeropuertos().size();

        return vuelo;
    } catch (Exception e) {
        System.out.println("Error en findByNumero: " + e.getMessage());
        return null;
    } finally {
        em.close();
    }
}

    
     
    public List<Vuelo> findByOrigenDestino(Long idOrigen, Long idDestino) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT v FROM Vuelo v " +
                         "JOIN v.aeropuertos a1 " +
                         "JOIN v.aeropuertos a2 " +
                         "WHERE a1.id = :origen AND a2.id = :destino";
            TypedQuery<Vuelo> query = em.createQuery(jpql, Vuelo.class);
            query.setParameter("origen", idOrigen);
            query.setParameter("destino", idDestino);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
     
    public List<Vuelo> findByFecha(Date fecha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Vuelo v WHERE v.fecha.fecha = :fecha";
            TypedQuery<Vuelo> query = em.createQuery(jpql, Vuelo.class);
            query.setParameter("fecha", fecha);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
     
    public List<Vuelo> findByAerolinea(Long idAerolinea) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Vuelo v WHERE v.aerolinea.id = :id";
            TypedQuery<Vuelo> query = em.createQuery(jpql, Vuelo.class);
            query.setParameter("id", idAerolinea);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
