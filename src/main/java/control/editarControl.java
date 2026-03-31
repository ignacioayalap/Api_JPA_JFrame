/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ControladoraPersistencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import view.editarView;
import view.menuView;

/**
 *
 * @author nacho
 */
public class editarControl implements ActionListener {

    private editarView vista;
    private ControladoraPersistencia controlPersis;
    private Usuario usuarioActual;

    public editarControl(editarView vista, ControladoraPersistencia controlPersis, Usuario usuarioActual) {
        this.vista = vista;
        this.controlPersis = controlPersis;
        this.usuarioActual = usuarioActual;


        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);


        cargarDatosActuales();
    }


    private void cargarDatosActuales() {
        vista.getInNombre().setText(usuarioActual.getNombrePersona());
        vista.getInApellido().setText(usuarioActual.getApellidoPersona());
        vista.getInDni().setText(String.valueOf(usuarioActual.getDniPersona()));
        vista.getInCelular().setText(usuarioActual.getCelular());
        vista.getInCorreo().setText(usuarioActual.getCorreoElectronicoUsuario());
        vista.getInPass().setText(usuarioActual.getContraseñaUsuario());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarCambios();
        } else if (e.getSource() == vista.getBtnVolver()) {
            volverMenu();
        }
    }

    private void guardarCambios() {
        String nombre   = vista.getInNombre().getText().trim();
        String apellido = vista.getInApellido().getText().trim();
        String dniStr   = vista.getInDni().getText().trim();
        String celular  = vista.getInCelular().getText().trim();
        String correo   = vista.getInCorreo().getText().trim();
        String pass     = new String(vista.getInPass().getPassword()).trim();


        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Nombre, apellido, correo y contraseña son obligatorios.",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }


        int dni = 0;
        if (!dniStr.isEmpty()) {
            try {
                dni = Integer.parseInt(dniStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista,
                    "El DNI debe ser un número válido.",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (!correo.equals(usuarioActual.getCorreoElectronicoUsuario())) {
            Usuario existente = controlPersis.buscarUsuarioPorCorreo(correo);
            if (existente != null) {
                JOptionPane.showMessageDialog(vista,
                    "El correo ya está en uso por otro usuario.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Aplicar cambios al objeto usuario
        usuarioActual.setNombrePersona(nombre);
        usuarioActual.setApellidoPersona(apellido);
        usuarioActual.setDniPersona(dni);
        usuarioActual.setCelular(celular.isEmpty() ? "Sin celular" : celular);
        usuarioActual.setCorreoElectronicoUsuario(correo);
        usuarioActual.setContraseñaUsuario(pass);

        try {
            controlPersis.actualizarUsuario(usuarioActual);
            JOptionPane.showMessageDialog(vista,
                "Perfil actualizado correctamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            volverMenu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                "Error al guardar los cambios: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverMenu() {
        vista.dispose();
        menuView mVista = new menuView();
        mVista.setLocationRelativeTo(null);
        menuControl mControl = new menuControl(mVista, controlPersis, usuarioActual);
        mVista.setVisible(true);
    }
}
