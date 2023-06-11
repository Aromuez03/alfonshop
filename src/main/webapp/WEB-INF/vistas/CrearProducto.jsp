<%@ page import="java.util.List" %>
<%@ page import="AlfonShop.dto.productoDto" %>
<%@ page import="AlfonShop.dao.categoria" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Producto</title>
    <link rel="icon" href="../css/iconos/AlfonShop-logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="../css/sugerencia.css">
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
	<div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Crear Producto</h2>
            </div>
            <div class="card-body">
                <form action="/Catalogo/GuardarProducto" method="POST">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="precio">Precio:</label>
                        <input type="number" class="form-control" id="precio" name="precio" required>
                    </div>
                    <div class="form-group">
                        <label for="cantidad">Cantidad:</label>
                        <input type="number" class="form-control" id="cantidad" name="cantidad" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripcion:</label>
                        <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                    </div>
                    <div class="form-group">
                        <label for="urlImg">Url de la Imagen:</label>
                        <input type="url" class="form-control" id="urlImg" name="urlImg" required>
                    </div>
                    <div class="form-group">
                        <label for="categoria">Categoría:</label>
                        <select class="form-control" id="categoria" name="categoria" required>
                            <% for (categoria categoria : (List<categoria>) request.getAttribute("categorias")) { %>
                                <option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-danger">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
	