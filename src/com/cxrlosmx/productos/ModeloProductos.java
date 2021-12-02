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

	public Productos getProducto(String nombre) {
		Productos producto=null;
		
		
		try {
		
		//1.-Establecer la conexion
		Connection conexion=origenDatos.getConnection();	
		//2.-Crear el SQL que busque el producto
		String sql="SELECT * FROM productos WHERE nombre=?";
		
		//3.-Crear la consulta
		PreparedStatement statement=conexion.prepareStatement(sql);
		//4.-Establecer los parametros
		statement.setString(1, nombre);
		//5.-Ejecutar la consulta
		ResultSet rc=statement.executeQuery();
		
		//6.-Obtener los datos de respuesta
		if(rc.next()) {
		
			producto=new Productos(nombre, rc.getInt("precio"));
		
		}
		else {
			throw new Exception("No se ha encontrado el producto con el nombre "+nombre);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
				return producto;
	}

	public void actualizarProducto(Productos productoActualizado) {
		// TODO Auto-generated method stub
		
	}
}
