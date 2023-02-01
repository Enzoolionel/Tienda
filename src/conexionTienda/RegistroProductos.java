package conexionTienda;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RegistroProductos {
  private JPanel panel1;
  private JComboBox comboNacionalidad;
  private JTextField txtNombreProducto;
  private JTextField txtPrecioProducto;
  private JTextField txtFechaIngreso;
  private JTextField txtFechaVencimiento;
  private JButton btnLimpiarCampos;
  private JButton btnGuardar;
  private JTextArea txtAreaInfo;
  private JButton btnConsultar;
  private JButton btnHayDatos;


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

    //BTN QUE VERIFICA SI HAY DATOS EN LA BD
    btnHayDatos.addActionListener((e)->{
      try {
        //GUARDAMOS EN VARIABLE LA CONEXION A LA BASE DE DATOS
        Connection conexion = conectar.conectar();

        /*EN LA VARIABLE SELECCIONAR LA IGUALAMOS A LA VARIABLE QUE TIENE LA CONEXION Y LE PASAMOS PREPARESTATEMENT
        Y ENTRE COMILLAS LE PASAMOS SELECT(seleccionar) (todo) FROM(de) Y LE PASO EL NOMBRE DE LA TABLA*/
        PreparedStatement seleccionar = conexion.prepareStatement("SELECT * FROM productos");

        //EL EXECUTEQUERY SIRVE PARA REALIZAR LA CONSULTA A LA BD Y DEVUELVE UN CONJUNTO DE DATOS
        ResultSet consulta = seleccionar.executeQuery();

        if (consulta.next()){
          JOptionPane.showMessageDialog(null,"Hay datos!");
        }else{
          JOptionPane.showMessageDialog(null, "No hay datos!");
        }
        conectar.cerrarConexion();
      }catch (Exception ex){
        JOptionPane.showMessageDialog(null,"error: " + ex);
      }
    });


    btnConsultar.addActionListener((e)->{

      txtAreaInfo.setText(" ");

      try{

        Connection conexion = conectar.conectar();
        PreparedStatement seleccionar = conexion.prepareStatement("SELECT * FROM productos");
        ResultSet consulta = seleccionar.executeQuery();

        while (consulta.next()){
          txtAreaInfo.append(consulta.getString(1));
          txtAreaInfo.append("      ");
          txtAreaInfo.append(consulta.getString(2));
          txtAreaInfo.append("      ");
          txtAreaInfo.append(consulta.getString(3));
          txtAreaInfo.append("      ");
          txtAreaInfo.append(consulta.getString(4));
          txtAreaInfo.append("      ");
          txtAreaInfo.append(consulta.getString(5));
          txtAreaInfo.append("      ");
          txtAreaInfo.append(consulta.getString(6));
          txtAreaInfo.append("\n");
        }

        conectar.cerrarConexion();
      }catch (Exception ex){
        JOptionPane.showMessageDialog(null,"error: " + ex);
      }
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
