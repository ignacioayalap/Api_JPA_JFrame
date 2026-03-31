/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import control.loginControl;
import dao.ControladoraPersistencia;
import view.loginView;

/**
 *
 * @author nacho
 */
public class SisReservaJPA_Ayala {

    public static void main(String[] args) {
        ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        
        loginView login = new loginView();
        login.setLocationRelativeTo(null);
        

        loginControl control = new loginControl(login, controlPersis);
        
        login.setVisible(true);
    }
}
