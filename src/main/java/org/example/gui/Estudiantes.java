package org.example.gui;

import org.example.controllers.EstudiantesController;
import org.example.models.EstudiantesEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Estudiantes extends JFrame{
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellido;
    private JTextField txtApellido;
    private JLabel lblEmail;
    private JButton volverButton;
    private JButton grabarButton;
    private JButton eliminarButton;
    private JPanel panelEstudiante;
    private JTextField txtEmail;
    private JTable tblEstudiantes;
    private JButton btnUpdate;

    private  int id = 0;

    public Estudiantes(){
        super("Estudiantes");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelEstudiante);

        LlenarLista();
        grabarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EstudiantesController().GrabarEstuidante(txtNombre.getText(), txtApellido.getText(), txtEmail.getText());
                LlenarLista();
            }
        });
        tblEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(tblEstudiantes.getSelectedRow() > -1) {
                    id = Integer.parseInt(tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 0).toString());
                    txtNombre.setText((String) tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 1));
                    txtApellido.setText((String) tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 2));
                    txtEmail.setText((String) tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 3));

                }
                //System.out.println(selectedCellValue);
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EstudiantesController().DeleteEstudiante(id);
                LlenarLista();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EstudiantesController().UpdateEstudiante(id ,txtNombre.getText(), txtApellido.getText(), txtEmail.getText());
                LlenarLista();
            }
        });
        volverButton.addActionListener(new ActionListener() {
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
    }

    private void LlenarLista(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        id=0;
       DefaultTableModel model = new DefaultTableModel();


        model.addColumn("IdEstudiante");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Email");

        tblEstudiantes.setModel(model);


        List<EstudiantesEntity> listaEstudiantes = new EstudiantesController().ListEstudiantes();

        for(EstudiantesEntity es: listaEstudiantes){
            String []datos = new String[4];
            datos[0] =  String.valueOf(es.getIdEstudiante());
            datos[1] = es.getNombre();
            datos[2] = es.getApellido();
            datos[3] = es.getEmail();
         model.addRow(datos);

        }


    }
}
