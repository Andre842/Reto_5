package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatosModelDB {

    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    DatosSucursalPuestoTrabajo datosDB;

    public ArrayList<DatosSucursalPuestoTrabajo> getPuestosTrabajo(int idSucursal) {
        ArrayList listPuestoTrabajo = new ArrayList();
        String query = "SELECT `idPuestoTrabajo`, `nombrePuestoTrabajo`, `salario`, `FK_idSucursal` FROM puestoTrabajo INNER JOIN sucursal ON (sucursal.idSucursal = puestoTrabajo.FK_idSucursal) WHERE sucursal.idSucursal = ?;";
        try {
            connection = conexion.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, idSucursal);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosDB = new DatosSucursalPuestoTrabajo();
                datosDB.setIdPuestoTrabajo(rs.getInt("idPuestoTrabajo"));
                datosDB.setNombrePuestoTrabajo(rs.getString("nombrePuestoTrabajo"));
                datosDB.setSalario(rs.getFloat("salario"));
                listPuestoTrabajo.add(datosDB);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listPuestoTrabajo;
    }

    public ArrayList<DatosSucursalPuestoTrabajo> getSucursales() {
        ArrayList listPuestoTrabajo = new ArrayList();
        String query = "SELECT `idSucursal`, `nombreSucursal` FROM sucursal;";
        try {
            connection = conexion.getConnection();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosDB = new DatosSucursalPuestoTrabajo();
                datosDB.setIdSucursal(rs.getInt("idSucursal"));
                datosDB.setNombreSucursal(rs.getString("nombreSucursal"));
                listPuestoTrabajo.add(datosDB);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listPuestoTrabajo;
    }
}
