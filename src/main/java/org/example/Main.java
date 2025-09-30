package org.example;

import org.example.Controller.ProductController;
import org.example.Database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    
                    1.Agregar Producto.
                    2.Obtener todos los productos.
                    3.Modificar producto.
                    4.Borrar producto.
                    5.Salir.
                    """);
            // Si el usuario presiona Cancelar o cierra el diálogo
            if (option == null) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Seguro que deseas salir?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    break; // Salir del programa
                } else {
                    continue; // Volver a mostrar el menú
                }
            }


            switch (option){
                case "1":
                    ProductController.create();
                    break;
                case "2":
                    ProductController.getAll();
                    break;
                case "3":
                    ProductController.update();
                    break;
                case "4":
                    ProductController.delete();
                    break;
                case "5":
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opcion invalida");
                    break;
            }
        }while (!option.equals("5"));
    }
}