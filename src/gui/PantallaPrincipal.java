/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import dto.Cliente;
import javax.swing.table.DefaultTableModel;
import dto.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;


/**
 *
 * @author konatasht
 */
public class PantallaPrincipal extends javax.swing.JFrame {
    
    private List<Cliente> listaClientes = new ArrayList<>();
    private static final String ARCHIVO_DATOS = "clientes.dat";
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName());
    private TableRowSorter<DefaultTableModel> sorter;
   
    
    
    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        initComponents(); // generado por NetBeans
        inicializarTabla(); // SIEMPRE después de initComponents()
        configurarOrdenacion(); // Configurar ordenación
        

    }
    
     private void configurarOrdenacion() {
        // Obtener el modelo de la tabla
    DefaultTableModel dtm = (DefaultTableModel) clientes.getModel();
    
    // Crear el TableRowSorter
    sorter = new TableRowSorter<>(dtm);
    
    // Asignar el sorter a la tabla
    clientes.setRowSorter(sorter);
    
    // Permitir ordenación en todas las columnas
    for (int i = 0; i < dtm.getColumnCount(); i++) {
        sorter.setSortable(i, true);
    }
    
    // Configurar que se pueda hacer clic en los encabezados para ordenar
    clientes.setAutoCreateRowSorter(true);
}

   
     
private void filtrarTabla() {
    String texto = campoBusqueda.getText().trim();
    
    if (texto.length() == 0) {
        // Si no hay texto, quitar el filtro
        sorter.setRowFilter(null);
        btnBuscar.setText("Buscar");
    } else {
        try {
            // Crear filtro para buscar en la columna 0 (Nombre)
            // (?i) = ignorar mayúsculas/minúsculas
            RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + texto, 0);
            sorter.setRowFilter(rf);
            
            // Mostrar cuántos resultados hay
            int resultados = clientes.getRowCount();
            btnBuscar.setText("Buscar (" + resultados + ")");
            
        } catch (java.util.regex.PatternSyntaxException e) {
            // Si el usuario escribe caracteres inválidos para regex
            javax.swing.JOptionPane.showMessageDialog(this,
                "Texto de búsqueda no válido",
                "Error de búsqueda",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
  }
    
    private void inicializarTabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"Nombre", "Apellidos", "Fecha Alta", "Provincia"});
        clientes.setModel(dtm); // 'clientes' es la JTable del diseñador
 }

    public void anadirCliente(Cliente cliente) {
        // Añadir a la lista
    listaClientes.add(cliente);
    
    // Añadir a la tabla
    DefaultTableModel dtm = (DefaultTableModel) clientes.getModel();
    dtm.addRow(cliente.toArrayString());
    
    // NO guardar automáticamente

 }

    public void guardarDatos(){
         try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(ARCHIVO_DATOS))) {
        oos.writeObject(listaClientes);
        System.out.println("Guardados " + listaClientes.size() + " clientes");
    } catch (IOException e) {
        logger.log(java.util.logging.Level.SEVERE, "Error al guardar datos", e);
        // Añadir mensaje al usuario
        javax.swing.JOptionPane.showMessageDialog(this,
            "Error al guardar los datos: " + e.getMessage(),
            "Error de guardado",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }
    
    private void cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
    
    if (!archivo.exists()) {
        System.out.println("Archivo de datos no encontrado.");
        return;
    }
    
    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(ARCHIVO_DATOS))) {
        
        List<Cliente> clientesGuardados = (List<Cliente>) ois.readObject();
        
        // Limpiar primero
        DefaultTableModel dtm = (DefaultTableModel) clientes.getModel();
        dtm.setRowCount(0);
        listaClientes.clear();
        
        // Añadir todos
        for (Cliente cliente : clientesGuardados) {
            listaClientes.add(cliente);
            dtm.addRow(cliente.toArrayString());
        }
        
        System.out.println("Cargados " + listaClientes.size() + " clientes desde archivo");
        
    } catch (IOException | ClassNotFoundException e) {
        logger.log(java.util.logging.Level.SEVERE, "Error al cargar datos", e);
    }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPanel1 = new javax.swing.JScrollPane();
        clientes = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        campoBusqueda = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        alta = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPanel1.setViewportView(clientes);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Clients");

        alta.setText("Alta...");
        alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaActionPerformed(evt);
            }
        });
        jMenu3.add(alta);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar)
                            .addComponent(btnCargar))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(btnBuscar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(campoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnBuscar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnEliminar)))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaActionPerformed
       DialogoAlta dialogoAlta = new DialogoAlta(this, true); // true = modal
        dialogoAlta.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_altaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int selectedRow = clientes.getSelectedRow();
    
    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Por favor, seleccione un cliente para eliminar.",
            "Sin selección",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return; // IMPORTANTE: añade este return
    }
    
    // Obtener datos del cliente para el mensaje
    DefaultTableModel dtm = (DefaultTableModel) clientes.getModel();
    String nombre = (String) dtm.getValueAt(selectedRow, 0);
    String apellidos = (String) dtm.getValueAt(selectedRow, 1);
    
    int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "¿Está seguro de que desea eliminar a " + nombre + " " + apellidos + "?",
        "Confirmar eliminación",
        javax.swing.JOptionPane.YES_NO_OPTION);
    
    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        // Eliminar SOLO de la tabla y de la lista en memoria
        // PERO NO guardar en el archivo
        dtm.removeRow(selectedRow);
        listaClientes.remove(selectedRow);
        
        // NO llamar a guardarDatos() aquí
        // guardarDatos(); // <-- COMENTA o ELIMINA esta línea
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "Cliente eliminado de la lista (pero no del archivo guardado).\n" +
            "Use 'Guardar' para sobrescribir el archivo.",
            "Eliminación completada",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         guardarDatos(); // Ya guarda en "clientes.dat"
    javax.swing.JOptionPane.showMessageDialog(this,
        "Clientes guardados en el archivo por defecto.\n" +
        "Total: " + listaClientes.size() + " clientes",
        "Guardado exitoso",
        javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
         // Preguntar si quiere perder cambios no guardados
    if (!listaClientes.isEmpty()) {
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
            "Al cargar se perderán los cambios no guardados.\n" +
            "¿Desea continuar?",
            "Confirmar carga",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE);
            
        if (respuesta != javax.swing.JOptionPane.YES_OPTION) {
            return; // Cancelar si el usuario dice NO
        }
    }
    
    // Cargar desde el archivo
    File archivo = new File(ARCHIVO_DATOS);
    
    if (!archivo.exists()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "No hay archivo guardado.\n" +
            "Primero guarde algunos clientes.",
            "Archivo no encontrado",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(ARCHIVO_DATOS))) {
        
        // Leer la lista desde el archivo
        List<Cliente> clientesGuardados = (List<Cliente>) ois.readObject();
        
        // Limpiar la tabla actual
        DefaultTableModel dtm = (DefaultTableModel) clientes.getModel();
        dtm.setRowCount(0);
        
        // Limpiar la lista actual
        listaClientes.clear();
        
        // Añadir los clientes guardados
        for (Cliente cliente : clientesGuardados) {
            listaClientes.add(cliente);
            dtm.addRow(cliente.toArrayString());
        }
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "Clientes cargados desde el archivo.\n" +
            "Total cargados: " + listaClientes.size() + " clientes",
            "Carga exitosa",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
    } catch (IOException | ClassNotFoundException e) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Error al cargar el archivo: " + e.getMessage(),
            "Error de carga",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        filtrarTabla();
    
    // Opcional: Mostrar cuántos resultados hay
    int resultados = clientes.getRowCount();
    if (campoBusqueda.getText().trim().isEmpty()) {
        btnBuscar.setText("Buscar");
    } else {
        btnBuscar.setText("Buscar (" + resultados + ")");
    }
// TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed
  
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PantallaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem alta;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField campoBusqueda;
    private javax.swing.JTable clientes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPanel1;
    // End of variables declaration//GEN-END:variables

}
