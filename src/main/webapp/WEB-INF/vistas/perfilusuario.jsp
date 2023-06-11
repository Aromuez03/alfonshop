<%@ page import="AlfonShop.dto.usuarioDto" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles del Usuario</title> 
    <link rel="stylesheet" type="text/css" href="../css/sugerencia.css">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="icon" href="../css/iconos/AlfonShop-logo.png" type="image/x-icon">
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
        <h2>Detalles del Usuario</h2>
        <div class="card">
            <div class="card-header">
                ID: ${usuario.id}
            </div>
            <div class="card-body">
                <table>
                    <tr>
                        <td class="card-title">Nombre:</td>
                        <td class="card-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${usuario.nombre}</td>
                    </tr>
                    <tr>
                        <td class="card-title">Email:</td>
                        <td class="card-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${usuario.email}</td>
                    </tr>
                    <tr>
                        <td class="card-title">Teléfono:</td>
                        <td class="card-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${usuario.telefono}</td>
                    </tr>
                    <tr>
                        <td class="card-title">Rol:</td>
                        <td class="card-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${usuario.rol.nombre}</td>
                    </tr>
                </table>
                <br>
                <a href="/usuarios" class="btn btn-danger">Volver a la lista de usuarios</a>
            </div>
        </div>
    </div>
</body>
</html>
