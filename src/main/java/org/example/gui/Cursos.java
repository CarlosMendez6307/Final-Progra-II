package org.example.gui;

import org.example.controllers.CursosController;
import org.example.controllers.EstudiantesController;
import org.example.models.CursosEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Cursos extends JFrame{
    private JLabel lblNombreCurso;
    private JTextField txtNombreCurso;
    private JLabel lblProfesor;
    private JTextField txtProfesor;
    private JButton btnVolver;
    private JButton btnUpdate;
    private JButton btnEliminar;
    private JButton btnGrabar;
    private JTable tblCursos;
    private JPanel panelCursos;

    private int id=0;
    public Cursos(){
        super("Cursos");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelCursos);
        LlenarLista();
        tblCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(tblCursos.getSelectedRow() > -1) {
                    id = Integer.parseInt(tblCursos.getValueAt(tblCursos.getSelectedRow(), 0).toString());
                    txtNombreCurso.setText((String) tblCursos.getValueAt(tblCursos.getSelectedRow(), 1));
                    txtProfesor.setText((String) tblCursos.getValueAt(tblCursos.getSelectedRow(), 2));
                }
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame admin = new Administracion();
                        admin.setLocationRelativeTo(null);
                        admin.setSize(600,600);
                        admin.setVisible(true);
                    }
                });
                dispose();
            }
        });
        btnGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CursosController().GrabarCurso(txtNombreCurso.getText(), txtProfesor.getText());
                LlenarLista();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CursosController().UpdateCurso(id,txtNombreCurso.getText(), txtProfesor.getText());
                LlenarLista();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CursosController().DeleteCurso(id);
                LlenarLista();
            }
        });
    }

    private void LlenarLista(){
        txtNombreCurso.setText("");
        txtProfesor.setText("");
        id=0;
        DefaultTableModel model = new DefaultTableModel();


        model.addColumn("IdCurso");
        model.addColumn("NombreCurso");
        model.addColumn("Profesor");


        tblCursos.setModel(model);


        List<CursosEntity> listaCursos = new CursosController().ListCursos();

        for(CursosEntity es: listaCursos){
            String []datos = new String[4];
            datos[0] =  String.valueOf(es.getIdCurso());
            datos[1] = es.getNombreCurso();
            datos[2] = es.getProfesor();
            model.addRow(datos);

        }


    }
}
