package Controlador;

import Modelo.*;
import Vistas.AddUserForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;



public class ControllerSucursalPuestoTrabajo implements ActionListener{
    private final AddUserForm view;
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<DatosSucursalPuestoTrabajo> list;
    DatosModelDB model = new DatosModelDB();

       
    public ControllerSucursalPuestoTrabajo(AddUserForm view) {
        this.view = view;
        this.getListaSucursales();
        Sucursal sucursal = (Sucursal) view.cbSucursal.getSelectedItem();
        getPuestosTrabajo(sucursal.getIdSucursal());
        events();
    }
    
    
    
    public final void events(){
        view.cbSucursal.addActionListener(this);
    }

     
    public final void getListaSucursales(){
        list = model.getSucursales();
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                int idSucursal = list.get(i).getIdSucursal();
                String nombreSucursal = list.get(i).getNombreSucursal();
                System.out.println(idSucursal + " - " + nombreSucursal);
                view.cbSucursal.addItem(new Sucursal());
            }  
        }else {
            JOptionPane.showMessageDialog(null, "No se encontraron Sucursales.", "Sucursales", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public final void getPuestosTrabajo(int idSucursal){
        list = model.getPuestosTrabajo(idSucursal);
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                DefaultComboBoxModel model = (DefaultComboBoxModel)view.cbOcupacion.getModel();
                Object [] puestoTrabajo = new Object [2];
                puestoTrabajo[0] = list.get(i).getIdPuestoTrabajo();
                puestoTrabajo[1] = list.get(i).getNombrePuestoTrabajo();
                model.addElement(puestoTrabajo[1]);
                view.cbOcupacion.setModel(model);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron Sucursales.","Sucursales", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        Object evento = ae.getSource();
        if(evento.equals(view.cbSucursal)){
            view.cbOcupacion.removeAllItems();
            Sucursal sucursal = (Sucursal)view.cbSucursal.getSelectedItem();
            getPuestosTrabajo(sucursal.getIdSucursal());
        }
    }
    
}
