/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import usaclibrary.USACLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import logica.NodoLista;
import logica.tablaHash;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



/**
 *
 * @author cris
 */
public class cargaUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form cargaUsuarios
     */
    /////////////////////////////variables globales/////////////////////////
    public static String rutaFichero = ""; // almacena la ruta del fichero para la carga masiva
    
    
    public cargaUsuarios() {
        initComponents();
        this.setLocationRelativeTo(null); // centra el frame en la pantalla
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtCargarDatos = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("carga_usuarios");
        setMaximumSize(new java.awt.Dimension(750, 450));
        setMinimumSize(new java.awt.Dimension(750, 450));
        setPreferredSize(new java.awt.Dimension(750, 450));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Carga de Usuarios");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 30, 360, 50);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Buscar:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 110, 70, 30);

        jtCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCargarDatosActionPerformed(evt);
            }
        });
        getContentPane().add(jtCargarDatos);
        jtCargarDatos.setBounds(130, 110, 420, 28);

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(560, 110, 110, 28);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 160, 620, 210);

        jButton2.setText("Cargar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(210, 380, 110, 28);

        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(390, 380, 110, 28);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carga.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 760, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCargarDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCargarDatosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new ventanaCargaMasiva().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("rutaFichero: "+rutaFichero);
        leerJson();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser fc = new JFileChooser(); // creacion del objeto JFileChooser que permite buscar archivos .json

        //creando el filtro para solo abrir archivos .json
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".JSON", "json");

        fc.setFileFilter(filtro); // se le agrega el filtro al JFileChooser

        int seleccion = fc.showOpenDialog(this); // hasta que no se cierra la ventana no pase de aqui

        if (seleccion == JFileChooser.APPROVE_OPTION) { //constante de identificacion del fichero

            File fichero = fc.getSelectedFile();

            // aqui en adelante se escribe la ruta del fichero en el jtxtField
            this.jtCargarDatos.setText(fichero.getAbsolutePath()); // getAbsolutePath me optiene la ruta del fichero

            rutaFichero = fichero.getAbsolutePath(); // se guarda en la varible global el string de ruta del fichero

            //lectura del fichero
            try (FileReader fr = new FileReader(fichero)) {

                String cadena = "";
                int valor = fr.read();

                while (valor != -1) {

                    cadena = cadena + (char) valor;
                    valor = fr.read();
                }

                this.jTextArea1.setText(cadena);
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jtCargarDatos;
    // End of variables declaration//GEN-END:variables

public void leerJson()
{    
    //declara objeto de tipo json
    JSONParser parser = new JSONParser();   
    
    try { 
        
        Object obj =parser.parse(new FileReader(rutaFichero));
        JSONObject jsonObject = (JSONObject) obj;
        
        JSONArray usuarios = (JSONArray)    jsonObject.get("Usuarios");
        System.out.println("");
        
        
        //recorrido
        for (int i = 0; i < usuarios.size(); i++) {
            
            
            //paso de los parametros a variables
            JSONObject elemento = (JSONObject) usuarios.get(i);

            String Carnet = String.valueOf(elemento.get("Carnet"));
            System.out.println("Carnet: " +Carnet);            
            String Nombre = String.valueOf(elemento.get("Nombre"));
            System.out.println("Nombre: " +Nombre);
            String Apellido = String.valueOf(elemento.get("Apellido"));
            System.out.println("Apellido: " +Apellido);            
            String Carrera = String.valueOf(elemento.get("Carrera"));
            System.out.println("Carrera: " +Carrera);
            String Password = String.valueOf(elemento.get("Password"));
            System.out.println("Password: " +Password);
            System.out.println("----------------------------------------------------------");
            //quitar guines a Carnet
            //String replace = Carnet.replace("-", "");
            
            //antes de guardar validar que no exista
            //aqui enviar a guardar en tabla Hash
            
            //validar que el carnet no exista ya en el sistema
            if (!USACLibrary.hash.existeUsuario(Carnet)) { // si no existe el carnet registrado ingresar nuevo usuario
              
                USACLibrary.hash.funcionHash(Carnet, Nombre, Apellido, Carrera, Password, USACLibrary.hash.getMd5(Password));
                //JOptionPane.showMessageDialog(this, "Usuario Registrado Exitosamente: "+Carnet, "AVISO", 1);
            }
            else{
                JOptionPane.showMessageDialog(this, "Carnet Ya Registrado: "+Carnet, "ERROR", 3);
            }
            
        }
        System.out.println("******************************************impresion Consola********************************************");
        USACLibrary.hash.graficaConsola();

        
        JOptionPane.showMessageDialog(null, "Carga Exitosa", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        
    }catch (FileNotFoundException e)
    {
        System.out.println("ocurrio un error de archivo");
    }
    catch (IOException e) 
    {
        System.out.println("ocurrio un error io");
    }
    catch (Exception e) 
    {
        System.out.println("ocurrio un error: "+e);
        System.out.println(e);
    }
    
}

}
