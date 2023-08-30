
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Natalia Carpintero, Paula Nuñez e Isabella Arrieta
 */
public class Principal extends javax.swing.JFrame {

    //SUBRUTINAS
    //01 Subrutina para llenar el archivo Empleados
    /**
     * * INSTRUCCION 1: PARA EMPLEADOS (DESPUES BORRAR) Los datos de sus
     * empleados (Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso,
     * salario fijo mensual y salario más comisiones) se encuentran en un
     * archivo de texto llamado Empleados, sin ningún orden específico.
     */
    public static void agregarDatosEmpleados(String file_name) {
        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", false);
            PrintWriter registro = new PrintWriter(outFile);

            //Matriz para crear Archivo Existente 
            //Aqui poner los datos
            String[][] datos = {};
            //Agregar datos de la matriz al registro
            for (String[] fila : datos) {
                String Nombre = fila[0];
                String Cedula = fila[1];
                String Cargo = fila[2];
                String Telefono = fila[3];
                String FechaIngreso = fila[4];
                String SalarioFijo = fila[5];
                String SalarioComisiones = fila[6];
                // Agregar los datos al archivo
                registro.println(Nombre + "\t" + Cedula + "\t" + Cargo + "\t" + Telefono + "\t" + FechaIngreso + "\t" + SalarioFijo + "\t" + SalarioComisiones);

            }

            registro.close();
            System.out.println("Datos agregados exitosamente al archivo.");

        } catch (IOException ex) {
            System.out.println("Error al agregar datos al archivo");
            ex.printStackTrace();
        }
    }

    //02 SUBRUTINA PARA MOSTRAR LOS CAMPOS ORDENADOS DEL ARCHIVO EMPLEADOS (si quieren se puede hacer asi)
    public static void Leer(Scanner sc, String file_name, JTable tabla) {
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");
                    model.addRow(temp); //Agregar datos del archivo a la tabla
                }

                br.close();
                hay = true;
                //Utilizo la tabla como si fuera una matriz
                // Ordenamiento burbuja por nombre (columna 1)
                int ContadorFila = model.getRowCount();
                for (int i = 0; i < ContadorFila - 1; i++) {
                    for (int j = 0; j < ContadorFila - i - 1; j++) {
                        if (model.getValueAt(j, 1).toString().compareTo(model.getValueAt(j + 1, 1).toString()) > 0) {
                            // Intercambiar filas en la tabla
                            for (int col = 0; col < model.getColumnCount(); col++) {
                                Object temp = model.getValueAt(j, col);
                                model.setValueAt(model.getValueAt(j + 1, col), j, col);
                                model.setValueAt(temp, j + 1, col);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }
    }
    
    //04 Subrutina para Agregar Datos (si quieren se puede hacer así)
    public void LlenarNuevosIngresos(String file_name) {
        /**(Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso,
     * salario fijo mensual y salario más comisiones
     ***/
        String Nombre, Cedula, Cargo, Telefono, FechaIngreso, SalarioFijo, SalarioComisiones;

        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", true);  //Archivo.txt
            // if false the file will be deleted and created everytime
            // if true the registers will be appended to the end of the file
            PrintWriter registrar_empleados = new PrintWriter(outFile);
            // LOGICA
            //AQUI OBTENER LAS CADENAS DE LOS JTEXTFIELD

            //Llamar a funciones de validaciones
            //Validacion1: verifica que no haya campos vacios
            //Validacion2: verifica que cada campo no tenga algun error de formato
            
            registrar_empleados.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
           
            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }

    }

    //06 SUBRUTINA PARA APLICAR SONIDO
    private void sonido(String cadena) {
        try {
            Clip clip = AudioSystem.getClip();
            URL url = getClass().getResource(cadena);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip.open(audioIn);
            clip.start();

        } catch (Exception e) {
            //System.err.println(e.getMessage());
        }
    }
    //FUNCIONES - Total 2
    // 01 funcion para validar si estan vacios los campos
    public boolean validacion1(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty() && !c4.isEmpty() && !c5.isEmpty() && !c6.isEmpty()&& !c7.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    //02 Funcion para validar 2
    public boolean validacion2(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {

        // Validacion Nombre
        boolean validon = true;
        for (char c : c1.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                validon = false;
                break;
            }
        }
        if (!validon) {
            //error1.setVisible(true); (label que muestra error)
            return false;
        } else {
            //error1.setVisible(false);
        }

        //Validacion Cedula
        boolean validoc = true;
        for (char c : c2.toCharArray()) {
            if (!Character.isDigit(c)) {
                validoc = false;
                break;
            }
        }
        if (!validoc) {
            //error2.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            //error2.setVisible(false); // Ocultar mensaje de error
        }

        //Validacion fecha de ingreso
        try {
            LocalDate fecha = LocalDate.parse(c3); // Intenta analizar la cadena como una fecha
            //error3.setVisible(true);
            return false;
        } catch (DateTimeParseException e) {
            //error3.setVisible(false);
        }

        //Validacion telefono de contacto
        // Verificar si la entrada consiste en números y tiene 10 dígitos
        boolean validonumero = true;
        for (char c : c5.toCharArray()) {
            if (!Character.isDigit(c)) {
                validonumero = false;
                break;
            }
        }

        boolean tiene10Digitos = c5.length() == 10;

        if (validonumero && tiene10Digitos) {
           // error5.setVisible(false);
        } else {
            //error5.setVisible(true);
            return false;
        }

       
        // Validacion salario 
        
        return true;

    }


    public Principal() {
        initComponents();
        //INTERFAZ
        //setIconImage(new ImageIcon(getClass().getResource("Imagenes/icon.png")).getImage());
        //this.setTitle(" ");
        this.setLocationRelativeTo(null); //centrar ventana
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Boton_Empleados = new javax.swing.JButton();
        Boton_Ventas = new javax.swing.JButton();
        Boton_Inventario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SWGothe", 0, 24)); // NOI18N
        jLabel1.setText("Archey - Menú");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(539, 33, -1, -1));

        Boton_Empleados.setText("E");
        Boton_Empleados.setActionCommand("");
        Boton_Empleados.setBorderPainted(false);
        Boton_Empleados.setContentAreaFilled(false);
        Boton_Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_EmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 53, 49));

        Boton_Ventas.setText("V");
        Boton_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_VentasActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 53, 50));

        Boton_Inventario.setText("I");
        Boton_Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_InventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 53, 51));

        jButton1.setText("N");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 53, 53));

        jButton2.setText("Info.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 60, 53));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1240, 700));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1160, 700));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 36)); // NOI18N
        jLabel2.setText("ARCHEY");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_EmpleadosActionPerformed

    }//GEN-LAST:event_Boton_EmpleadosActionPerformed

    private void Boton_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_VentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Boton_VentasActionPerformed

    private void Boton_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_InventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Boton_InventarioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton Boton_Empleados;
    private javax.swing.JButton Boton_Inventario;
    private javax.swing.JButton Boton_Ventas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
