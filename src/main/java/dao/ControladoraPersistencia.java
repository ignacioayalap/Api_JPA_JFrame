/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import modelo.Tarifa;
import modelo.Usuario;
import modelo.Aerolinea;
import modelo.Avion;
import modelo.Fecha;
import modelo.Piloto;
import modelo.Aeropuerto;
import modelo.Reserva;
import modelo.Pago;
import modelo.Ciudad;
import modelo.Vuelo;
import modelo.*;
import  dao.AerolineaDAO;
import  dao.AeropuertoDAO;
import  dao.AvionDAO;
import  dao.CiudadDAO;
import  dao.FechaDAO;
import  dao.PagoDAO;
import  dao.PilotoDAO;
import  dao.ReservaDAO;
import  dao.TarifaDAO;
import  dao.UsuarioDAO;
import  dao.VueloDAO;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.EntityTransaction;
 
public class ControladoraPersistencia {
    
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private VueloDAO vueloDAO = new VueloDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();
    private AeropuertoDAO aeropuertoDAO = new AeropuertoDAO();
    private TarifaDAO tarifaDAO = new TarifaDAO();
    private AerolineaDAO aerolineaDAO = new AerolineaDAO();
    private AvionDAO avionDAO = new AvionDAO();
    private PilotoDAO pilotoDAO = new PilotoDAO();
    private FechaDAO fechaDAO = new FechaDAO();
    private PagoDAO pagoDAO = new PagoDAO();
    private CiudadDAO ciudadDAO = new CiudadDAO();
    
     
    
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }
    
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioDAO.findById(id);
    }
    
    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioDAO.findByCorreo(correo);
    }
    
    public Usuario loginUsuario(String correo, String contraseña) {
        return usuarioDAO.login(correo, contraseña);
    }
    
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.findAll();
    }
    
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.update(usuario);
    }
    
    public void eliminarUsuario(Long id) {
        usuarioDAO.deleteById(id);
    }
    
    
    
    public void guardarVuelo(Vuelo vuelo) {
        vueloDAO.save(vuelo);
    }
    
    public Vuelo buscarVueloPorId(Long id) {
        return vueloDAO.findById(id);
    }
    
    public Vuelo buscarVueloPorNumero(int numeroVuelo) {
        return vueloDAO.findByNumero(numeroVuelo);
    }
    
    public List<Vuelo> obtenerTodosVuelos() {
        return vueloDAO.findAll();
    }
    
    public List<Vuelo> buscarVuelosPorOrigenDestino(Long idOrigen, Long idDestino) {
        return vueloDAO.findByOrigenDestino(idOrigen, idDestino);
    }
    
    public void actualizarVuelo(Vuelo vuelo) {
        vueloDAO.update(vuelo);
    }
    
    public void eliminarVuelo(Long id) {
        vueloDAO.deleteById(id);
    }
    
     
    
    public void guardarReserva(Reserva reserva) {
    EntityManager em = JPAUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
        tx.begin();
        
         
        if (reserva.getVueloReservado() != null && reserva.getVueloReservado().getId() != null) {
            Vuelo vueloExistente = em.find(Vuelo.class, reserva.getVueloReservado().getId());
            reserva.setVueloReservado(vueloExistente);
        }
        
        em.persist(reserva);
        tx.commit();
    } catch (Exception e) {
        if (tx.isActive()) {
            tx.rollback();
        }
        throw new RuntimeException("Error al guardar la reserva: " + e.getMessage(), e);
    } finally {
        em.close();
    }
}
    
    public Reserva buscarReservaPorId(Long id) {
        return reservaDAO.findById(id);
    }
    
    public List<Reserva> obtenerTodasReservas() {
        return reservaDAO.findAll();
    }
    
    public void actualizarReserva(Reserva reserva) {
        reservaDAO.update(reserva);
    }
    
    public void eliminarReserva(Long id) {
        reservaDAO.deleteById(id);
    }
    
     
    
    public void guardarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertoDAO.save(aeropuerto);
    }
    
    public Aeropuerto buscarAeropuertoPorId(Long id) {
        return aeropuertoDAO.findById(id);
    }
    
    public List<Aeropuerto> obtenerTodosAeropuertos() {
        return aeropuertoDAO.findAll();
    }
    
     
    
    public void guardarTarifa(Tarifa tarifa) {
        tarifaDAO.save(tarifa);
    }
    
    public Tarifa buscarTarifaPorId(Long id) {
        return tarifaDAO.findById(id);
    }
    
    public List<Tarifa> obtenerTodasTarifas() {
        return tarifaDAO.findAll();
    }
    
     
    
    public void guardarAerolinea(Aerolinea aerolinea) {
        aerolineaDAO.save(aerolinea);
    }
    
    public void guardarAvion(Avion avion) {
        avionDAO.save(avion);
    }
    
    public void guardarPiloto(Piloto piloto) {
        pilotoDAO.save(piloto);
    }
    
    public void guardarFecha(Fecha fecha) {
        fechaDAO.save(fecha);
    }
    
    public void guardarPago(Pago pago) {
        pagoDAO.save(pago);
    }
    
    public void guardarCiudad(Ciudad ciudad) {
        ciudadDAO.save(ciudad);
    }
}