package com.cxrlosmx.productos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ModeloProductos {
		
	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos) {
		this.origenDatos=origenDatos;
	}
	
	public List<Productos> getProductos()throws Exception{
		List<Productos> productos=new ArrayList<>();
		
		Connection conexion=null;
		Statement statement=null;
		ResultSet rc=null;
		
		
		//----------Establecer la conexion
		conexion=origenDatos.getConnection();
		//----Crear la sentencia SQL
		String sql="SELECT * FROM producto";
		statement=conexion.createStatement();
		//----Ejecutar esa sentencia SQL
		rc=statement.executeQuery(sql);
		//---Recorrer el ResultSet obtenido
		while(rc.next()) {
			String n=rc.getString("nombre");
			int p=rc.getInt("precio");
			Productos producto=new Productos(n, p);
			productos.add(producto);
		}
		return productos;
		
		
	}
	public void agregarElProducto(Productos producto) {
		//1.-Obtener la conexion
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		
		try {
			//Establecemos la conexiòn
			miConexion=origenDatos.getConnection();
			
		
		//2.-Crear la instruccion SQL para insertar datos
		String instruccionSQL="INSERT INTO PRODUCTO (nombre, precio) VALUES (?,?)";
		miStatement=miConexion.prepareStatement(instruccionSQL);
		//3.-Establecer los parametros para el producto
		miStatement.setString(1,producto.getNombre());
		miStatement.setInt(2, producto.getPrecio());
		
		//4.-Ejecutar la instruccion SQL
		miStatement.execute();
		
		} catch (Exception e) {
			
		}
	}
}
