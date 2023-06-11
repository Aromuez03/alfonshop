<%@ page import="java.util.List" %>
<%@ page import="AlfonShop.dto.sugerenciaDto" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Sugerencias</title>
    <link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/sugerencia.css">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="navcard">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
	        <div class="container">
	            <a class="navbar-brand" href="/bienvenida">Bienvenida</a>
	            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	            <div class="collapse navbar-collapse" id="navbarNav">
	                <ul class="navbar-nav">
	                    <li class="nav-item">
	                        <a class="nav-link" href="/Catalogo">Catálogo</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/presupuesto">Presupuesto</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/NuevaSugerencia">Nueva Sugerencia</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/BuzonSugerencias">Buzón de Sugerencias</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/usuarios">Usuarios</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/Logout">Logout</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </nav>
    </div>
	<div class="container">
		<h2>Sugerencias</h2><br>
		<% for (sugerenciaDto sugerencia : (List<sugerenciaDto>) request.getAttribute("sugerencias")) { %>
	    <div class="card">
	        <div class="card-header">
	            Sugerencia ID: <%= sugerencia.getId() %>
	        </div>
	        <div class="card-body">
	            <h5 class="card-title">Mensaje</h5>
	            <p class="card-text"><%= sugerencia.getMensaje() %></p>
	            <h5 class="card-title">Estado</h5>
	            <% if(sugerencia.getEstado().getNombre().equals("enrevision")) { %>
	                <p class="card-text">En Revisión</p>
	            <% } else { %>
	                <p class="card-text"><%= sugerencia.getEstado().getNombre() %></p>
	            <% } %>
	            <a href="/editarSugerencia?id=<%= sugerencia.getId() %>" class="btn btn-danger edit-button">Editar</a>
	        </div>
	    </div>
		<% } %>
	</div>
</body>
</html>
