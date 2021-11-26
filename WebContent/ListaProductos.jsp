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
	</tr>
	
	<c:forEach var="i" items="${LISTAPRODUCTOS}">
		<tr>
		<td>
		${i.nombre}
		</td>
		<td>
		${i.precio}
		</td>
		</tr>
		
	</c:forEach>
	
	</table>
	<div id="contenedor-boton">
		<inout type="button" value="Insertar Registro" onclick="window.location.href='inserta_producto.jsp'"/>
	
	
	</div>

</body>
</html>