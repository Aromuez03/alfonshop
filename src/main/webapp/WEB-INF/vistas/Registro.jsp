<html>
<head>
<meta charset="UTF-8">
<title>Registro de usuario</title>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/registro.css">
<link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
<script src="css/scripts/scriptpopup.js"></script>
</head>
<body>
    <div class="container">
	  <h2>Registro de usuario</h2>
		<% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
			<br>
			<div class="alert alert-danger custom-alert">Ha ocurrido un error vuelve a intentarlo</div>
      	<% } %>
	  <form  method="post" action="/Registro">
	    <div>
	      <label for="nombre">Nombre completo</label>
	      <input type="text" id="nombre" name="nombre" maxlength="30" required>
	    </div>
	    <div>
	      <label for="email">Correo electrónico</label>
	      <input type="email" id="email" name="email" maxlength="50" required>
	    </div>
	    <div>
	      <label for="telefono">Teléfono</label>
	      <input type="tel" id="telefono" name="telefono" maxlength="15" required>
	    </div>
	    <div>
	      <label for="clave">Contraseña</label>
	      <input type="password" id="clave" name="clave" maxlength="15" required>
	    </div>
	    <input type="submit" value="Registro">
	  </form>
	</div>
</body>
</html>
