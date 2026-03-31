/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @Column(name = "numero_reserva", unique = true, nullable = false)
    private int numeroReserva;

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vueloReservado;

    @OneToOne
    @JoinColumn(name = "id_pago")
    private Pago pagoReserva;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Usuario personaReserva;

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public Vuelo getVueloReservado() {
        return vueloReservado;
    }

    public void setVueloReservado(Vuelo vueloReservado) {
        this.vueloReservado = vueloReservado;
    }

    public Pago getPagoReserva() {
        return pagoReserva;
    }

    public void setPagoReserva(Pago pagoReserva) {
        this.pagoReserva = pagoReserva;
    }

    public Usuario getPersonaReserva() {
        return personaReserva;
    }

    public void setPersonaReserva(Usuario personaReserva) {
        this.personaReserva = personaReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work if the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reserva[ id=" + id + " ]";
    }

}