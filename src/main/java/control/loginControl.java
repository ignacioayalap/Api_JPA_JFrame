/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ControladoraPersistencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import view.loginView;
import view.registroView;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class loginControl implements ActionListener {

    private loginView vista;
    private ControladoraPersistencia controlPersis;

    public loginControl(loginView vista, ControladoraPersistencia controlPersis) {
        this.vista = vista;
        this.controlPersis = controlPersis;


        this.vista.getBtnIniciarSesion().addActionListener(this);
        this.vista.getBtnRegistro().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnIniciarSesion()) {
            iniciarSesion();
        } else if (e.getSource() == vista.getBtnRegistro()) {
            abrirRegistro();
        }
    }

    private void iniciarSesion() {
        String correo = vista.getInLoginMail().getText();
        String pass = vista.getInLoginPass().getText();

        if (correo.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos.");
            return;
        }

        Usuario u = controlPersis.loginUsuario(correo, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(vista, "Bienvenido " + u.getNombrePersona());
            vista.dispose();
            

            view.menuView menuV = new view.menuView();
            menuV.setLocationRelativeTo(null);
            new menuControl(menuV, controlPersis, u);
            menuV.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas.");
        }
    }

    private void abrirRegistro() {
        vista.dispose();
        registroView rVista = new registroView();
        rVista.setLocationRelativeTo(null);
        new registroControl(rVista, controlPersis);
        rVista.setVisible(true);
    }
}
