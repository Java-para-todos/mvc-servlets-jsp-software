package com.cxrlosmx.productos;

import java.sql.Connection;
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
}
