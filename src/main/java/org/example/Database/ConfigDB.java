package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/TiendaRiwi";
            String user = "root";
            String password = "Qwe.123*";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Base de datos conectada exitosamente");
        } catch (ClassNotFoundException error){
            System.out.println("Driver no instalado "+ error.getMessage());
        } catch (SQLException error){
            System.out.println("Error al conectar la base de datos "+ error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection(){
        try{
            if (objConnection != null){
                objConnection.close();
                System.out.println("Base de datos cerrada exitosamente");
            }
        } catch (SQLException error){
            System.out.println("Error al cerrar la base de datos" + error.getMessage());
        }
    }
}
