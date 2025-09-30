package org.example.Utils;

import javax.swing.*;

public class InputValidation {
    public static String leerString(String mensaje){
        while(true){
            String entrada = JOptionPane.showInputDialog(null, mensaje);

            if(entrada == null){
                JOptionPane.showMessageDialog(null, "Operacion cancelada");
                return null;
            }
            entrada = entrada.trim();
            if (entrada.isEmpty()){
                JOptionPane.showMessageDialog(null, "No puede estar vacio");
                continue;
            }
            try {
                Double.parseDouble(entrada);
                JOptionPane.showMessageDialog(null,"No puedes poner un numero");
                continue;
            }catch(NumberFormatException e){
                return entrada;
            }
        }
    }

    public static Integer leerEnteroNoNegativo(String mensaje) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensaje);

            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }

            entrada = entrada.trim();

            if (entrada.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacío");
                continue;
            }

            try {
                int numero = Integer.parseInt(entrada);
                if (numero < 0) {
                    JOptionPane.showMessageDialog(null, "No se permiten números negativos");
                    continue;
                }
                return numero;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número entero válido");
            }
        }
    }

    public static Double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensaje);

            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }

            entrada = entrada.trim();

            if (entrada.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacío");
                continue;
            }

            try {
                double numero = Double.parseDouble(entrada);
                if (numero < 0) {
                    JOptionPane.showMessageDialog(null, "No se permiten números negativos");
                    continue;
                }
                return numero;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número decimal válido");
            }
        }
    }

}
