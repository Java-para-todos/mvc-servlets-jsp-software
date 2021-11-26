package com.cxrlosmx.productos;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloProductos modeloProductos;
	
	//Definir o establecer el DataSource
		@Resource(name="jdbc/Productos")
		
		//Creamos una variable de DataSource
		private DataSource miPool;
	
	//Este metodo es como si fuera el mètodo main, desde aqui arranca nuestra aplicacion
	@Override
		public void init() throws ServletException {
			super.init();
			//Le pasamos el pool de conexion
			try {
			modeloProductos=new ModeloProductos(miPool);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtener la lista de productos de la clase Modelo
		List<Productos> listaProductos=null;
		try {
			 listaProductos=modeloProductos.getProductos();
		
		//Agregar la lista de productos al request
		request.setAttribute("LISTAPRODUCTOS", listaProductos);
		
		//Enviar el request a la pagina JSP
		RequestDispatcher miDispatcher=request.getRequestDispatcher("/ListaProductos.jsp");
		
		miDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
