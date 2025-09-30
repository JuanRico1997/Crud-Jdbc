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
        double precio = InputValidation.leerDoubleNoNegativo("Ingrese el precio del producto: ");
        int stock = InputValidation.leerEnteroNoNegativo("Ingrese el stock del producto: ");

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

    public static  void delete(){
        //Llamamos al modelo para acceder a los metodos.
        ProductModel productModel = new ProductModel();

        //Llamamos la funcion getAll para traer todos los productos antes de pedir el id
        getAll();

        //Pedimos el id del producto que queremos eliminar
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto:"));

        //Le pasamos el id a la funcion findById para encontrar el producto
        Product objProduct = productModel.findById(idDelete);

        //Verificamos si el coder existe
        if (objProduct == null){
            JOptionPane.showMessageDialog(null,"El producto no fue encontrado");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null,"Estás seguro?");
            if (confirm == 0) productModel.delete(objProduct);
        }
    }

    public static void update(){
        //Llamamos al modelo para acceder a los metodos.
        ProductModel productModel = new ProductModel();

        //Llamamos la funcion getAll para traer todos los productos antes de pedir el id
        getAll();

        //Pedimos el id del coder al usuario
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto:"));

        //Con el id recibido buscamos el producto
        Product objProduct = productModel.findById(idUpdate);

        //Condicional ara verificar si existe
        if (objProduct == null){
            JOptionPane.showMessageDialog(null,"El producto no fue encontrado");
        } else {
            objProduct.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre:",objProduct.getNombre()));
            objProduct.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio:",objProduct.getPrecio())));
            objProduct.setStock(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo stock:",objProduct.getStock())));

            //Pasamos el coder con la nueva informacion al metodo update
            productModel.update(objProduct);
        }

    }


}
