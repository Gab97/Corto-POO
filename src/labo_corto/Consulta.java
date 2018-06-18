/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_corto;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    public JLabel lblCodigo, lblNombre, lblTipo, lblCantidad;
    
    public JTextField codigo, nombre, cantidad;
    
    public JComboBox tipo;
    
    public JPanel table;
    
    public JButton buscar, actualizar, eliminar, limpiar, insertar;
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblNombre);
        container.add(lblCantidad);
        container.add(lblTipo);
        container.add(codigo);
        container.add(nombre);
        container.add(cantidad);
        container.add(tipo);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }
    
    public final void agregarLabels(){
        lblCodigo = new JLabel("Codigo");
        lblNombre = new JLabel("Nombre");
        lblTipo = new JLabel("Tipo");
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblTipo.setBounds(10, 100, ANCHOC, ALTOC);
    }
    
    public final void formulario(){
        codigo = new JTextField();
        tipo = new JComboBox();       
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        buscar = new JButton("Buscar");
        actualizar = new JButton ("Actualizar");
        eliminar = new JButton("Eliminar");
        limpiar = new JButton("Limpiar");
        insertar = new JButton("Insertar");
        
        table = new JPanel();
        
        codigo.setBounds(140, 10, ANCHOC, ALTOC);        
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        cantidad.setBounds(140, 100, ANCHOC, ALTOC);
        codigo.setBounds(210, 140, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(30, 210, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        
        
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    private void llenarTabla() {

        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;

                    case 1:
                        return String.class;

                    case 2:
                        return String.class;

                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Cantidad");
        tm.addColumn("Disponibilidad");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();
        
        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCodigo(), fi.setDisponibilidad()});
    }
        resultados.setModel(tm);
    }

    public void eventos() {

        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getSelectedItem.toString(), Integer.parseInt(cantidad.getText()), true);

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), true);

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();

                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito.");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());

                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El filtro buscado no ha sido encontrado");
                } else {
                    codigo.setText(f.getNombre());
                    nombre.setSelectedItem(f.getNombre());
                    cantidad.setText(Integer.toString(f.getStock()));

                    if (f.getDisponilididad()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });
        
       limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
       });
    }
    
    public void limpiarCampos(){
        codigo.setText("");
        nombre.setSelectedItem("FRAM");
        cantidad.setText("");
    }
    
    public static void main(String [] args){
        java.awt.EventQueue.invokeLater( new Runnable(){
            @Override
            public void run() {
                new Consulta().setVisible(true);
            } 
        });
    }

}
