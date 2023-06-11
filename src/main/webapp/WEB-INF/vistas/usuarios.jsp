<%@ page import="java.util.List" %>
<%@ page import="AlfonShop.dto.usuarioDto" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
    <link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/listausuarios.css">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.css">
    <script src="css/scripts/scriptpopup.js"></script>
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
    <h1>Gestión de Usuarios</h1><br>
    <div class="card">
        <div class="card-body">
            <% for (usuarioDto usuarioDTO : (List<usuarioDto>) request.getAttribute("usuarios")) { %>
                <div class="card mb-3 custom-shadow">
                    <div class="card-body">
                        <h5 class="card-title">Nombre: <%= usuarioDTO.getNombre() %></h5>
                        <p class="card-text">Email: <%= usuarioDTO.getEmail() %></p>
                        <p class="card-text">Teléfono: <%= usuarioDTO.getTelefono() %></p>
                        <p class="card-text">Rol: <%= usuarioDTO.getRol().getNombre() %></p>
                        <div class="btn-group">
                        	<% if(usuarioDTO.getRol().getNombre().equals("usuario") || usuarioDTO.getRol().getNombre().equals("admin")){ %>
                            <a href="/usuario/<%= usuarioDTO.getId() %>" class="btn btn-primary">
							    <i class="fas fa-eye"></i>
							</a>
							<a href="/usuario/editar/<%= usuarioDTO.getId() %>" class="btn btn-primary">
							    <i class="fas fa-edit"></i>
							</a>
							<a href="#" class="btn btn-danger" onclick="confirmarEliminacion(<%= usuarioDTO.getId() %>, '<%= request.getAttribute("palabraGenerada") %>')">
							    <i class="fas fa-trash"></i>
							</a>
							<% } %>
                        </div>
                    </div>
                </div>
            <% } %>
        </div>
    </div>
    </div>
</body>
</html>
