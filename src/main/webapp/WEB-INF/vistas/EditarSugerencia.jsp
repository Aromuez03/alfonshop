<%@ page import="AlfonShop.dto.sugerenciaDto" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Sugerencia</title>
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
    	<h2>Editar Sugerencia</h2>
     	<div class="card">
		    <form action="/editarSugerencia" method="post">
		    <div class="card-body">
		        <input type="hidden" name="id" value="${sugerenciaDto.id}">
		        <div class="form-group">
		            <h5 class="card-title">Mensaje</h5><input type="hidden" name="mensaje" value="${sugerenciaDto.mensaje}">
		            <p class="card-text">${sugerenciaDto.mensaje}</p>
		        </div>
		        <div class="form-group">
		            <h5 class="card-title">Estado</h5>
		            <select class="form-control" id="estado" name="estado">
		                <option value="1" ${sugerenciaDto.estado.id == 1 ? 'selected' : ''}>En Revisión</option>
		                <option value="2" ${sugerenciaDto.estado.id == 2 ? 'selected' : ''}>Aprobada</option>
		                <option value="3" ${sugerenciaDto.estado.id == 3 ? 'selected' : ''}>Rechazada</option>
		            </select>
		        </div>
		        <button type="submit" class="btn btn-danger edit-button">Guardar</button>
		     </div>
		    </form>
	    </div>
    </div>
</body>
</html>
