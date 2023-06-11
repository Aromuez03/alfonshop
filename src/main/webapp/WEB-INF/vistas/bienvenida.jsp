<%@ page import="AlfonShop.dto.usuarioDto" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido a AlfonShop</title>
    <link rel="stylesheet" type="text/css" href="css/bienvenida.css">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/auxiliar1.css">
    <link rel="icon" href="css/iconos/AlfonShop-logo.png" type="image/x-icon">
</head>
<body>
		<nav class="navbar navbar-expand-lg navbar-light ">
	        <div class="container">
	            <a class="navbar-brand" href="/bienvenida">Bienvenida</a>
	            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	            <div class="collapse navbar-collapse" id="navbarNav">
	                <ul class="navbar-nav">
	                    <li class="nav-item">
	                        <a class="nav-link" href="/Catalogo">Cat�logo</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/presupuesto">Presupuesto</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/NuevaSugerencia">Nueva Sugerencia</a>
	                    </li>
	                    <% if(request.getAttribute("rolUsuario").equals("admin") || request.getAttribute("rolUsuario").equals("superadmin")){ %>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/BuzonSugerencias">Buz�n de Sugerencias</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/usuarios">Usuarios</a>
	                    </li>
	                    <% }%>
	                    <% if(request.getAttribute("rolUsuario").equals("usuario") ||request.getAttribute("rolUsuario").equals("admin") || request.getAttribute("rolUsuario").equals("superadmin")){ %>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/Logout">Logout</a>
	                    </li>
	                    <% } else {%>
	                    <li class="nav-item">
	                        <a class="nav-link" href="/Login">Login</a>
	                    </li>
	                    <% }%>
	                </ul>
	            </div>
	        </div>
	    </nav>
	<div class="custom-alert-container">
        <% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
        	<br>
            <div class="alert alert-danger custom-alert">No tienes permisos o est�s verificado</div>
        <% } %>
        <% if (request.getParameter("needverificado") != null && request.getParameter("needverificado").equals("1")) { %>
        	<br>
            <div class="alert alert-success custom-alert">Necesitamos que verifiques tu email</div>
        <% } %>
    </div>
<div class="alfs-crp">
	<div class="alfs-cntnr">
	    <h1 class="alfs-h1">Bienvenido a AlfonShop</h1>
	    <p class="alfs-p">En AlfonShop nos enorgullece ser una de las mejores tiendas online de Andaluc�a, ofreciendo productos de origen nacional. Creemos en el impulso de la econom�a espa�ola y queremos contribuir a superar la recesi�n econ�mica que estamos enfrentando en este 2023.</p>
	    <p class="alfs-p">�Por qu� elegir AlfonShop?</p>
	    <ul class="alfs-ul">
	        <li class="alfs-li">Amplia selecci�n de productos nacionales: Ofrecemos una amplia gama de productos de diferentes categor�as, todos ellos de origen nacional. Desde alimentos y bebidas hasta productos de moda y tecnolog�a, encontrar�s todo lo que necesitas.</li>
	        <li class="alfs-li">Calidad garantizada: Nos aseguramos de trabajar con proveedores y marcas de confianza que cumplen con los m�s altos est�ndares de calidad. Solo ofrecemos productos que sabemos que cumplir�n con las expectativas de nuestros clientes.</li>
	        <li class="alfs-li">Env�o r�pido y seguro: Nos preocupamos por la satisfacci�n de nuestros clientes y nos esforzamos por brindar un servicio de env�o r�pido y seguro. Trabajamos con empresas de log�stica confiables para que tus productos lleguen a tiempo y en perfectas condiciones.</li>
	        <li class="alfs-li">Contribuci�n a la econom�a espa�ola: Al elegir productos de origen nacional, est�s ayudando a fortalecer la econom�a espa�ola. Tu apoyo a la producci�n local tiene un impacto positivo en la generaci�n de empleo y el crecimiento econ�mico de nuestro pa�s.</li>
	        <li class="alfs-li">Atenci�n al cliente excepcional: Nuestro equipo de atenci�n al cliente est� disponible para ayudarte en todo momento. Si tienes alguna pregunta, consulta o problema, no dudes en contactarnos. Estamos aqu� para brindarte la mejor experiencia de compra.</li>
	    </ul>
	    <p class="alfs-p">�Gracias por elegir AlfonShop! Explora nuestra tienda y descubre la amplia variedad de productos de calidad que tenemos para ofrecerte.</p>
	</div>
</div>
</body>
</html>
