package org.example.Controller;

import org.example.Entity.Product;
import org.example.Model.ProductModel;
import org.example.Utils.InputValidation;

import javax.swing.*;

public class ProductController {

    public static void create(){
        //Usamos el modelo
        ProductModel objProductModel = new ProductModel();

        //Pedir los datos al usuario
        String nombre = InputValidation.leerString("Ingrese el nombre del producto: ");
        if(nombre == null){
            return;
        }
        Double precio = InputValidation.leerDoubleNoNegativo("Ingrese el precio del producto: ");
        if(precio == null){
            return;
        }
        Integer stock = InputValidation.leerEnteroNoNegativo("Ingrese el stock del producto: ");
        if(stock == null){
            return;
        }

        //Creamos el producto para asignarle los datos y luego pasarlo a la funcion
        Product objProduct = new Product();

        //Llenamos con los datos que tenemos ( el id se le pone la bd en la logica del model)
        objProduct.setNombre(nombre);
        objProduct.setPrecio(precio);
        objProduct.setStock(stock);

        //El insert devuelve un objeto generico y por eso los casteamos para que sea tipo producto y objProduct quede con un producto y no con un objeto generico
        objProduct = (Product) objProductModel.insert(objProduct);

        //Mostramos el producto creado
        JOptionPane.showMessageDialog(null,objProduct.toString());
    }

    public static void getAll(){
        //Llamamos al modelo para acceder a los metodos
        ProductModel productModel = new ProductModel();

        //Creamos como un titulo para mostrar todos los coders
        String listProducts = "Lista de productos \n";

        //Recorremos todos los coders
        for (Object i : productModel.findAll()){
            Product objProduct = (Product) i;
            listProducts += objProduct.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listProducts);
    }

    public static void delete() {
        // Llamamos al modelo para acceder a los métodos
        ProductModel productModel = new ProductModel();

        // Mostramos todos los productos antes de pedir el id
        getAll();

        // Pedimos el id del producto
        String entrada = JOptionPane.showInputDialog("Ingrese el id del producto:");
        if (entrada == null) { // usuario canceló
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return; // volver al menú
        }

        entrada = entrada.trim();
        if (entrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El id no puede estar vacío");
            return;
        }

        Integer idDelete;
        try {
            idDelete = Integer.parseInt(entrada);
            if (idDelete < 0) {
                JOptionPane.showMessageDialog(null, "El id no puede ser negativo");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número entero válido");
            return;
        }

        // Buscamos el producto
        Product objProduct = productModel.findById(idDelete);

        // Verificamos si existe
        if (objProduct == null) {
            JOptionPane.showMessageDialog(null, "El producto no fue encontrado");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este producto?");
            if (confirm == JOptionPane.YES_OPTION) {
                productModel.delete(objProduct);
                JOptionPane.showMessageDialog(null, "Producto eliminado con éxito ✅");
            }
        }
    }


    public static void update() {
        // Llamamos al modelo para acceder a los métodos
        ProductModel productModel = new ProductModel();

        // Mostramos todos los productos antes de pedir el id
        getAll();

        // Pedimos el id del producto al usuario
        Integer idUpdate = InputValidation.leerEnteroNoNegativo("Ingrese el id del producto: ");
        if (idUpdate == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return; // cancelar operación
        }

        // Con el id recibido buscamos el producto
        Product objProduct = productModel.findById(idUpdate);

        // Verificar si existe
        if (objProduct == null) {
            JOptionPane.showMessageDialog(null, "El producto no fue encontrado");
            return;
        }

        // ✅ Editar nombre
        String nuevoNombre = JOptionPane.showInputDialog(
                null,
                "Ingrese el nuevo nombre:",
                objProduct.getNombre()
        );
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        // ✅ Editar precio
        String entradaPrecio = JOptionPane.showInputDialog(
                null,
                "Ingrese el nuevo precio:",
                objProduct.getPrecio()
        );
        if (entradaPrecio == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        Double nuevoPrecio;
        try {
            nuevoPrecio = Double.parseDouble(entradaPrecio);
            if (nuevoPrecio < 0) {
                JOptionPane.showMessageDialog(null, "El precio no puede ser negativo");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido para el precio");
            return;
        }

        // ✅ Editar stock
        String entradaStock = JOptionPane.showInputDialog(
                null,
                "Ingrese el nuevo stock:",
                objProduct.getStock()
        );
        if (entradaStock == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        Integer nuevoStock;
        try {
            nuevoStock = Integer.parseInt(entradaStock);
            if (nuevoStock < 0) {
                JOptionPane.showMessageDialog(null, "El stock no puede ser negativo");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número entero válido para el stock");
            return;
        }

        // ✅ Si todo está correcto → aplicar cambios
        objProduct.setNombre(nuevoNombre);
        objProduct.setPrecio(nuevoPrecio);
        objProduct.setStock(nuevoStock);

        // Pasamos el objeto con la nueva información al modelo
        productModel.update(objProduct);

        JOptionPane.showMessageDialog(null, "Producto actualizado con éxito ✅");
    }


}
