/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "piloto")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Piloto extends Persona implements Serializable {

    @Column(name = "numero_piloto", nullable = false)
    private int numeroPiloto;

    public int getNumeroPiloto() {
        return numeroPiloto;
    }

    public void setNumeroPiloto(int numeroPiloto) {
        this.numeroPiloto = numeroPiloto;
    }

}