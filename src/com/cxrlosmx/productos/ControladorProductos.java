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
		//Simplificamos metodos
		
		//1.-Leer el parametro del formulario
		String comando=request.getParameter("instruccion");
		
		//2.-Si no se envia el parametro listar productos
		if(comando==null) {
			comando="listar";
		}
		
		//3.-Redirigir el flujo de ejecuciòn al mètodo adecuado
		switch(comando) {
		case "listar":{
			obtenerProductos(request, response);
			break;
		}
		
		case "intruccion":{
			agregarProducto(request, response);
			break;
		}
		
		default:{
			obtenerProductos(request, response);
		}
		
		}
		
		
		
		
	}



	private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
		//Pasos
		
		//1.-Leer la informacion del producto que viene del formulario
		String nombre=request.getParameter("nombre");
		int precio=Integer.parseInt(request.getParameter("nombre"));
		//2.-Crear un objeto de tipo producto
		Productos producto=new Productos(nombre,precio);
		//3.-Enviar el objeto al modelo e insertar el objeto producto en la BBDD
		modeloProductos.agregarElProducto(producto);
		//4.-Volver a listar la tabla de producto
		obtenerProductos(request, response);
		
	}



	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
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
