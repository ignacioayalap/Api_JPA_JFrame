/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Usuario extends Persona implements Serializable {

    @Column(name = "numero_usuario", unique = true, nullable = false)
    private int numeroUsuario;

    @Column(name = "celular", nullable = true)
    private String celular;

    @Column(name = "contrasena_usuario", nullable = false)
    private String contraseñaUsuario;

    @Column(name = "correo_electronico_usuario", unique = true, nullable = false)
    private String correoElectronicoUsuario;

    @OneToMany(mappedBy = "personaReserva")
    private List<Reserva> listaReservas;

    @OneToMany(mappedBy = "usuarioConsultante")
    private List<Consulta> listaConsultasUsuario;

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(int numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public String getCorreoElectronicoUsuario() {
        return correoElectronicoUsuario;
    }

    public void setCorreoElectronicoUsuario(String correoElectronicoUsuario) {
        this.correoElectronicoUsuario = correoElectronicoUsuario;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public List<Consulta> getListaConsultasUsuario() {
        return listaConsultasUsuario;
    }

    public void setListaConsultasUsuario(List<Consulta> listaConsultasUsuario) {
        this.listaConsultasUsuario = listaConsultasUsuario;
    }

}