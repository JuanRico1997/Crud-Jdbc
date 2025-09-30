package org.example.Model;

import org.example.Database.CRUD;
import org.example.Database.ConfigDB;
import org.example.Entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements CRUD {


    @Override
    public Object insert(Object obj) {
        //Abrir conexion
        Connection objConnection = ConfigDB.openConnection();

        //Convertir objeto abstracto en coder
        Product objProduct = (Product) obj;

        try {
            String sql = "INSERT INTO products (nombre,precio,stock) VALUES (?,?,?)";

            // Preparar el statement para ingresar los datos, ademas agregar el return generated keys para que devuelva el id generado por la bd
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar el valor a los ? ? ?
            objPrepare.setString(1, objProduct.getNombre());
            objPrepare.setDouble(2, objProduct.getPrecio());
            objPrepare.setInt(3, objProduct.getStock());

            //Ejecutar la query
            objPrepare.execute();

            //Obtener resultados con los id generados
            ResultSet objRest = objPrepare.getGeneratedKeys();
            while (objRest.next()) {
                objProduct.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objProduct;
    }

    @Override
    public java.util.List<Object> findAll() {
        //Para guardar los products de la bd
        List<Object> listProducts = new ArrayList<>();

        //Abrir conexion
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Hacemos la sentencia sql
            String sql = "SELECT * FROM products";
            //Usamos el preparedStatemend que me permite hacer la consulta
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //Ejecutamos la query y lo guardamos en una variable
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                //Crear product para poder agregarlo a la lista
                Product objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setNombre(objResult.getString("nombre"));
                objProduct.setPrecio(objResult.getDouble("precio"));
                objProduct.setStock(objResult.getInt("stock"));
                //Agregamos cada producto a la lista de productos
                listProducts.add(objProduct);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return listProducts;
    }

    @Override
    public boolean update(Object obj) {
        //Hacer la conexion con la bd
        Connection objConnection = ConfigDB.openConnection();
        //Convertir el objeto  generico en un producto
        Product objProduct = (Product) obj;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE products SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Asignar el valor a los parametros de la query
            objPrepare.setString(1, objProduct.getNombre());
            objPrepare.setDouble(2, objProduct.getPrecio());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setInt(4, objProduct.getId());

            //Guardamos el resultado de la bd para verificar si si hubo modificaciones en las columnas
            int result = objPrepare.executeUpdate();

            //Si hubo mas de una columna modificada significa que fue exitoso
            if (result > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "El producto fue modificado");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //Convertir el objeto en un producto
        Product objProduct = (Product) obj;
        //Abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Paso el id del producto que nos pasaron por parametros para eliminarlo
            objPrepare.setInt(1, objProduct.getId());

            //Obtengo cuantas columnas fueron afectadas
            int result = objPrepare.executeUpdate();

            //Si mas de una columna fue modificada (eliminada) eso significa que fue eliminado
            if (result > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El producto fue eliminado");
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Product findById(int id) {
        //Abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        //Empezamos con el product null
        Product objProduct = null;

        try {
            //Hacemos la sentencia sql
            String sql = "SELECT * FROM products WHERE id = ?";
            //Usamos el preparedStatemend que me permite hacer la consulta
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            //Ejecutamos la query y lo guardamos en una variable
            ResultSet objResult = objPrepare.executeQuery();

            //Verificamos si hay resultado y empezamos a llenar nuestro producto para retornarlo
            if (objResult.next()) {
                objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setNombre(objResult.getString("nombre"));
                objProduct.setPrecio(objResult.getDouble("precio"));
                objProduct.setStock(objResult.getInt("stock"));

            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objProduct;
    }
}