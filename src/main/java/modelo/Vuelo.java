/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "vuelo")
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Long id;

    @Column(name = "numero_vuelo", unique = true, nullable = false)
    private int numeroVuelo;

    @ManyToOne
    @JoinColumn(name = "id_avion")
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "id_fecha")
    private Fecha fecha;

    @ManyToOne
    @JoinColumn(name = "id_piloto")
    private Piloto piloto;

    @ManyToOne
    @JoinColumn(name = "id_aerolinea")
    private Aerolinea aerolinea;

    @ManyToMany
    @JoinTable(
        name = "vuelo_aeropuerto",
        joinColumns = @JoinColumn(name = "id_vuelo"),
        inverseJoinColumns = @JoinColumn(name = "id_aeropuerto")
    )
    private List<Aeropuerto> aeropuertos;

    @OneToMany(mappedBy = "vuelo")
    private List<Tarifa> tarifas;

    @OneToMany(mappedBy = "vueloReservado")
    private List<Reserva> listaReservas;

    @OneToMany(mappedBy = "vueloConsultado")
    private List<Consulta> listaConsultas;

    // Métodos delegados para facilitar el acceso en JTable
    public String getDestino() {
        if (aeropuertos != null && aeropuertos.size() > 1) {
            return aeropuertos.get(1).getNombreAeropuerto();
        }
        return "N/A";
    }

    public String getOrigen() {
        if (aeropuertos != null && !aeropuertos.isEmpty()) {
            return aeropuertos.get(0).getNombreAeropuerto();
        }
        return "N/A";
    }

    public Integer getPrecios() {
        if (tarifas != null && !tarifas.isEmpty()) {
            return tarifas.get(0).getPrecioTarifa();
        }
        return 0;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public List<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(List<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }

    public void setListaConsultas(List<Consulta> listaConsultas) {
        this.listaConsultas = listaConsultas;
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
        if (!(object instanceof Vuelo)) {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Vuelo[ id=" + id + " ]";
    }

}