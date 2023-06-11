<html>
<head>
    <title>Recuperar Contrase�a</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/recuperacion.css">
    <link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-body">
            <h1 class="card-title">Recuperar Contrase�a</h1>

            <% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
                <div class="alert alert-danger">El correo electr�nico no existe</div>
            <% } %>

            <form action="${pageContext.request.contextPath}/RecuperarContrasena" method="post">
                <div class="form-group">
                    <label for="email">Correo Electr�nico:</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Recuperar Contrase�a</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
