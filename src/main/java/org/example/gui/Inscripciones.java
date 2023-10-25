package org.example.gui;

import org.example.controllers.CursosController;
import org.example.controllers.EstudiantesController;
import org.example.controllers.InscripcionesController;
import org.example.models.CursosEntity;
import org.example.models.EstudiantesEntity;
import org.example.models.InscripcionesEntity;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.List;
import java.util.Vector;



public class Inscripciones extends JFrame{
    private JPanel panelInscripciones;
    private JLabel lblEstudiante;
    private JComboBox cboEstudiante;
    private JLabel lblCuso;
    private JComboBox cboCurso;
    private JButton btnVolver;
    private JButton btnGrabar;
    private JButton btnUpdate;
    private JButton btnEliminar;
    private JTable tblInscripciones;
    private JScrollPane panelHeader;
    int id = 0;

    public Inscripciones(){
        super("Inscripciones");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelInscripciones);
        LlenarLista();
        LLenaCombos();
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
                new InscripcionesController().GrabarInscripcion(((Item)cboEstudiante.getSelectedItem()).getId(),((Item)cboCurso.getSelectedItem()).getId());
                LlenarLista();

            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Integer.parseInt(
                        tblInscripciones.getValueAt(tblInscripciones.getSelectedRow(), 0
                        ).toString());
                new InscripcionesController()
                        .DeleteInscripcion(id);
               LlenarLista();
            }

        });
        btnUpdate.addComponentListener(new ComponentAdapter() {
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblInscripciones.getValueAt(tblInscripciones.getSelectedRow(), 0) != null){
                    id = Integer.parseInt(
                            tblInscripciones.getValueAt(tblInscripciones.getSelectedRow(), 0
                            ).toString());
                    new InscripcionesController().UpdateInscripcion(id,((Item)cboEstudiante.getSelectedItem()).getId(),((Item)cboCurso.getSelectedItem()).getId());
                    LlenarLista();
                }
            }
        });
    }

    private void LLenaCombos() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<EstudiantesEntity> listaEstudiantes = new EstudiantesController().ListEstudiantes();
        for (EstudiantesEntity es: listaEstudiantes
             ) {
            model.addElement(new Item(es.getIdEstudiante(),es.getNombre()));
        }
        cboEstudiante.setModel(model);

        DefaultComboBoxModel modelCursos = new DefaultComboBoxModel();
        List<CursosEntity> listaCursos = new CursosController().ListCursos();
        for (CursosEntity es: listaCursos
        ) {
            modelCursos.addElement(new Item(es.getIdCurso(),es.getNombreCurso()));
        }
        cboCurso.setModel(modelCursos);

    }

    private void LlenarLista() {
        id=0;
        String [] header = {"IdInscripcion", "Estudiante", "Curso", "Fecha"};

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("IdInscripcion");
        model.addColumn("Estudiante");
        model.addColumn("Curso");
        model.addColumn("Fecha");

        tblInscripciones.setModel(model);


        List<InscripcionesEntity> listaInscripciones = new InscripcionesController().ListInscripciones();

        for(InscripcionesEntity es: listaInscripciones){
            String []datos = new String[4];
            datos[0] =  String.valueOf(es.getIdInscripcion());
            datos[1] = es.getEstudiantes().getNombre();
            datos[2] = es.getCursos().getNombreCurso();
            datos[3] = es.getFechaInscripcion().toString();

            model.addRow(datos);
        }

    }

}

class Item {
    private int id;
    private String description;

    public Item(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }
}
class ItemRenderer extends BasicComboBoxRenderer
{
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value != null)
        {
            Item item = (Item)value;
            setText( item.getDescription().toUpperCase() );
        }

        if (index == -1)
        {
            Item item = (Item)value;
            setText( "" + item.getId() );
        }


        return this;
    }
}
