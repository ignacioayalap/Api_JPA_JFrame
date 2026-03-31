/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ControladoraPersistencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.menuView;
import view.registroView;
import view.loginView;
import modelo.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class registroControl implements ActionListener {

    private registroView vista;
    private ControladoraPersistencia controlPersis;

    public registroControl(registroView vista, ControladoraPersistencia controlPersis) {
        this.vista = vista;
        this.controlPersis = controlPersis;

        this.vista.getBtnCrearUsuario().addActionListener(this);
        this.vista.getBtnVolverRegistro().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnCrearUsuario()) {
            registrar();
        } else if (e.getSource() == vista.getBtnVolverRegistro()) {
            volverLogin();
        }
    }

    private void registrar() {
        String nombre = vista.getInNombre().getText();
        String apellido = vista.getInApellido().getText();
        String correo = vista.getInCorreo().getText();
        String pass = vista.getInContraseña().getText();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.");
            return;
        }

        Usuario u = new Usuario();
        u.setNombrePersona(nombre);
        u.setApellidoPersona(apellido);
        u.setCorreoElectronicoUsuario(correo);
        u.setContraseñaUsuario(pass);
        u.setDniPersona(0); 
        u.setCelular("000000");
        u.setNumeroUsuario((int)(Math.random()*10000));

        controlPersis.guardarUsuario(u);
        JOptionPane.showMessageDialog(vista, "Usuario registrado con éxito.");
        volverLogin();
    }

    private void volverLogin() {
        vista.dispose();
        loginView lVista = new loginView();
        lVista.setLocationRelativeTo(null);
        new loginControl(lVista, controlPersis);
        lVista.setVisible(true);
    }
}
