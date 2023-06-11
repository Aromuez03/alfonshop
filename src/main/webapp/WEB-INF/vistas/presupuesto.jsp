<%@ page import="AlfonShop.dto.productoDto" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Presupuesto</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="css/scripts/scriptpresupuesto.js"></script>
 	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/presupuesto2.css">
</head>
<body>
	<div class="navcard">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
	        <div class="container">
	            <a class="navbar-brand mrgnizq" href="/bienvenida">Bienvenida</a>
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
	                    <% if(request.getAttribute("rolUsuario").equals("admin") || request.getAttribute("rolUsuario").equals("superadmin")){ %>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/BuzonSugerencias">Buzón de Sugerencias</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/usuarios">Usuarios</a>
	                    </li>
	                    <% }%>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/Logout">Logout</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </nav>
    </div>
	<div class="cardgenerico">
	    <div class="container">
	    	<h2>Presupuesto</h2>
		    <form id="generarPresupuestoForm">
				<% for (productoDto producto : (List<productoDto>)request.getAttribute("productos")) { %>
					<div class="card">
						<div class="card-body">
							<h5 class="card-title"><%= producto.getNombre() %></h5>
							<p class="card-text">Precio: <%= producto.getPrecio() %>$</p>
							<img src="<%= producto.getUrlImg() %>" alt="<%= producto.getDescripcion() %>" class="card-img-top"><br>
							<input type="checkbox" name="productos" value="<%= producto.getId() %>">
						</div>
					</div>
				<% } %>
		    </form>
		    <div class="btn-wrapper">
		        <button type="submit" form="generarPresupuestoForm" class="btn btn-danger">Generar Presupuesto</button>
		    </div>
		    <br>
		</div>
    </div>
</body>
</html>
