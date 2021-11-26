package com.cxrlosmx.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletPruebas
 */
@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//Definir o establecer el DataSource
	@Resource(name="jdbc/Productos")
	
	//Creamos una variable de DataSource
	private DataSource miPool;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Crear el objeto printWritter
		PrintWriter salida=response.getWriter();
		
		response.setContentType("text/plain");
		
		//Crear conexion con BBDD
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultSet=null;
		
		try {
			miConexion=miPool.getConnection();
			
			String sql="SELECT * FROM producto";
			miStatement=miConexion.createStatement();
			miResultSet=miStatement.executeQuery(sql);
			salida.println("Productos");
			while(miResultSet.next()) {
				String nombreArticulo=miResultSet.getString(1);
				salida.println(nombreArticulo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
