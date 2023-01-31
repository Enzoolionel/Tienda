package conexionTienda;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class RegistroProductos {
  private JPanel panel1;
  private JComboBox comboNacionalidad;
  private JTextField txtNombreProducto;
  private JTextField txtPrecioProducto;
  private JTextField txtFechaIngreso;
  private JTextField txtFechaVencimiento;
  private JButton btnLimpiarCampos;
  private JButton btnGuardar;




  public RegistroProductos() {

    //METODO PARA INSERTAR DATOS EN LA BASE DE DATOS.
    btnGuardar.addActionListener((e)->{
      try {
        Connection conexion = conectar.conectar();
        PreparedStatement insertar = conexion.prepareStatement("insert into productos values(?,?,?,?,?,?)");

        insertar.setString(1, "0");
        insertar.setString(2, txtNombreProducto.getText());
        insertar.setString(3, txtPrecioProducto.getText());
        insertar.setString(4, comboNacionalidad.getSelectedItem().toString());
        insertar.setString(5, txtFechaIngreso.getText());
        insertar.setString(6, txtFechaVencimiento.getText());
        insertar.executeUpdate();

        JOptionPane.showMessageDialog(null, "Datos registrados!");

        conectar.cerrarConexion();
      }catch (Exception ex){
        JOptionPane.showMessageDialog(null, "error: " + ex);
      }
      });

    //METODO PARA LIMPIAR LOS CAMPOS
    btnLimpiarCampos.addActionListener((e)->{
      txtNombreProducto.setText("");
      txtPrecioProducto.setText("");
      comboNacionalidad.setSelectedIndex(0);
      txtFechaIngreso.setText("");
      txtFechaVencimiento.setText("");

    });
  }

  private JButton getBtnLimpiarCampos;
  Conexion conectar = Conexion.getInstance();



  public static void main(String[] args) {
    JFrame frame = new JFrame("RegistroProductos");
    frame.setContentPane(new RegistroProductos().panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
