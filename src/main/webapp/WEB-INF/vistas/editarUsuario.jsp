<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" type="text/css" href="../../css/sugerencia.css">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="icon" href="../../css/iconos/AlfonShop-logo.png" type="image/x-icon">
    <style type="text/css">
    </style>
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
    	<h2>Editar Usuario</h2>
     	<div class="card">
		    <form method="post" action="/usuario/guardar">
		    <div class="card-body">
		        <input type="hidden" name="id" value="${usuario.id}" />
		        <input type="hidden" name="verificado" value="${usuario.verificado}" />
		        <div class="form-group">
		            <h5 class="card-title">Nombre: <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" /></h5> 
			        <h5 class="card-title">Email: <input type="email" id="email" name="email" value="${usuario.email}" /></h5>
			        <h5 class="card-title">Teléfono: <input type="tel" id="telefono" name="telefono" value="${usuario.telefono}" /></h5>
			        <h5 class="card-title">Rol:
				        <select id="rol.id" name="rol.id">
				        	<option value="" selected="selected" disabled="disabled">Selecciona un rol</option>
				        	<option value="1">usuario</option>
				        	<option value="2">Administrador</option>
				        </select>
			        </h5>
		        </div>
			    <input type="hidden" id="clave" name="clave" /><br>
			    <input type="submit" value="Guardar" class="btn btn-danger"/>
		     </div>
		    </form>
	    </div>
    </div>
</body>
</html>
