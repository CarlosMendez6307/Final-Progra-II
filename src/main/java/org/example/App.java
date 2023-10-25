package org.example;

import org.example.gui.Login;

import javax.swing.*;

public class App 
{
public static void main(String[] args){

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            JFrame login = new Login();
            login.setLocationRelativeTo(null);
            login.setSize(500,150);
            login.setVisible(true);
        }
    });

}
}
