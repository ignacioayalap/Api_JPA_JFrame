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
import view.misReservasView;
import view.menuView;

/**
 *
 * @author nacho
 */
public class misReservasControl implements ActionListener {

    private misReservasView vista;
    private ControladoraPersistencia controlPersis;
    private Usuario usuarioActual;

    public misReservasControl(misReservasView vista, ControladoraPersistencia controlPersis, Usuario usuarioActual) {
        this.vista = vista;
        this.controlPersis = controlPersis;
        this.usuarioActual = usuarioActual;


        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);

        cargarReservas();
    }

    private void cargarReservas() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaReservas().getModel();
        modelo.setRowCount(0);

        List<Reserva> listaR = controlPersis.obtenerTodasReservas();

        for (Reserva r : listaR) {

            if (r.getPersonaReserva() != null && r.getPersonaReserva().getId().equals(usuarioActual.getId())) {
                Object[] row = {
                    r.getNumeroReserva(),
                    r.getVueloReservado() != null ? r.getVueloReservado().getNumeroVuelo() : "N/A",
                    r.getVueloReservado() != null ? r.getVueloReservado().getDestino() : "N/A",
                    r.getVueloReservado() != null ? r.getVueloReservado().getOrigen() : "N/A",
                    r.getVueloReservado() != null ? r.getVueloReservado().getPrecios() : 0
                };
                modelo.addRow(row);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnVolver()) {
            volverMenu();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminarReserva();
        }
    }

    private void eliminarReserva() {
        int fila = vista.getTablaReservas().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una reserva para eliminar.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro de que desea eliminar esta reserva?", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            try {

                int nReserva = (int) vista.getTablaReservas().getValueAt(fila, 0);
                

                List<Reserva> reservas = controlPersis.obtenerTodasReservas();
                for (Reserva r : reservas) {
                    if (r.getNumeroReserva() == nReserva) {
                        controlPersis.eliminarReserva(r.getId());
                        break;
                    }
                }

                JOptionPane.showMessageDialog(vista, "Reserva eliminada.");
                cargarReservas(); // Refrescar tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
            }
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
