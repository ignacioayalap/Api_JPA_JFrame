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
@Table(name = "asiento")
public class Asiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento")
    private Long id;

    @Column(name = "fila_asiento", nullable = false)
    private int filaAsiento;

    @Column(name = "letra_asiento", nullable = false)
    private char letraAsiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_asiento", nullable = false)
    private Clase claseAsiento;

    @ManyToOne
    @JoinColumn(name = "id_avion")
    private Avion avion;

    public int getFilaAsiento() {
        return filaAsiento;
    }

    public void setFilaAsiento(int filaAsiento) {
        this.filaAsiento = filaAsiento;
    }

    public char getLetraAsiento() {
        return letraAsiento;
    }

    public void setLetraAsiento(char letraAsiento) {
        this.letraAsiento = letraAsiento;
    }

    public Clase getClaseAsiento() {
        return claseAsiento;
    }

    public void setClaseAsiento(Clase claseAsiento) {
        this.claseAsiento = claseAsiento;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
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
        if (!(object instanceof Asiento)) {
            return false;
        }
        Asiento other = (Asiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Asiento[ id=" + id + " ]";
    }

}