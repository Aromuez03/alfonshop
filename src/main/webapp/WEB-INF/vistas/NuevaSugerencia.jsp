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
		<form action="/NuevaSugerencia" method="post">
		<h2>Nueva Sugerencia</h2><br>
		
	    <div class="card">
	        <div class="card-header">
	            Introduce la sugerencia
	        </div>
	        <div class="card-body">
	            <h5 class="card-title">Mensaje</h5>
                <textarea id="mensaje" name="mensaje" rows="4" cols="30" class="form-control" style="resize: none" maxlength="500" placeholder="Maximo 500 caracteres"></textarea><br>
				<input type="submit" value="Enviar" class="btn btn-danger enviar-button">
	        </div>
	    </div>
	    </form>
	</div>
</body>
</html>
	