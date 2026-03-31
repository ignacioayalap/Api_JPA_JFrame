/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tarifa")
public class Tarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa")
    private Long id;

    @Column(name = "numero_tarifa", nullable = false)
    private int numeroTarifa;

    @Column(name = "impuesto_tarifa")
    private int impuestoTarifa;

    @Column(name = "precio_tarifa", nullable = false)
    private int precioTarifa;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_tarifa", nullable = false)
    private Clase claseTarifa;

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    public int getNumeroTarifa() {
        return numeroTarifa;
    }

    public void setNumeroTarifa(int numeroTarifa) {
        this.numeroTarifa = numeroTarifa;
    }

    public int getImpuestoTarifa() {
        return impuestoTarifa;
    }

    public void setImpuestoTarifa(int impuestoTarifa) {
        this.impuestoTarifa = impuestoTarifa;
    }

    public int getPrecioTarifa() {
        return precioTarifa;
    }

    public void setPrecioTarifa(int precioTarifa) {
        this.precioTarifa = precioTarifa;
    }

    public Clase getClaseTarifa() {
        return claseTarifa;
    }

    public void setClaseTarifa(Clase claseTarifa) {
        this.claseTarifa = claseTarifa;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
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
        if (!(object instanceof Tarifa)) {
            return false;
        }
        Tarifa other = (Tarifa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tarifa[ id=" + id + " ]";
    }

}