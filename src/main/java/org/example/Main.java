package org.example;

import org.example.Controller.ProductController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option;

        do {
            // ✅ Mostrar el menú completo
            option = JOptionPane.showInputDialog(
                    null,
                    """
                    Seleccione una opción:
                    
                    1. Agregar Producto
                    2. Obtener todos los productos
                    3. Modificar producto
                    4. Borrar producto
                    5. Salir
                    """,
                    "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE
            );

            // ✅ Si el usuario presionó Cancelar o cerró la ventana
            if (option == null) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Seguro que deseas salir?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    break; // salir del bucle → termina el programa
                } else {
                    continue; // volver a mostrar el menú
                }
            }

            // ✅ Validar la opción ingresada
            switch (option) {
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
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida, intenta de nuevo.");
                    break;
            }

        } while (!"5".equals(option)); // ✅ condición segura (no se rompe si option es null)
    }
}
