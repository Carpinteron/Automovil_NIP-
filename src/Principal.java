
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
            String[][] empleados = {
                {"Ana López", "123456789", "Vendedor", "3101234567", "05-01-2023", "2500000", "3000000"},
                {"Carlos Pérez", "234567890", "Gerente", "3112345678", "15-11-2022", "4000000", "5500000"},
                {"María Rodríguez", "345678901", "Asistente", "3203456789", "10-02-2023", "2000000", "2300000"},
                {"Luis González", "456789012", "Vendedor", "3104567890", "20-03-2023", "2200000", "2800000"},
                {"Laura Martínez", "567890123", "Contador", "3215678901", "08-09-2022", "3500000", "4200000"},
                {"Juan Sánchez", "678901234", "Vendedor", "3106789012", "03-12-2022", "2300000", "3000000"},
                {"Diana Herrera", "789012345", "Recepcionista", "3227890123", "25-04-2023", "1800000", "2000000"},
                {"Andrés Castro", "890123456", "Técnico", "3118901234", "10-01-2023", "2100000", "2500000"},
                {"Sofía Ramírez", "901234567", "Vendedor", "3109012345", "01-03-2023", "2400000", "3200000"},
                {"David Méndez", "012345678", "Asistente", "3210123456", "18-10-2022", "2100000", "2500000"},
                {"Camila Vargas", "123456780", "Vendedor", "3101234567", "15-02-2023", "2600000", "3500000"},
                {"Oscar Ríos", "234567891", "Gerente", "3122345678", "01-09-2022", "4200000", "5800000"},
                {"Elena Mendoza", "345678902", "Asistente", "3223456789", "08-01-2023", "2100000", "2500000"},
                {"Javier Herrera", "456789013", "Vendedor", "3114567890", "10-04-2023", "2300000", "3000000"},
                {"Paula Gómez", "567890124", "Contador", "3205678901", "22-11-2022", "3600000", "4300000"},
                {"Mateo López", "678901235", "Vendedor", "3106789012", "18-12-2022", "2200000", "2800000"},
                {"Isabella Castro", "789012346", "Recepcionista", "3217890123", "10-03-2023", "1900000", "2100000"},
                {"Santiago Ramírez", "890123457", "Técnico", "3128901234", "15-01-2023", "2000000", "2300000"},
                {"Valentina Méndez", "901234568", "Vendedor", "3109012345", "05-03-2023", "2500000", "3300000"},
                {"Andrea Vargas", "012345679", "Asistente", "3210123456", "28-09-2022", "2000000", "2300000"}

            };

            //Agregar datos de la matriz al registro
            for (String[] fila : empleados) {
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

    //02 SUBRUTINA PARA MOSTRAR LOS CAMPOS ARCHIVO EMPLEADOS
    public static void LeerNormal(Scanner sc, String file_name, JTable tabla) {
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

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }
    }

    //03 SUBRUTINA PARA MOSTRAR LOS CAMPOS ORDENADOS DEL ARCHIVO EMPLEADOS 
    public static void LeerOrdenado(Scanner sc, String file_name, JTable tabla, String NoS) {
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
                if (NoS == "Nombre") {
                    // Utilizo la tabla como si fuera una matriz
                    // Ordenamiento burbuja por columna 0 (nombre)
                    int Contador = model.getRowCount();
                    for (int i = 0; i < Contador - 1; i++) {
                        for (int j = 0; j < Contador - i - 1; j++) {
                            if (model.getValueAt(j, 0).toString().compareTo(model.getValueAt(j + 1, 0).toString()) > 0) {
                                // Intercambiar filas en la tabla
                                for (int col = 0; col < model.getColumnCount(); col++) {
                                    Object temp = model.getValueAt(j, col);
                                    model.setValueAt(model.getValueAt(j + 1, col), j, col);
                                    model.setValueAt(temp, j + 1, col);
                                }
                            }
                        }
                    }
                } else {
                    /**ESTO ES INTERESANTE ES ALGO PROPIO DE LA TABLA
                     * TableRowSorter<DefaultTableModel> sorter = new
                     * TableRowSorter<>(model); tabla.setRowSorter(sorter);
                     *
                     * // Ordenar por la columna de salario de mayor a menor int
                     * salarioColumna = 6; sorter.setComparator(salarioColumna,
                     * new Comparator<String>() {
                     *
                     * @Override public int compare(String o1, String o2) {
                     * double salario1 = Double.parseDouble(o1); double salario2
                     * = Double.parseDouble(o2); return Double.compare(salario2,
                     * salario1); } });
                    * *
                     */
                    //Ordenamiento Burbuja
                    int salarioColumna = 6;
                    int contador = model.getRowCount();

                    for (int i = 0; i < contador - 1; i++) {
                        for (int j = 0; j < contador - i - 1; j++) {
                            String salario1Str = model.getValueAt(j, salarioColumna).toString();
                            String salario2Str = model.getValueAt(j + 1, salarioColumna).toString();

                            double salario1 = Double.parseDouble(salario1Str);
                            double salario2 = Double.parseDouble(salario2Str);

                            if (salario1 < salario2) {
                                // Intercambiar filas en el modelo de la tabla
                                for (int col = 0; col < model.getColumnCount(); col++) {
                                    Object temp = model.getValueAt(j, col);
                                    model.setValueAt(model.getValueAt(j + 1, col), j, col);
                                    model.setValueAt(temp, j + 1, col);
                                }
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

    //04 HOLA SE ME OLVIDO COMO USAR BOTTON GROUP 
    public void Verificar() {
        //ordenar por salario o nombre
        Scanner sc = new Scanner(System.in);
        if (BotonOrdenar.isSelected()) {
            LeerOrdenado(sc, "Empleados", TablaEMPLEADOS, "Nombre");
            sc.close();
        } else if (BotonOrdenarSalario.isSelected()) {
            LeerOrdenado(sc, "Empleados", TablaEMPLEADOS, "Salario");
            sc.close();
        } else {
            LeerNormal(sc, "Empleados", TablaEMPLEADOS);
            sc.close();
        }
    }

    //04 Subrutina para Agregar Datos (si quieren se puede hacer así)
    public void LlenarNuevosIngresos(String file_name) {
        /**
         * (Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso,
         * salario fijo mensual y salario más comisiones *
         */
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
        if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty() && !c4.isEmpty() && !c5.isEmpty() && !c6.isEmpty() && !c7.isEmpty()) {
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
        PanelEmpleados.setVisible(false);
        PanelInventario.setVisible(false);
        PanelVentas.setVisible(true);
        PanelEmpleados.setEnabled(false);
        PanelVentas.setEnabled(true);
        PanelInventario.setEnabled(false);
        Actual = PanelVentas;
        //ARCHIVO EMPLEADOS
        //Crear
        agregarDatosEmpleados("Empleados");
        //Mostrar Archivo Empleados
        Scanner sc = new Scanner(System.in);
        LeerNormal(sc, "Empleados", TablaEMPLEADOS);
        sc.close();

    }

    public static void CambiaEstadoPANEL(JPanel p) {
        p.setVisible(!p.isVisible());
        p.setEnabled(!p.isEnabled());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Boton_Empleados = new javax.swing.JButton();
        Boton_Ventas = new javax.swing.JButton();
        Boton_Inventario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        PanelVentas = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaVENTAS = new javax.swing.JTable();
        PanelInventario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PanelEmpleados = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEMPLEADOS = new javax.swing.JTable();
        BotonOrdenar = new javax.swing.JRadioButton();
        BotonOrdenarSalario = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png"))); // NOI18N
        Boton_Empleados.setBorderPainted(false);
        Boton_Empleados.setContentAreaFilled(false);
        Boton_Empleados.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x63 .png"))); // NOI18N
        Boton_Empleados.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x63 .png"))); // NOI18N
        Boton_Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_EmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 70, 60));

        Boton_Ventas.setText("V");
        Boton_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_VentasActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 53, 50));

        Boton_Inventario.setText("I");
        Boton_Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_InventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 53, 51));

        jButton1.setText("N");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 53, 53));

        jButton2.setText("Info.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 60, 53));

        PanelVentas.setOpaque(false);
        PanelVentas.setPreferredSize(new java.awt.Dimension(1240, 700));

        jLabel4.setFont(new java.awt.Font("SWGothe", 0, 24)); // NOI18N
        jLabel4.setText("|          Ventas");

        TablaVENTAS.setBackground(new java.awt.Color(255, 204, 204));
        TablaVENTAS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TablaVENTAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Vendedor", "C.C.", "Tipo Auto", "Codigo", "Monto de Venta", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVENTAS.setColumnSelectionAllowed(true);
        TablaVENTAS.setDoubleBuffered(true);
        TablaVENTAS.setGridColor(new java.awt.Color(102, 0, 0));
        TablaVENTAS.setRequestFocusEnabled(false);
        TablaVENTAS.setRowHeight(40);
        TablaVENTAS.setSelectionBackground(new java.awt.Color(255, 153, 153));
        TablaVENTAS.setSelectionForeground(new java.awt.Color(153, 0, 0));
        TablaVENTAS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaVENTAS.setShowGrid(true);
        jScrollPane3.setViewportView(TablaVENTAS);
        TablaVENTAS.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaVENTAS.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVentasLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel4))
                    .addGroup(PanelVentasLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        getContentPane().add(PanelVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1160, 700));

        PanelInventario.setOpaque(false);
        PanelInventario.setPreferredSize(new java.awt.Dimension(1240, 700));

        jLabel3.setFont(new java.awt.Font("SWGothe", 0, 24)); // NOI18N
        jLabel3.setText("|      Inventario");

        javax.swing.GroupLayout PanelInventarioLayout = new javax.swing.GroupLayout(PanelInventario);
        PanelInventario.setLayout(PanelInventarioLayout);
        PanelInventarioLayout.setHorizontalGroup(
            PanelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInventarioLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel3)
                .addContainerGap(899, Short.MAX_VALUE))
        );
        PanelInventarioLayout.setVerticalGroup(
            PanelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInventarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addContainerGap(653, Short.MAX_VALUE))
        );

        getContentPane().add(PanelInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1160, 700));

        PanelEmpleados.setBackground(new java.awt.Color(239, 239, 239));
        PanelEmpleados.setOpaque(false);
        PanelEmpleados.setPreferredSize(new java.awt.Dimension(1240, 700));
        PanelEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SWGothe", 0, 24)); // NOI18N
        jLabel1.setText("|      Empleados");
        PanelEmpleados.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 15, -1, -1));

        TablaEMPLEADOS.setBackground(new java.awt.Color(255, 204, 204));
        TablaEMPLEADOS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TablaEMPLEADOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TablaEMPLEADOS.setForeground(new java.awt.Color(255, 153, 51));
        TablaEMPLEADOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "C.C.", "Cargo", "Telf. Contacto", "Fecha Ingreso", "Salario Mensual", "Salario+Comisiones", "Seleccionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaEMPLEADOS.setColumnSelectionAllowed(true);
        TablaEMPLEADOS.setGridColor(new java.awt.Color(102, 0, 0));
        TablaEMPLEADOS.setRowHeight(40);
        TablaEMPLEADOS.setSelectionBackground(new java.awt.Color(255, 153, 153));
        TablaEMPLEADOS.setSelectionForeground(new java.awt.Color(102, 0, 0));
        TablaEMPLEADOS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaEMPLEADOS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaEMPLEADOS.setShowGrid(true);
        jScrollPane1.setViewportView(TablaEMPLEADOS);
        TablaEMPLEADOS.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        PanelEmpleados.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 244, 1101, -1));

        BotonOrdenar.setText("Ordenar por nombre");
        BotonOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(992, 189, -1, -1));

        BotonOrdenarSalario.setText("Ordenar por Salario");
        BotonOrdenarSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarSalarioActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonOrdenarSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, -1, -1));

        getContentPane().add(PanelEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 1160, 700));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 36)); // NOI18N
        jLabel2.setText("ARCHEY");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    JPanel Actual;

    private void Boton_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_EmpleadosActionPerformed

        if (Actual != PanelEmpleados) {
            PanelEmpleados.setVisible(true);
            PanelEmpleados.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual
                    = PanelEmpleados; //System.out.println(Actual); } *
        }
        /**
         * *
         * PanelVentas.setVisible(false); PanelInventario.setVisible(false);
         * PanelEmpleados.setVisible(true); *
         */


    }//GEN-LAST:event_Boton_EmpleadosActionPerformed

    private void Boton_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_VentasActionPerformed
        if (Actual != PanelVentas) {
            PanelVentas.setVisible(true);
            PanelVentas.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelVentas;
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_VentasActionPerformed

    private void Boton_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_InventarioActionPerformed
        if (Actual != PanelInventario) {
            PanelInventario.setVisible(true);
            PanelInventario.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelInventario;
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_InventarioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BotonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarActionPerformed
        Verificar();
    }//GEN-LAST:event_BotonOrdenarActionPerformed

    private void BotonOrdenarSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarSalarioActionPerformed
        Verificar();
    }//GEN-LAST:event_BotonOrdenarSalarioActionPerformed

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
    private javax.swing.JRadioButton BotonOrdenar;
    private javax.swing.JRadioButton BotonOrdenarSalario;
    private javax.swing.JButton Boton_Empleados;
    private javax.swing.JButton Boton_Inventario;
    private javax.swing.JButton Boton_Ventas;
    private javax.swing.JPanel PanelEmpleados;
    private javax.swing.JPanel PanelInventario;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JTable TablaEMPLEADOS;
    private javax.swing.JTable TablaVENTAS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
