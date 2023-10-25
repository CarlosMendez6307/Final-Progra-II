package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administracion extends  JFrame{
    private JPanel panelAdministracion;
    private JButton inscripcionesButton;
    private JButton estudiantesButton;
    private JButton cursosButton;
    private MenuBar menuBar;

    private Menu menu;

    public Administracion(){
        super("Administracion");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelAdministracion);

        inscripcionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame inscripciones = new Inscripciones();
                        inscripciones.setLocationRelativeTo(null);
                        inscripciones.setSize(600,600);
                        inscripciones.setVisible(true);
                    }
                });
                dispose();

            }
        });
        estudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame estudiantes = new Estudiantes();
                        estudiantes.setLocationRelativeTo(null);
                        estudiantes.setSize(600,600);
                        estudiantes.setVisible(true);
                    }
                });
                dispose();
            }
        });
        cursosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame estudiantes = new Cursos();
                        estudiantes.setLocationRelativeTo(null);
                        estudiantes.setSize(600,600);
                        estudiantes.setVisible(true);
                    }
                });
                dispose();
            }
        });
    }

}
