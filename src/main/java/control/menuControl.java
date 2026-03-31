/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ControladoraPersistencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.menuView;
import view.reservarView;
import view.misReservasView;
import view.loginView;
import view.editarView;
import modelo.Usuario;

/**
 *
 * @author nacho
 */
public class menuControl implements ActionListener {

    private menuView vista;
    private ControladoraPersistencia controlPersis;
    private Usuario usuarioActual;

    public menuControl(menuView vista, ControladoraPersistencia controlPersis, Usuario usuarioActual) {
        this.vista = vista;
        this.controlPersis = controlPersis;
        this.usuarioActual = usuarioActual;

        this.vista.getBtnCerrarSesion().addActionListener(this);
        this.vista.getBtnReservarVuelo().addActionListener(this);
        this.vista.getBtnMisReservas().addActionListener(this);
        this.vista.getBtnEditarPerfil().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnCerrarSesion()) {
            cerrarSesion();
        } else if (e.getSource() == vista.getBtnReservarVuelo()) {
            abrirReservar();
        } else if (e.getSource() == vista.getBtnMisReservas()) {
            abrirMisReservas();
        } else if (e.getSource() == vista.getBtnEditarPerfil()) {
            abrirEditarPerfil();
        }
    }

    private void cerrarSesion() {
        vista.dispose();
        loginView loginV = new loginView();
        loginV.setLocationRelativeTo(null);
        new loginControl(loginV, controlPersis);
        loginV.setVisible(true);
    }

    private void abrirReservar() {
        vista.dispose();
        reservarView rVista = new reservarView();
        rVista.setLocationRelativeTo(null);
        new reservarControl(rVista, controlPersis, usuarioActual);
        rVista.setVisible(true);
    }

    private void abrirMisReservas() {
        vista.dispose();
        misReservasView mrVista = new misReservasView();
        mrVista.setLocationRelativeTo(null);
        new misReservasControl(mrVista, controlPersis, usuarioActual);
        mrVista.setVisible(true);
    }

    private void abrirEditarPerfil() {
        vista.dispose();
        editarView eVista = new editarView();
        eVista.setLocationRelativeTo(null);
        new editarControl(eVista, controlPersis, usuarioActual);
        eVista.setVisible(true);
    }
}
