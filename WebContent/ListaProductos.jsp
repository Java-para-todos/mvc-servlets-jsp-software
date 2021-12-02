<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
	
	

	<table>
	<tr>
	<td>Nombre</td>
	<td>Precio</td>
	<td>Acción</td>
	</tr>
	
	<c:forEach var="i" items="${LISTAPRODUCTOS}">
	<!-- Link para cada producto con su campo clave -->
		<c:url var="linkTemp" value="ControladorProductos">
		<c:param name="instruccion" value="cargar"></c:param>
		<c:param name="nombre" value="${i.nombre}"></c:param>
		</c:url>
	
		<tr>
		<td>
		${i.nombre}
		</td>
		<td>
		${i.precio}
		</td>
		<td>
		<a href="${linkTemp}">Actualizar</a>
		</td>
		</tr>
		
	</c:forEach>
	
	</table>
	<div id="contenedor-boton">
		<inout type="button" value="Insertar Registro" onclick="window.location.href='inserta_producto.jsp'"/>
	
	
	</div>

</body>
</html>