<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Insertar Registros</h1>
	<form method="GET" action="ControladorProductos">
	<input type="hidden" name="instruccion" value="actualizarBBDD">
	<input type="hidden" name="CArt" value="${ ProductoActualizar.nombre}">
	<label>Nombre<input type="text" name="nombre" value="${ ProductoActualizar.nombre}">
	</label>
	<br>
	<label>Precio<input type="number" name="precio" value="${ ProductoActualizar.precio}">
	</label>
	<br>
	<input type="submit" value="Actualizar">
	</form>
</body>
</html>