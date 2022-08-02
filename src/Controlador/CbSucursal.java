package Controlador;

import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CbSucursal {
    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    ResultSet rs;

    public CbSucursal() {
    }
   
    public ArrayList getListaSucursales() throws SQLException{
        System.out.println("Función getListaSucursales");
        ArrayList mListaSucursales = new ArrayList();
        Sucursal sucursal = null;
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT idSucursal, nombreSucursal FROM `sucursal`;");
            while(rs.next()){
                sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombreSucursal(rs.getString("nombreSucursal"));
                mListaSucursales.add(sucursal);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return mListaSucursales;
    }
}
