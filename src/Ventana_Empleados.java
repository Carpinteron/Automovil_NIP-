

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Natalia Carpintero, Paula Nuñez e Isabella Arrieta
 */
public class Ventana_Empleados extends javax.swing.JFrame {

    /**
     * INSTRUCCION 1: PARA EMPLEADOS (DESPUES BORRAR)
     * Los datos de sus empleados (Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso, salario fijo mensual y salario más comisiones)
     * se encuentran en un archivo de texto llamado Empleados, sin ningún orden específico.
     
     * Restricciones: La cantidad mínima de empleados es de 20. 
     
     * Requerimientos funcionales: 
     * Desarrollar un programa a través del cual el usuario (administrador de la empresa) pueda hacer las siguientes operaciones: 
            Agregar un nuevo registro en el archivo de Empleados. 
            Eliminar un registro en el archivo de Empleados. 
            Ordenar el archivo de Empleados por Nombre o por salario. 
            Actualizar el campo salario más comisiones en el archivo de Empleados, teniendo en cuenta que si realizó una venta superior a $30 millones en el mes, recibirá un 2% de comisiones sobre la venta total. 
     
     * Requerimientos no funcionales
            Tener un manejo adecuado de errores cuando se presenten los siguientes casos: “archivo no encontrado”, “archivo con registros errados” y
            los que usted considere. No ingresar un registro si contiene algún error: por ejemplo, una cédula mal digitada, un saldo negativo o 
            un campo vacío.  

            Validaciones. En los casos que se quiera agregar un nuevo registro, se debe validar que el empleado (o el código del auto en el 
            caso del archivo de Ventas) no exista en los registros actuales. Mostrar un mensaje al usuario que advierta que ya existe ese registro. 
            En los casos que se quiera eliminar un registro, se debe verificar que el registro existe en el archivo. 

            Código comentado adecuadamente sobre los procedimientos y estructuras utilizadas. 

     */
    public static void CrearRegistroEmpleados(Scanner sc, String file_name){
        String Nombre, Cedula, Cargo, Telefono, Fecha_ingreso, Salario_fijo, Salario_comisiones;
       
        try {
            
            FileWriter outFile = new FileWriter("Empleados" + ".txt", false);  //Archivo: Empleados.txt
            // if false the file will be deleted and created everytime
            // if true the registers will be appended to the end of the file
            PrintWriter registrar_empleados = new PrintWriter(outFile);
            
            // LOGICA
            String hay_empleado;
            System.out.println("Hay mas empleados? si - no");
            hay_empleado = sc.nextLine();
            while(hay_empleado.equalsIgnoreCase("si")){
                System.out.println("Campo 1");
                Nombre = sc.nextLine();
                System.out.println("Campo 2");
                Cedula = sc.nextLine();
                System.out.println("Campo 3");
                Cargo = sc.nextLine();
                System.out.println("Campo 4");
                Telefono = sc.nextLine();
                System.out.println("Campo 5");
                Fecha_ingreso = sc.nextLine();
                System.out.println("Campo 6");
                Salario_fijo = sc.nextLine();
                System.out.println("Campo 7");
                Salario_comisiones = sc.nextLine();
                
                //Validaciones 
                while(Double.parseDouble(Cedula) < 0){
                    System.out.println("Cedula debe ser positiva");
                    Cedula = sc.nextLine();
                }
                while(Double.parseDouble(Salario_fijo) < 0){
                    System.out.println("Salario debe ser positivo");
                    Salario_fijo = sc.nextLine();
                }
                while(Double.parseDouble(Salario_comisiones) < 0){
                    System.out.println("Salario debe ser positivo");
                    Salario_comisiones = sc.nextLine();
                }
                if (!Nombre.isEmpty() && !Cedula.isEmpty() && !Cargo.isEmpty() && !Telefono.isEmpty() && !Fecha_ingreso.isEmpty() && !Salario_fijo.isEmpty() && !Salario_comisiones.isEmpty()){
                    registrar_empleados.println(Nombre +"\t"+ Cedula +"\t"+ Cargo +"\t"+ Telefono +"\t"+ Fecha_ingreso+"\t"+ Salario_fijo+"\t"+ Salario_comisiones);
                }
                System.out.println("Hay mas registros? si - no");
                hay_empleado = sc.nextLine();  
            }
           registrar_empleados.close();
        } catch (IOException ex) {
            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }
    
    }
    
    public Ventana_Empleados() {
        initComponents();
        this.setLocationRelativeTo(null);//centrar ventana
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Boton_Menu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Boton_Menu.setText("Volver al menú");
        Boton_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_MenuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SWGothe", 0, 24)); // NOI18N
        jLabel2.setText("Empleados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(658, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(521, 521, 521))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Boton_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(98, 98, 98)
                .addComponent(Boton_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(482, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_MenuActionPerformed
        Principal m = new Principal();
        this.dispose();
        m.setVisible(true);
    }//GEN-LAST:event_Boton_MenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Menu;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
