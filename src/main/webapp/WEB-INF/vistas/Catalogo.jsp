<%@ page import="java.util.List" %>
<%@ page import="AlfonShop.dto.productoDto" %>
<%@ page import="AlfonShop.dao.categoria" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catálogo de productos</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/catalogo2.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.css">
    <script src="css/scripts/scriptpopup2.js"></script>
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
    	<br>
        <h1>Catálogo de productos</h1><% if(request.getAttribute("rolUsuario").equals("admin") || request.getAttribute("rolUsuario").equals("superadmin")){ %> <a href="/Catalogo/CrearProducto" class="btn btn-danger margenIzqd">Nuevo Producto</a><% } %>   
        <div class="row">
            <div class="col-md-6">
                <form method="get" action="${request.contextPath}/Catalogo">
                    <label for="categoria">Filtrar por categoría:</label>
                    <select id="categoria" name="categoriaId">
                        <option value="">Todas las categorías</option>
                        <% 
                        for (categoria categoria : (List<categoria>) request.getAttribute("categorias")) { 
                            String selected = "";
                            if (categoria.getId() == (int) request.getAttribute("categoriaSeleccionada")) {
                                selected = "selected";
                            }
                        %>
                        <option value="<%= categoria.getId() %>" <%= selected %>><%= categoria.getNombre() %></option>
                        <% } %>
                    </select>
                    <input type="submit" class="btn btn-danger" value="Filtrar">
                </form>
            </div>
        </div>
        <div class="row">
            <% 
            for (productoDto producto : (List<productoDto>) request.getAttribute("productos")) { 
            %>
            <div class="col-md-4">
                <div class="card">
                    <img src="<%= producto.getUrlImg() %>" class="card-img-top imgnMizq" alt="<%= producto.getDescripcion() %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= producto.getNombre() %></h5>
                        <p class="card-text"><%= producto.getPrecio() %>$</p>
                        <% if(request.getAttribute("rolUsuario").equals("admin") || request.getAttribute("rolUsuario").equals("superadmin")){ %>
                        <a href="/Catalogo/EditarProducto/<%= producto.getId() %>" class="btn btn-primary"><i class="fas fa-edit"></i></a>
                        <a href="#" class="btn btn-danger" onclick="confirmarEliminacion(<%= producto.getId() %>, '<%= request.getAttribute("palabraGenerada") %>')">
							<i class="fas fa-trash"></i>
						</a>
						<% }%>  
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
