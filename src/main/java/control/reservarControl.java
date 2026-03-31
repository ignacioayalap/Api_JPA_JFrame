/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ControladoraPersistencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Vuelo;
import view.reservarView;
import view.menuView;

/**
 *
 * @author nacho
 */
public class reservarControl implements ActionListener {

    private reservarView vista;
    private ControladoraPersistencia controlPersis;
    private Usuario usuarioActual;

    public reservarControl(reservarView vista, ControladoraPersistencia controlPersis, Usuario usuarioActual) {
        this.vista = vista;
        this.controlPersis = controlPersis;
        this.usuarioActual = usuarioActual;

        this.vista.getBtnReservar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);

        cargarVuelos();
    }

    private void cargarVuelos() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaVuelos().getModel();
        modelo.setRowCount(0);
        List<Vuelo> listaV = controlPersis.obtenerTodosVuelos();

        for (Vuelo v : listaV) {
            Object[] row = {
                v.getNumeroVuelo(),
                v.getDestino(),
                v.getOrigen(),
                v.getPrecios() != null ? v.getPrecios() : 0 
            };
            modelo.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnReservar()) {
            reservar();
        } else if (e.getSource() == vista.getBtnVolver()) {
            volverMenu();
        }
    }

    private void reservar() {
        int fila = vista.getTablaVuelos().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un vuelo de la tabla.");
            return;
        }

        int nVuelo = (int) vista.getTablaVuelos().getValueAt(fila, 0);
        Vuelo v = controlPersis.buscarVueloPorNumero(nVuelo);

        if (v != null) {
            Reserva r = new Reserva();
            r.setNumeroReserva((int) (Math.random() * 100000));
            r.setVueloReservado(v);
            r.setPersonaReserva(usuarioActual);

            controlPersis.guardarReserva(r);
            JOptionPane.showMessageDialog(vista, "¡Reserva realizada con éxito!");
            volverMenu();
        }
    }

    private void volverMenu() {
        vista.dispose();
        menuView mVista = new menuView();
        mVista.setLocationRelativeTo(null);
        new menuControl(mVista, controlPersis, usuarioActual);
        mVista.setVisible(true);
    }
}
