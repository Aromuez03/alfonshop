<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/login2.css">
	<link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
</head>
<body>
<% if (request.getParameter("error2") != null && request.getParameter("error2").equals("1")) { %>
	<p class="alert alert-danger">Es necesario que inicies sesion antes de continuar</p>
<% } %>
<% if (request.getParameter("recuperacion") != null && request.getParameter("recuperacion").equals("1")) { %>
	<p class="alert alert-success">Revise la bandeja de entrada de su email </p>
<% } %>
	<h1>Inicio de Sesión</h1>
	<form method="post" action="/Login">
		<table>
			<tr>
				<td>Email de usuario:</td>
				<td><input type="email" style="width: 100%; padding: 12px 20px; margin: 8px 0; box-sizing: border-box; border: 2px solid #ccc; border-radius: 4px;" name="email" placeholder="ejemplo@gmail.com" maxlength="50" required/></td>
			</tr>
			<tr>
				<td>Contraseña:</td>
				<td><input type="password" name="clave" placeholder="1Hm#I6a4L" maxlength="15" required/></td>
			</tr>
			<% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
				<tr>
					<td></td>
				</tr>
                <tr>
					<td colspan="2" class="alert alert-danger">Las credenciales no son correctas</td>
				</tr>
            <% } %>
			<tr>
				<td colspan="2" class="formatosubmit"><input type="submit" value="Iniciar sesión" class="btn btn-primary btn-sm" /></td>
			</tr>
			<tr>
				<td class="centrado"><a href="/RecuperarContrasena">¿Ha olvidado la contraseña?</a></td>
				<td><a href="/Registro">Registrarse</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
